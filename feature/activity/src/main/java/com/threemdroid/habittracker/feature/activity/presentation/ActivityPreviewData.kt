package com.threemdroid.habittracker.feature.activity.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState
import java.time.LocalDate

private val previewAnchorDate: LocalDate = LocalDate.of(2026, 3, 24)
private val previewStartDate: LocalDate = previewAnchorDate.minusDays(1)
private val previewEndDate: LocalDate = previewAnchorDate.plusDays(5)

private val previewChartData = listOf(
    ActivityChartPoint(
        date = previewAnchorDate.minusDays(1),
        totalProgress = 2.0,
        completionRatio = 1.0,
        isDoneDay = true,
    ),
    ActivityChartPoint(
        date = previewAnchorDate,
        totalProgress = 1.5,
        completionRatio = 0.75,
        isDoneDay = false,
    ),
    ActivityChartPoint(
        date = previewAnchorDate.plusDays(1),
        totalProgress = 0.0,
        completionRatio = 0.0,
        isDoneDay = false,
    ),
    ActivityChartPoint(
        date = previewAnchorDate.plusDays(2),
        totalProgress = 1.0,
        completionRatio = 0.5,
        isDoneDay = false,
    ),
    ActivityChartPoint(
        date = previewAnchorDate.plusDays(3),
        totalProgress = 2.0,
        completionRatio = 1.0,
        isDoneDay = true,
    ),
    ActivityChartPoint(
        date = previewAnchorDate.plusDays(4),
        totalProgress = 0.5,
        completionRatio = 0.25,
        isDoneDay = false,
    ),
    ActivityChartPoint(
        date = previewAnchorDate.plusDays(5),
        totalProgress = 1.0,
        completionRatio = 0.5,
        isDoneDay = false,
    ),
)

private val previewFilters = listOf(
    ActivityHabitFilterOption(habitId = null, name = "All Habits"),
    ActivityHabitFilterOption(habitId = "habit-walk", name = "Walk"),
    ActivityHabitFilterOption(habitId = "habit-read", name = "Read"),
)

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun ActivityContentPreview() {
    PreviewActivityScreen(
        uiState = previewUiState(
            screenState = ActivityScreenState.Content,
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun ActivityEmptyPreview() {
    PreviewActivityScreen(
        uiState = previewUiState(
            availableHabitFilters = emptyList(),
            chartData = emptyList(),
            analyticsSummary = ActivityAnalyticsSummary(),
            screenState = ActivityScreenState.Empty,
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun ActivityErrorPreview() {
    PreviewActivityScreen(
        uiState = previewUiState(
            availableHabitFilters = previewFilters,
            chartData = emptyList(),
            analyticsSummary = ActivityAnalyticsSummary(),
            screenState = ActivityScreenState.Error(
                error = com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError.GENERIC,
            ),
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun ActivityLoadingPreview() {
    PreviewActivityScreen(
        uiState = previewUiState(
            availableHabitFilters = previewFilters,
            chartData = emptyList(),
            analyticsSummary = ActivityAnalyticsSummary(),
            screenState = ActivityScreenState.Loading,
        ),
    )
}

@Composable
private fun PreviewActivityScreen(
    uiState: ActivityUiState,
) {
    HabitTrackerTheme {
        ActivityScreen(
            uiState = uiState,
            onIntent = {},
        )
    }
}

private fun previewUiState(
    availableHabitFilters: List<ActivityHabitFilterOption> = previewFilters,
    chartData: List<ActivityChartPoint> = previewChartData,
    analyticsSummary: ActivityAnalyticsSummary = ActivityAnalyticsSummary(
        averageCompletionRatio = 0.57,
        doneDays = 2,
        totalProgress = 8.0,
    ),
    screenState: ActivityScreenState,
): ActivityUiState = ActivityUiState(
    selectedPeriod = ActivityPeriod.WEEKLY,
    periodAnchorDate = previewAnchorDate,
    periodStartDate = previewStartDate,
    periodEndDate = previewEndDate,
    selectedHabitId = null,
    availableHabitFilters = availableHabitFilters,
    chartData = chartData,
    analyticsSummary = analyticsSummary,
    screenState = screenState,
)
