package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.home.contract.HomeHabitItem
import com.threemdroid.habittracker.feature.home.testutil.HOME_TEST_DATE
import com.threemdroid.habittracker.feature.home.testutil.TestHomeActivityRepository
import com.threemdroid.habittracker.feature.home.testutil.TestHomeHabitsRepository
import com.threemdroid.habittracker.feature.home.testutil.homeTestHabit
import com.threemdroid.habittracker.feature.home.testutil.homeTestHabitEntry
import com.threemdroid.habittracker.feature.home.testutil.homeTestMoodEntry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ObserveHomeDataUseCaseTest {
    @Test
    fun invoke_filtersToActiveScheduledHabits_andAggregatesHomeSnapshot() = runTest {
        val habitsRepository = TestHomeHabitsRepository().apply {
            setHabits(
                listOf(
                    homeTestHabit(
                        id = "habit-everyday",
                        type = HabitType.QUANTITY,
                        targetValue = 3.0,
                        defaultIncrement = 1.0,
                        selectedDays = emptySet(),
                    ),
                    homeTestHabit(
                        id = "habit-today",
                        type = HabitType.QUANTITY,
                        targetValue = 2.0,
                        defaultIncrement = 0.5,
                        selectedDays = setOf(HOME_TEST_DATE.dayOfWeek),
                    ),
                    homeTestHabit(
                        id = "habit-other-day",
                        selectedDays = setOf(HOME_TEST_DATE.plusDays(1).dayOfWeek),
                    ),
                    homeTestHabit(
                        id = "habit-stopped",
                        state = HabitState.STOPPED,
                    ),
                ),
            )
            setEntries(
                listOf(
                    homeTestHabitEntry(id = "entry-1", habitId = "habit-everyday", value = 1.0),
                    homeTestHabitEntry(id = "entry-2", habitId = "habit-today", value = 1.25),
                    homeTestHabitEntry(id = "entry-3", habitId = "habit-today", value = 1.0),
                    homeTestHabitEntry(id = "entry-4", habitId = "habit-other-day", value = 9.0),
                ),
            )
        }
        val activityRepository = TestHomeActivityRepository().apply {
            setMoodEntries(
                listOf(
                    homeTestMoodEntry(id = "mood-1", moodToken = "calm"),
                    homeTestMoodEntry(id = "mood-2", moodToken = "energized"),
                ),
            )
        }

        val result = ObserveHomeDataUseCase(
            habitsRepository = habitsRepository,
            activityRepository = activityRepository,
        ).invoke(HOME_TEST_DATE).first()

        val snapshot = result.assertSuccess()
        assertEquals(listOf("habit-everyday", "habit-today"), snapshot.habitsToday.map(HomeHabitItem::habitId))
        assertEquals("calm", snapshot.selectedMoodToken)

        val everydayHabit = snapshot.habitsToday.first { item -> item.habitId == "habit-everyday" }
        assertEquals(1.0, everydayHabit.progressValue, 0.0)
        assertEquals(false, everydayHabit.isCompleted)

        val todayHabit = snapshot.habitsToday.first { item -> item.habitId == "habit-today" }
        assertEquals(2.25, todayHabit.progressValue, 0.0)
        assertTrue(todayHabit.isCompleted)
    }

    @Test
    fun invoke_whenMoodObservationFails_returnsTheFailure() = runTest {
        val expectedError = AppError.Storage(
            source = "mood",
            message = "Mood query failed.",
        )
        val habitsRepository = TestHomeHabitsRepository().apply {
            setHabits(listOf(homeTestHabit()))
            setEntries(emptyList())
        }
        val activityRepository = TestHomeActivityRepository().apply {
            setMoodEntriesFailure(expectedError)
        }

        val result = ObserveHomeDataUseCase(
            habitsRepository = habitsRepository,
            activityRepository = activityRepository,
        ).invoke(HOME_TEST_DATE).first()

        assertEquals(AppResult.Failure(expectedError), result)
    }

    private fun AppResult<HomeSnapshot>.assertSuccess(): HomeSnapshot = when (this) {
        is AppResult.Success -> value
        is AppResult.Failure -> throw AssertionError("Expected success but was $this")
    }
}
