package com.threemdroid.habittracker.feature.activity.presentation

import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary
import com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState
import com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot
import java.time.LocalDate

internal object ActivityReducer {
    fun reduce(
        currentState: ActivityUiState,
        result: ActivityResult,
    ): ActivityUiState = when (result) {
        is ActivityResult.ConfigurationChanged -> currentState.copy(
            selectedPeriod = result.period,
            periodAnchorDate = result.periodAnchorDate,
            periodStartDate = result.periodStartDate,
            periodEndDate = result.periodEndDate,
            selectedHabitId = result.selectedHabitId,
            chartData = emptyList(),
            analyticsSummary = ActivityAnalyticsSummary(),
            screenState = ActivityScreenState.Loading,
        )

        ActivityResult.ActivityDataLoading -> currentState.copy(
            chartData = emptyList(),
            analyticsSummary = ActivityAnalyticsSummary(),
            screenState = ActivityScreenState.Loading,
        )

        is ActivityResult.ActivityDataLoaded -> currentState.copy(
            selectedPeriod = result.snapshot.selectedPeriod,
            periodAnchorDate = result.snapshot.periodAnchorDate,
            periodStartDate = result.snapshot.periodStartDate,
            periodEndDate = result.snapshot.periodEndDate,
            selectedHabitId = result.snapshot.selectedHabitId,
            availableHabitFilters = result.snapshot.availableHabitFilters,
            chartData = result.snapshot.chartData,
            analyticsSummary = result.snapshot.analyticsSummary,
            screenState = result.snapshot.toScreenState(),
        )

        ActivityResult.ActivityDataLoadFailed -> currentState.copy(
            chartData = emptyList(),
            analyticsSummary = ActivityAnalyticsSummary(),
            screenState = ActivityScreenState.Error(ActivityLoadError.GENERIC),
        )
    }
}

internal sealed interface ActivityResult {
    data class ConfigurationChanged(
        val period: ActivityPeriod,
        val periodAnchorDate: LocalDate,
        val periodStartDate: LocalDate,
        val periodEndDate: LocalDate,
        val selectedHabitId: String?,
    ) : ActivityResult

    data object ActivityDataLoading : ActivityResult

    data class ActivityDataLoaded(
        val snapshot: ActivitySnapshot,
    ) : ActivityResult

    data object ActivityDataLoadFailed : ActivityResult
}

private fun ActivitySnapshot.toScreenState(): ActivityScreenState =
    if (availableHabitFilters.isEmpty()) {
        ActivityScreenState.Empty
    } else {
        ActivityScreenState.Content
    }
