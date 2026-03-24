package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.home.testutil.HOME_TEST_DATE
import com.threemdroid.habittracker.feature.home.testutil.HOME_TEST_INSTANT
import com.threemdroid.habittracker.feature.home.testutil.TestHomeHabitsRepository
import com.threemdroid.habittracker.feature.home.testutil.homeTestClock
import com.threemdroid.habittracker.feature.home.testutil.homeTestHabit
import com.threemdroid.habittracker.feature.home.testutil.homeTestHabitEntry
import java.time.Instant
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RecordHomeHabitProgressUseCaseTest {
    @Test
    fun invoke_booleanHabitWithoutMultipleUpdates_existingEntryIsNoOp() = runTest {
        val repository = TestHomeHabitsRepository().apply {
            setHabits(
                listOf(
                    homeTestHabit(
                        type = HabitType.BOOLEAN,
                        allowsMultipleUpdatesPerDay = false,
                    ),
                ),
            )
            setEntries(
                listOf(
                    homeTestHabitEntry(
                        id = "existing-entry",
                        loggedAt = Instant.parse("2026-03-24T06:00:00Z"),
                    ),
                ),
            )
        }

        val result = createUseCase(repository).invoke(
            habitId = "habit-1",
            entryDate = HOME_TEST_DATE,
        )

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(0, repository.upsertHabitEntryCallCount)
        assertEquals("existing-entry", repository.currentEntries().single().id)
        assertEquals(1.0, repository.currentEntries().single().value, 0.0)
    }

    @Test
    fun invoke_quantityHabitWithoutMultipleUpdates_reusesExistingEntryId() = runTest {
        val repository = TestHomeHabitsRepository().apply {
            setHabits(
                listOf(
                    homeTestHabit(
                        type = HabitType.QUANTITY,
                        allowsMultipleUpdatesPerDay = false,
                    ),
                ),
            )
            setEntries(listOf(homeTestHabitEntry(id = "existing-entry", value = 1.0)))
        }

        val result = createUseCase(repository).invoke(
            habitId = "habit-1",
            entryDate = HOME_TEST_DATE,
            requestedValue = 3.5,
        )

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(1, repository.upsertHabitEntryCallCount)
        assertEquals(1, repository.currentEntries().size)
        assertEquals("existing-entry", repository.currentEntries().single().id)
        assertEquals(3.5, repository.currentEntries().single().value, 0.0)
        assertEquals(HOME_TEST_INSTANT, repository.currentEntries().single().loggedAt)
    }

    @Test
    fun invoke_whenMultipleUpdatesAreAllowed_generatesNewEntryId() = runTest {
        val repository = TestHomeHabitsRepository().apply {
            setHabits(
                listOf(
                    homeTestHabit(
                        type = HabitType.QUANTITY,
                        allowsMultipleUpdatesPerDay = true,
                    ),
                ),
            )
            setEntries(listOf(homeTestHabitEntry(id = "existing-entry", value = 1.0)))
        }

        val result = createUseCase(repository).invoke(
            habitId = "habit-1",
            entryDate = HOME_TEST_DATE,
            requestedValue = 2.0,
        )

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(1, repository.upsertHabitEntryCallCount)
        assertEquals(2, repository.currentEntries().size)
        assertEquals(
            setOf("existing-entry", "generated-entry-id"),
            repository.currentEntries().map { entry -> entry.id }.toSet(),
        )
    }

    @Test
    fun invoke_withoutRequestedValue_usesDefaultIncrementAndDateScopedId() = runTest {
        val repository = TestHomeHabitsRepository().apply {
            setHabits(
                listOf(
                    homeTestHabit(
                        id = "habit-2",
                        type = HabitType.QUANTITY,
                        defaultIncrement = 1.25,
                        allowsMultipleUpdatesPerDay = false,
                    ),
                ),
            )
        }

        val result = createUseCase(repository).invoke(
            habitId = "habit-2",
            entryDate = HOME_TEST_DATE,
        )

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(1, repository.currentEntries().size)
        assertEquals("habit-2-$HOME_TEST_DATE", repository.currentEntries().single().id)
        assertEquals(1.25, repository.currentEntries().single().value, 0.0)
    }

    @Test
    fun invoke_stoppedHabit_returnsValidationFailure() = runTest {
        val repository = TestHomeHabitsRepository().apply {
            setHabits(
                listOf(
                    homeTestHabit(
                        state = HabitState.STOPPED,
                        stoppedAt = HOME_TEST_INSTANT,
                    ),
                ),
            )
        }

        val result = createUseCase(repository).invoke(
            habitId = "habit-1",
            entryDate = HOME_TEST_DATE,
        )

        assertTrue(result is AppResult.Failure)
        assertEquals(
            AppError.Validation(message = "Stopped habits cannot be updated from Home."),
            (result as AppResult.Failure).error,
        )
        assertEquals(0, repository.upsertHabitEntryCallCount)
    }

    private fun createUseCase(
        repository: TestHomeHabitsRepository,
    ): RecordHomeHabitProgressUseCase = RecordHomeHabitProgressUseCase(
        habitsRepository = repository,
        clock = homeTestClock(),
        idGenerator = { "generated-entry-id" },
    )
}
