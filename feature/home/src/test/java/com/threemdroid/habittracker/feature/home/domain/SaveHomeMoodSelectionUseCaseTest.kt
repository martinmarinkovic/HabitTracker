package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.feature.home.testutil.HOME_TEST_DATE
import com.threemdroid.habittracker.feature.home.testutil.HOME_TEST_INSTANT
import com.threemdroid.habittracker.feature.home.testutil.TestHomeActivityRepository
import com.threemdroid.habittracker.feature.home.testutil.homeTestClock
import com.threemdroid.habittracker.feature.home.testutil.homeTestMoodEntry
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SaveHomeMoodSelectionUseCaseTest {
    @Test
    fun invoke_blankMoodToken_returnsValidationFailure() = runTest {
        val repository = TestHomeActivityRepository()

        val result = createUseCase(repository).invoke(
            entryDate = HOME_TEST_DATE,
            moodToken = " ",
        )

        assertTrue(result is AppResult.Failure)
        assertEquals(
            AppError.Validation(message = "Mood token must not be blank."),
            (result as AppResult.Failure).error,
        )
        assertEquals(0, repository.upsertMoodEntryCallCount)
    }

    @Test
    fun invoke_withoutExistingMood_usesDateScopedMoodId() = runTest {
        val repository = TestHomeActivityRepository()

        val result = createUseCase(repository).invoke(
            entryDate = HOME_TEST_DATE,
            moodToken = "calm",
        )

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(1, repository.upsertMoodEntryCallCount)
        assertEquals(1, repository.currentMoodEntries().size)
        assertEquals("mood-$HOME_TEST_DATE", repository.currentMoodEntries().single().id)
        assertEquals(HOME_TEST_INSTANT, repository.currentMoodEntries().single().loggedAt)
        assertEquals("calm", repository.currentMoodEntries().single().moodToken)
    }

    @Test
    fun invoke_withExistingMood_reusesExistingEntryId() = runTest {
        val repository = TestHomeActivityRepository().apply {
            setMoodEntries(
                listOf(
                    homeTestMoodEntry(
                        id = "existing-mood-id",
                        moodToken = "focused",
                    ),
                ),
            )
        }

        val result = createUseCase(repository).invoke(
            entryDate = HOME_TEST_DATE,
            moodToken = "energized",
        )

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(1, repository.currentMoodEntries().size)
        assertEquals("existing-mood-id", repository.currentMoodEntries().single().id)
        assertEquals("energized", repository.currentMoodEntries().single().moodToken)
    }

    @Test
    fun invoke_whenMoodObservationFails_returnsTheFailure() = runTest {
        val expectedError = AppError.Storage(
            source = "mood",
            message = "Mood query failed.",
        )
        val repository = TestHomeActivityRepository().apply {
            setMoodEntriesFailure(expectedError)
        }

        val result = createUseCase(repository).invoke(
            entryDate = HOME_TEST_DATE,
            moodToken = "calm",
        )

        assertEquals(AppResult.Failure(expectedError), result)
        assertEquals(0, repository.upsertMoodEntryCallCount)
    }

    private fun createUseCase(
        repository: TestHomeActivityRepository,
    ): SaveHomeMoodSelectionUseCase = SaveHomeMoodSelectionUseCase(
        activityRepository = repository,
        clock = homeTestClock(),
    )
}
