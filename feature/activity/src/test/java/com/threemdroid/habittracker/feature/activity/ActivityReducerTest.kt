package com.threemdroid.habittracker.feature.activity

import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState
import com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot
import com.threemdroid.habittracker.feature.activity.domain.windowFor
import com.threemdroid.habittracker.feature.activity.presentation.ActivityReducer
import com.threemdroid.habittracker.feature.activity.presentation.ActivityResult
import com.threemdroid.habittracker.feature.activity.testutil.ACTIVITY_TEST_DATE
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ActivityReducerTest {
    private val initialWindow = ActivityPeriod.WEEKLY.windowFor(ACTIVITY_TEST_DATE)

    @Test
    fun reduce_configurationChanged_setsLoadingAndClearsAnalytics() {
        val currentState = ActivityUiState.initial(
            selectedPeriod = ActivityPeriod.WEEKLY,
            periodAnchorDate = ACTIVITY_TEST_DATE,
            periodStartDate = initialWindow.startDate,
            periodEndDate = initialWindow.endDate,
        ).copy(
            selectedHabitId = "habit-a",
            availableHabitFilters = listOf(ActivityHabitFilterOption(null, "All Habits")),
            chartData = listOf(
                ActivityChartPoint(
                    date = ACTIVITY_TEST_DATE,
                    totalProgress = 2.0,
                    completionRatio = 1.0,
                    isDoneDay = true,
                ),
            ),
            analyticsSummary = ActivityAnalyticsSummary(
                averageCompletionRatio = 1.0,
                doneDays = 1,
                totalProgress = 2.0,
            ),
            screenState = ActivityScreenState.Content,
        )

        val monthlyWindow = ActivityPeriod.MONTHLY.windowFor(ACTIVITY_TEST_DATE)
        val updatedState = ActivityReducer.reduce(
            currentState = currentState,
            result = ActivityResult.ConfigurationChanged(
                period = ActivityPeriod.MONTHLY,
                periodAnchorDate = ACTIVITY_TEST_DATE,
                periodStartDate = monthlyWindow.startDate,
                periodEndDate = monthlyWindow.endDate,
                selectedHabitId = null,
            ),
        )

        assertEquals(ActivityPeriod.MONTHLY, updatedState.selectedPeriod)
        assertEquals(ActivityScreenState.Loading, updatedState.screenState)
        assertTrue(updatedState.chartData.isEmpty())
        assertEquals(0.0, updatedState.analyticsSummary.totalProgress, 0.0)
        assertEquals(null, updatedState.selectedHabitId)
    }

    @Test
    fun reduce_activityDataLoaded_withoutHabits_setsEmptyState() {
        val updatedState = ActivityReducer.reduce(
            currentState = ActivityUiState.initial(
                selectedPeriod = ActivityPeriod.DAILY,
                periodAnchorDate = ACTIVITY_TEST_DATE,
                periodStartDate = ACTIVITY_TEST_DATE,
                periodEndDate = ACTIVITY_TEST_DATE,
            ),
            result = ActivityResult.ActivityDataLoaded(
                snapshot = ActivitySnapshot(
                    selectedPeriod = ActivityPeriod.DAILY,
                    periodAnchorDate = ACTIVITY_TEST_DATE,
                    periodStartDate = ACTIVITY_TEST_DATE,
                    periodEndDate = ACTIVITY_TEST_DATE,
                    selectedHabitId = null,
                    availableHabitFilters = emptyList(),
                    chartData = emptyList(),
                    analyticsSummary = ActivityAnalyticsSummary(),
                ),
            ),
        )

        assertEquals(ActivityScreenState.Empty, updatedState.screenState)
    }

    @Test
    fun reduce_activityDataLoadFailed_setsErrorState() {
        val updatedState = ActivityReducer.reduce(
            currentState = ActivityUiState.initial(
                selectedPeriod = ActivityPeriod.WEEKLY,
                periodAnchorDate = ACTIVITY_TEST_DATE,
                periodStartDate = initialWindow.startDate,
                periodEndDate = initialWindow.endDate,
            ),
            result = ActivityResult.ActivityDataLoadFailed,
        )

        assertTrue(updatedState.screenState is ActivityScreenState.Error)
        assertTrue(updatedState.chartData.isEmpty())
    }
}
