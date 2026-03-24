package com.threemdroid.habittracker.feature.activity.contract

import java.time.LocalDate

enum class ActivityPeriod {
    DAILY,
    WEEKLY,
    MONTHLY,
}

enum class ActivityLoadError {
    GENERIC,
}

data class ActivityHabitFilterOption(
    val habitId: String?,
    val name: String,
)

data class ActivityChartPoint(
    val date: LocalDate,
    val totalProgress: Double,
    val completionRatio: Double,
    val isDoneDay: Boolean,
)

data class ActivityAnalyticsSummary(
    val averageCompletionRatio: Double = 0.0,
    val doneDays: Int = 0,
    val totalProgress: Double = 0.0,
)

sealed interface ActivityScreenState {
    data object Loading : ActivityScreenState

    data object Content : ActivityScreenState

    data object Empty : ActivityScreenState

    data class Error(
        val error: ActivityLoadError,
    ) : ActivityScreenState
}

data class ActivityUiState(
    val selectedPeriod: ActivityPeriod,
    val periodAnchorDate: LocalDate,
    val periodStartDate: LocalDate,
    val periodEndDate: LocalDate,
    val selectedHabitId: String?,
    val availableHabitFilters: List<ActivityHabitFilterOption>,
    val chartData: List<ActivityChartPoint>,
    val analyticsSummary: ActivityAnalyticsSummary,
    val screenState: ActivityScreenState,
) {
    companion object {
        fun initial(
            selectedPeriod: ActivityPeriod,
            periodAnchorDate: LocalDate,
            periodStartDate: LocalDate,
            periodEndDate: LocalDate,
        ): ActivityUiState = ActivityUiState(
            selectedPeriod = selectedPeriod,
            periodAnchorDate = periodAnchorDate,
            periodStartDate = periodStartDate,
            periodEndDate = periodEndDate,
            selectedHabitId = null,
            availableHabitFilters = emptyList(),
            chartData = emptyList(),
            analyticsSummary = ActivityAnalyticsSummary(),
            screenState = ActivityScreenState.Loading,
        )
    }
}

sealed interface ActivityIntent {
    data class PeriodSelected(
        val period: ActivityPeriod,
    ) : ActivityIntent

    data object PreviousPeriodRequested : ActivityIntent

    data object NextPeriodRequested : ActivityIntent

    data class HabitFilterSelected(
        val habitId: String?,
    ) : ActivityIntent

    data object RetryLoad : ActivityIntent
}

sealed interface ActivityEffect
