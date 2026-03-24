package com.threemdroid.habittracker.feature.learn.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.feature.learn.testutil.TestLearnRepository
import com.threemdroid.habittracker.feature.learn.testutil.learnContent
import com.threemdroid.habittracker.feature.learn.testutil.learnSection
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ObserveLearnContentUseCaseTest {
    @Test
    fun invoke_mapsRepositoryContentIntoUiModels() = runTest {
        val repository = TestLearnRepository().apply {
            setContent(
                listOf(
                    learnContent(
                        id = "routine",
                        title = "Routine / Habit Building",
                        imageUrl = "https://iomc.rs/rutinna/db/routine.jpg",
                        sections = listOf(
                            learnSection(
                                id = "routine-1",
                                title = "Automates Good Behavior",
                                body = "Habits reduce the need for constant decision-making.",
                            ),
                            learnSection(
                                id = "routine-2",
                                title = "Improves Efficiency",
                                body = "Having a structured routine removes wasted time.",
                            ),
                        ),
                        videoUrl = "https://iomc.rs/rutinna/db/routine.mp4",
                    ),
                ),
            )
        }

        val result = ObserveLearnContentUseCase(repository).invoke().first()

        assertTrue(result is AppResult.Success)
        result as AppResult.Success
        assertEquals(listOf("Routine / Habit Building"), result.value.map { it.title })
        assertEquals(
            listOf("https://iomc.rs/rutinna/db/routine.jpg"),
            result.value.map { it.imageUrl },
        )
        assertEquals(
            listOf(
                listOf(
                    "Automates Good Behavior\n\nHabits reduce the need for constant decision-making.",
                    "Improves Efficiency\n\nHaving a structured routine removes wasted time.",
                ),
            ),
            result.value.map { it.paragraphs },
        )
        assertEquals(
            listOf("https://iomc.rs/rutinna/db/routine.mp4"),
            result.value.map { it.videoUrl },
        )
    }

    @Test
    fun invoke_whenRepositoryFails_returnsFailureUnchanged() = runTest {
        val repository = TestLearnRepository().apply {
            setFailure(
                AppError.Unavailable(
                    message = "Learn content is blocked until a verified Learn contract exists in the repository.",
                ),
            )
        }

        val result = ObserveLearnContentUseCase(repository).invoke().first()

        assertTrue(result is AppResult.Failure)
        result as AppResult.Failure
        assertEquals(
            "Learn content is blocked until a verified Learn contract exists in the repository.",
            result.error.message,
        )
    }
}
