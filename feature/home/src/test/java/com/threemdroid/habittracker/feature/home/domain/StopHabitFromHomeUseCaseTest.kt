package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.feature.home.testutil.HOME_TEST_INSTANT
import com.threemdroid.habittracker.feature.home.testutil.TestHomeHabitsRepository
import com.threemdroid.habittracker.feature.home.testutil.homeTestClock
import com.threemdroid.habittracker.feature.home.testutil.homeTestHabit
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StopHabitFromHomeUseCaseTest {
    @Test
    fun invoke_activeHabit_marksItStoppedWithCurrentClockInstant() = runTest {
        val repository = TestHomeHabitsRepository().apply {
            setHabits(listOf(homeTestHabit()))
        }

        val result = createUseCase(repository).invoke("habit-1")

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(1, repository.upsertHabitCallCount)
        assertEquals(HabitState.STOPPED, repository.currentHabits().single().state)
        assertEquals(HOME_TEST_INSTANT, repository.currentHabits().single().stoppedAt)
    }

    @Test
    fun invoke_alreadyStoppedHabit_returnsSuccessWithoutUpsert() = runTest {
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

        val result = createUseCase(repository).invoke("habit-1")

        assertEquals(AppResult.Success(Unit), result)
        assertEquals(0, repository.upsertHabitCallCount)
        assertEquals(HOME_TEST_INSTANT, repository.currentHabits().single().stoppedAt)
    }

    @Test
    fun invoke_missingHabit_returnsNotFoundFailure() = runTest {
        val repository = TestHomeHabitsRepository()

        val result = createUseCase(repository).invoke("missing-habit")

        assertTrue(result is AppResult.Failure)
        assertEquals(
            AppError.NotFound(message = "Habit missing-habit was not found."),
            (result as AppResult.Failure).error,
        )
        assertEquals(0, repository.upsertHabitCallCount)
    }

    private fun createUseCase(
        repository: TestHomeHabitsRepository,
    ): StopHabitFromHomeUseCase = StopHabitFromHomeUseCase(
        habitsRepository = repository,
        clock = homeTestClock(),
    )
}
