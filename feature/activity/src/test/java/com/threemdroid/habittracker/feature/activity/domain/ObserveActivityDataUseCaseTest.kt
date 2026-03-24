package com.threemdroid.habittracker.feature.activity.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.testutil.ACTIVITY_TEST_DATE
import com.threemdroid.habittracker.feature.activity.testutil.ACTIVITY_TEST_INSTANT
import com.threemdroid.habittracker.feature.activity.testutil.TestActivityHabitsRepository
import com.threemdroid.habittracker.feature.activity.testutil.activityObserveUseCase
import com.threemdroid.habittracker.feature.activity.testutil.activityTestEntry
import com.threemdroid.habittracker.feature.activity.testutil.activityTestHabit
import java.time.DayOfWeek
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ObserveActivityDataUseCaseTest {
    @Test
    fun invoke_forAllHabits_buildsWeeklyChartReadyAnalytics() = runTest {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(
                listOf(
                    activityTestHabit(
                        id = "habit-a",
                        name = "Walk",
                        type = HabitType.QUANTITY,
                        targetValue = 2.0,
                        selectedDays = emptySet(),
                    ),
                    activityTestHabit(
                        id = "habit-b",
                        name = "Read",
                        type = HabitType.BOOLEAN,
                        targetValue = 1.0,
                        selectedDays = setOf(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
                    ),
                ),
            )
            setEntries(
                listOf(
                    activityTestEntry(
                        id = "entry-1",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE.minusDays(1),
                        value = 2.0,
                    ),
                    activityTestEntry(
                        id = "entry-2",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE,
                        value = 1.0,
                    ),
                    activityTestEntry(
                        id = "entry-3",
                        habitId = "habit-b",
                        entryDate = ACTIVITY_TEST_DATE,
                        value = 1.0,
                    ),
                ),
            )
        }

        val result = activityObserveUseCase(repository).invoke(
            period = ActivityPeriod.WEEKLY,
            anchorDate = ACTIVITY_TEST_DATE,
            selectedHabitId = null,
        ).first()

        val snapshot = result.assertSuccess()
        assertEquals(ACTIVITY_TEST_DATE.minusDays(1), snapshot.periodStartDate)
        assertEquals(ACTIVITY_TEST_DATE.plusDays(5), snapshot.periodEndDate)
        assertEquals(listOf("All Habits", "Walk", "Read"), snapshot.availableHabitFilters.map { it.name })
        assertEquals(7, snapshot.chartData.size)
        assertEquals(4.0, snapshot.analyticsSummary.totalProgress, 0.0)
        assertEquals(2.5 / 9.0, snapshot.analyticsSummary.averageCompletionRatio, 0.0001)
        assertEquals(1, snapshot.analyticsSummary.doneDays)

        val mondayPoint = snapshot.chartData.first { chartPoint ->
            chartPoint.date == ACTIVITY_TEST_DATE.minusDays(1)
        }
        assertEquals(2.0, mondayPoint.totalProgress, 0.0)
        assertEquals(1.0, mondayPoint.completionRatio, 0.0)
        assertTrue(mondayPoint.isDoneDay)

        val tuesdayPoint = snapshot.chartData.first { chartPoint ->
            chartPoint.date == ACTIVITY_TEST_DATE
        }
        assertEquals(2.0, tuesdayPoint.totalProgress, 0.0)
        assertEquals(0.75, tuesdayPoint.completionRatio, 0.0)
        assertFalse(tuesdayPoint.isDoneDay)
    }

    @Test
    fun invoke_emptySelectedDays_treatsHabitAsScheduledEveryDay() = runTest {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(
                listOf(
                    activityTestHabit(
                        id = "habit-a",
                        name = "Walk",
                        type = HabitType.BOOLEAN,
                        targetValue = 1.0,
                        selectedDays = emptySet(),
                    ),
                ),
            )
            setEntries(
                listOf(
                    activityTestEntry(
                        id = "entry-1",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE,
                        value = 1.0,
                    ),
                ),
            )
        }

        val result = activityObserveUseCase(repository).invoke(
            period = ActivityPeriod.DAILY,
            anchorDate = ACTIVITY_TEST_DATE,
            selectedHabitId = null,
        ).first()

        val snapshot = result.assertSuccess()
        val chartPoint = snapshot.chartData.single()
        assertEquals(1.0, chartPoint.totalProgress, 0.0)
        assertEquals(1.0, chartPoint.completionRatio, 0.0)
        assertTrue(chartPoint.isDoneDay)
        assertEquals(1.0, snapshot.analyticsSummary.averageCompletionRatio, 0.0)
        assertEquals(1, snapshot.analyticsSummary.doneDays)
    }

    @Test
    fun invoke_createdAtHistoricalGating_limitsScheduledDaysBeforeHabitExists() = runTest {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(
                listOf(
                    activityTestHabit(
                        id = "habit-a",
                        name = "Walk",
                        type = HabitType.BOOLEAN,
                        targetValue = 1.0,
                        selectedDays = emptySet(),
                        createdAt = ACTIVITY_TEST_INSTANT.plusSeconds(2 * 86_400),
                    ),
                ),
            )
            setEntries(
                listOf(
                    activityTestEntry(
                        id = "entry-1",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE.plusDays(3),
                        value = 1.0,
                    ),
                ),
            )
        }

        val result = activityObserveUseCase(repository).invoke(
            period = ActivityPeriod.WEEKLY,
            anchorDate = ACTIVITY_TEST_DATE,
            selectedHabitId = null,
        ).first()

        val snapshot = result.assertSuccess()
        assertEquals(0.25, snapshot.analyticsSummary.averageCompletionRatio, 0.0)
        assertEquals(1, snapshot.analyticsSummary.doneDays)

        val createdDatePoint = snapshot.chartData.first { chartPoint ->
            chartPoint.date == ACTIVITY_TEST_DATE.plusDays(2)
        }
        assertEquals(0.0, createdDatePoint.totalProgress, 0.0)
        assertEquals(0.0, createdDatePoint.completionRatio, 0.0)

        val completedDayPoint = snapshot.chartData.first { chartPoint ->
            chartPoint.date == ACTIVITY_TEST_DATE.plusDays(3)
        }
        assertEquals(1.0, completedDayPoint.totalProgress, 0.0)
        assertEquals(1.0, completedDayPoint.completionRatio, 0.0)
        assertTrue(completedDayPoint.isDoneDay)
    }

    @Test
    fun invoke_stoppedAtHistoricalGating_limitsScheduledDaysAfterHabitStops() = runTest {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(
                listOf(
                    activityTestHabit(
                        id = "habit-a",
                        name = "Walk",
                        type = HabitType.BOOLEAN,
                        targetValue = 1.0,
                        selectedDays = emptySet(),
                        stoppedAt = ACTIVITY_TEST_INSTANT.plusSeconds(2 * 86_400),
                    ),
                ),
            )
            setEntries(
                listOf(
                    activityTestEntry(
                        id = "entry-1",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE.minusDays(1),
                        value = 1.0,
                    ),
                ),
            )
        }

        val result = activityObserveUseCase(repository).invoke(
            period = ActivityPeriod.WEEKLY,
            anchorDate = ACTIVITY_TEST_DATE,
            selectedHabitId = null,
        ).first()

        val snapshot = result.assertSuccess()
        assertEquals(0.25, snapshot.analyticsSummary.averageCompletionRatio, 0.0)
        assertEquals(1, snapshot.analyticsSummary.doneDays)

        val completedDayPoint = snapshot.chartData.first { chartPoint ->
            chartPoint.date == ACTIVITY_TEST_DATE.minusDays(1)
        }
        assertEquals(1.0, completedDayPoint.totalProgress, 0.0)
        assertEquals(1.0, completedDayPoint.completionRatio, 0.0)
        assertTrue(completedDayPoint.isDoneDay)

        val afterStoppedDayPoint = snapshot.chartData.first { chartPoint ->
            chartPoint.date == ACTIVITY_TEST_DATE.plusDays(3)
        }
        assertEquals(0.0, afterStoppedDayPoint.totalProgress, 0.0)
        assertEquals(0.0, afterStoppedDayPoint.completionRatio, 0.0)
        assertFalse(afterStoppedDayPoint.isDoneDay)
    }

    @Test
    fun invoke_forSelectedHabit_filtersAnalyticsToThatHabit() = runTest {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(
                listOf(
                    activityTestHabit(
                        id = "habit-a",
                        name = "Walk",
                        targetValue = 2.0,
                        selectedDays = emptySet(),
                    ),
                    activityTestHabit(
                        id = "habit-b",
                        name = "Read",
                        type = HabitType.BOOLEAN,
                        targetValue = 1.0,
                        selectedDays = setOf(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
                    ),
                ),
            )
            setEntries(
                listOf(
                    activityTestEntry(
                        id = "entry-1",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE.minusDays(1),
                        value = 2.0,
                    ),
                    activityTestEntry(
                        id = "entry-2",
                        habitId = "habit-b",
                        entryDate = ACTIVITY_TEST_DATE,
                        value = 1.0,
                    ),
                ),
            )
        }

        val result = activityObserveUseCase(repository).invoke(
            period = ActivityPeriod.WEEKLY,
            anchorDate = ACTIVITY_TEST_DATE,
            selectedHabitId = "habit-b",
        ).first()

        val snapshot = result.assertSuccess()
        assertEquals("habit-b", snapshot.selectedHabitId)
        assertEquals(1.0, snapshot.analyticsSummary.totalProgress, 0.0)
        assertEquals(0.5, snapshot.analyticsSummary.averageCompletionRatio, 0.0)
        assertEquals(1, snapshot.analyticsSummary.doneDays)

        val tuesdayPoint = snapshot.chartData.first { chartPoint ->
            chartPoint.date == ACTIVITY_TEST_DATE
        }
        assertEquals(1.0, tuesdayPoint.totalProgress, 0.0)
        assertEquals(1.0, tuesdayPoint.completionRatio, 0.0)
        assertTrue(tuesdayPoint.isDoneDay)
    }

    @Test
    fun invoke_whenHabitEntriesFail_returnsFailure() = runTest {
        val expectedError = AppError.Storage(
            source = "entries",
            message = "Activity entry query failed.",
        )
        val repository = TestActivityHabitsRepository().apply {
            setHabits(listOf(activityTestHabit(id = "habit-a")))
            setRangeEntryError(habitId = "habit-a", error = expectedError)
        }

        val result = activityObserveUseCase(repository).invoke(
            period = ActivityPeriod.MONTHLY,
            anchorDate = ACTIVITY_TEST_DATE,
            selectedHabitId = null,
        ).first()

        assertEquals(AppResult.Failure(expectedError), result)
    }

    private fun AppResult<ActivitySnapshot>.assertSuccess(): ActivitySnapshot = when (this) {
        is AppResult.Success -> value
        is AppResult.Failure -> throw AssertionError("Expected success but was $this")
    }
}
