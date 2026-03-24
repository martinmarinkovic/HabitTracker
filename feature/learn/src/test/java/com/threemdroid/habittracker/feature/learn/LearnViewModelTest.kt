package com.threemdroid.habittracker.feature.learn

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.feature.learn.contract.LearnEffect
import com.threemdroid.habittracker.feature.learn.contract.LearnIntent
import com.threemdroid.habittracker.feature.learn.contract.LearnScreenState
import com.threemdroid.habittracker.feature.learn.domain.ObserveLearnContentUseCase
import com.threemdroid.habittracker.feature.learn.presentation.LearnViewModel
import com.threemdroid.habittracker.feature.learn.testutil.TestLearnRepository
import com.threemdroid.habittracker.feature.learn.testutil.learnContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LearnViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun init_whenRepositoryFails_setsErrorState() = runTest(dispatcher) {
        val repository = TestLearnRepository().apply {
            setFailure(
                AppError.Unavailable(
                    message = "Learn content is blocked until a verified Learn contract exists in the repository.",
                ),
            )
        }

        val viewModel = learnViewModel(repository)
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.screenState is LearnScreenState.Error)
        assertNull(viewModel.uiState.value.selectedArticleId)
    }

    @Test
    fun init_whenRepositorySucceeds_setsContentState() = runTest(dispatcher) {
        val repository = TestLearnRepository().apply {
            setContent(
                listOf(
                    learnContent(id = "learn-1", title = "Why Habits Stick"),
                    learnContent(id = "learn-2", title = "Mood Patterns"),
                ),
            )
        }

        val viewModel = learnViewModel(repository)
        advanceUntilIdle()

        assertEquals(LearnScreenState.Content, viewModel.uiState.value.screenState)
        assertEquals(2, viewModel.uiState.value.articles.size)
    }

    @Test
    fun init_whenRepositorySucceedsWithEmptyContent_setsEmptyState() = runTest(dispatcher) {
        val repository = TestLearnRepository().apply {
            setContent(emptyList())
        }

        val viewModel = learnViewModel(repository)
        advanceUntilIdle()

        assertEquals(LearnScreenState.Empty, viewModel.uiState.value.screenState)
        assertTrue(viewModel.uiState.value.articles.isEmpty())
    }

    @Test
    fun articleSelected_andDetailBackRequested_updatesSelectionState() = runTest(dispatcher) {
        val repository = TestLearnRepository().apply {
            setContent(listOf(learnContent(id = "learn-1", title = "Why Habits Stick")))
        }

        val viewModel = learnViewModel(repository)
        advanceUntilIdle()

        viewModel.handleIntent(LearnIntent.ArticleSelected("learn-1"))
        advanceUntilIdle()
        assertEquals("learn-1", viewModel.uiState.value.selectedArticleId)

        viewModel.handleIntent(LearnIntent.DetailBackRequested)
        advanceUntilIdle()
        assertNull(viewModel.uiState.value.selectedArticleId)
    }

    @Test
    fun articleSelected_withUnknownId_keepsSelectionUnchanged() = runTest(dispatcher) {
        val repository = TestLearnRepository().apply {
            setContent(listOf(learnContent(id = "learn-1", title = "Why Habits Stick")))
        }

        val viewModel = learnViewModel(repository)
        advanceUntilIdle()

        viewModel.handleIntent(LearnIntent.ArticleSelected("missing-id"))
        advanceUntilIdle()

        assertNull(viewModel.uiState.value.selectedArticleId)
    }

    @Test
    fun retryLoad_afterFailureUsesLatestRepositoryValue() = runTest(dispatcher) {
        val repository = TestLearnRepository().apply {
            setFailure(AppError.Unavailable(message = "Blocked"))
        }

        val viewModel = learnViewModel(repository)
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value.screenState is LearnScreenState.Error)

        repository.setContent(listOf(learnContent(id = "learn-1", title = "Why Habits Stick")))
        viewModel.handleIntent(LearnIntent.RetryLoad)
        advanceUntilIdle()

        assertEquals(LearnScreenState.Content, viewModel.uiState.value.screenState)
        assertEquals(listOf("Why Habits Stick"), viewModel.uiState.value.articles.map { it.title })
    }

    @Test
    fun videoPlaybackRequested_withSelectedArticleVideo_emitsOpenVideoEffect() = runTest(dispatcher) {
        val repository = TestLearnRepository().apply {
            setContent(
                listOf(
                    learnContent(
                        id = "routine",
                        title = "Routine / Habit Building",
                        videoUrl = "https://iomc.rs/rutinna/db/routine.mp4",
                    ),
                ),
            )
        }

        val viewModel = learnViewModel(repository)
        advanceUntilIdle()

        viewModel.handleIntent(LearnIntent.ArticleSelected("routine"))
        val effectDeferred = async { viewModel.effects.first() }

        viewModel.handleIntent(LearnIntent.VideoPlaybackRequested)
        advanceUntilIdle()

        assertEquals(
            LearnEffect.OpenVideo("https://iomc.rs/rutinna/db/routine.mp4"),
            effectDeferred.await(),
        )
    }

    private fun learnViewModel(
        repository: TestLearnRepository,
    ): LearnViewModel = LearnViewModel(
        observeLearnContentUseCase = ObserveLearnContentUseCase(repository),
    )
}
