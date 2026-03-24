package com.threemdroid.habittracker.feature.learn

import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel
import com.threemdroid.habittracker.feature.learn.contract.LearnScreenState
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState
import com.threemdroid.habittracker.feature.learn.presentation.LearnReducer
import com.threemdroid.habittracker.feature.learn.presentation.LearnResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LearnReducerTest {
    @Test
    fun loading_resetsStateToLoading() {
        val initialState = LearnUiState(
            screenState = LearnScreenState.Content,
            articles = listOf(article("learn-1")),
            selectedArticleId = "learn-1",
        )

        val reduced = LearnReducer.reduce(initialState, LearnResult.Loading)

        assertEquals(LearnScreenState.Loading, reduced.screenState)
        assertTrue(reduced.articles.isEmpty())
        assertNull(reduced.selectedArticleId)
    }

    @Test
    fun contentLoaded_withArticles_setsContentState() {
        val reduced = LearnReducer.reduce(
            LearnUiState.initial(),
            LearnResult.ContentLoaded(listOf(article("learn-1"), article("learn-2"))),
        )

        assertEquals(LearnScreenState.Content, reduced.screenState)
        assertEquals(2, reduced.articles.size)
    }

    @Test
    fun contentLoaded_withoutArticles_setsEmptyState() {
        val reduced = LearnReducer.reduce(
            LearnUiState.initial(),
            LearnResult.ContentLoaded(emptyList()),
        )

        assertEquals(LearnScreenState.Empty, reduced.screenState)
        assertTrue(reduced.articles.isEmpty())
    }

    @Test
    fun contentLoaded_whenSelectionStillExists_preservesSelection() {
        val reduced = LearnReducer.reduce(
            LearnUiState(
                screenState = LearnScreenState.Content,
                articles = listOf(article("learn-1")),
                selectedArticleId = "learn-1",
            ),
            LearnResult.ContentLoaded(listOf(article("learn-1"), article("learn-2"))),
        )

        assertEquals(LearnScreenState.Content, reduced.screenState)
        assertEquals("learn-1", reduced.selectedArticleId)
    }

    @Test
    fun contentLoaded_whenSelectionNoLongerExists_clearsSelection() {
        val reduced = LearnReducer.reduce(
            LearnUiState(
                screenState = LearnScreenState.Content,
                articles = listOf(article("learn-1")),
                selectedArticleId = "learn-1",
            ),
            LearnResult.ContentLoaded(listOf(article("learn-2"))),
        )

        assertEquals(LearnScreenState.Content, reduced.screenState)
        assertNull(reduced.selectedArticleId)
    }

    @Test
    fun loadFailed_setsErrorStateAndClearsContent() {
        val reduced = LearnReducer.reduce(
            LearnUiState(
                screenState = LearnScreenState.Content,
                articles = listOf(article("learn-1")),
                selectedArticleId = "learn-1",
            ),
            LearnResult.LoadFailed("Learn is unavailable."),
        )

        assertTrue(reduced.screenState is LearnScreenState.Error)
        assertTrue(reduced.articles.isEmpty())
        assertNull(reduced.selectedArticleId)
    }

    @Test
    fun articleSelectionChanged_updatesSelectedArticleId() {
        val reduced = LearnReducer.reduce(
            LearnUiState(
                screenState = LearnScreenState.Content,
                articles = listOf(article("learn-1")),
            ),
            LearnResult.ArticleSelectionChanged("learn-1"),
        )

        assertEquals("learn-1", reduced.selectedArticleId)
    }
}

private fun article(id: String): LearnArticleUiModel = LearnArticleUiModel(
    id = id,
    title = "Article $id",
)
