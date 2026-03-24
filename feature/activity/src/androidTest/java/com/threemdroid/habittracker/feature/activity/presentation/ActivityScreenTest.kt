package com.threemdroid.habittracker.feature.activity.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ActivityScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun contentState_rendersAnalyticsAndChart() {
        composeRule.setContent {
            HabitTrackerTheme {
                ActivityScreen(
                    uiState = contentState(),
                    onIntent = {},
                )
            }
        }

        composeRule.onNodeWithTag(ActivityUiTestTags.ROOT).assertIsDisplayed()
        composeRule.onNodeWithTag(ActivityUiTestTags.PERIOD_HEADER).assertIsDisplayed()
        composeRule.onNodeWithTag(ActivityUiTestTags.ANALYTICS_CARD).assertIsDisplayed()
        composeRule.onNodeWithTag(ActivityUiTestTags.CHART_CARD).assertIsDisplayed()
    }

    @Test
    fun periodTabsAndNavigationControls_emitIntents() {
        val intents = mutableListOf<ActivityIntent>()

        composeRule.setContent {
            HabitTrackerTheme {
                ActivityScreen(
                    uiState = contentState(),
                    onIntent = { intent -> intents += intent },
                )
            }
        }

        composeRule.onNodeWithText("Monthly").performClick()
        composeRule.onNodeWithTag(ActivityUiTestTags.PREVIOUS_PERIOD_BUTTON).performClick()
        composeRule.onNodeWithTag(ActivityUiTestTags.NEXT_PERIOD_BUTTON).performClick()

        assertEquals(
            listOf(
                ActivityIntent.PeriodSelected(ActivityPeriod.MONTHLY),
                ActivityIntent.PreviousPeriodRequested,
                ActivityIntent.NextPeriodRequested,
            ),
            intents,
        )
    }

    @Test
    fun filterSheet_openAndSelectHabit_emitsFilterIntent() {
        var lastIntent: ActivityIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                ActivityScreen(
                    uiState = contentState(),
                    onIntent = { intent -> lastIntent = intent },
                )
            }
        }

        composeRule.onNodeWithTag(ActivityUiTestTags.FILTER_BUTTON).performClick()
        composeRule.onNodeWithTag(ActivityUiTestTags.FILTER_SHEET).assertIsDisplayed()
        composeRule.onNodeWithTag(ActivityUiTestTags.filterOption("habit-read")).performClick()

        assertEquals(ActivityIntent.HabitFilterSelected("habit-read"), lastIntent)
    }

    @Test
    fun errorState_retryEmitsIntent() {
        var lastIntent: ActivityIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                ActivityScreen(
                    uiState = errorState(),
                    onIntent = { intent -> lastIntent = intent },
                )
            }
        }

        composeRule.onNodeWithTag(ActivityUiTestTags.RETRY_BUTTON).performClick()

        assertEquals(ActivityIntent.RetryLoad, lastIntent)
    }
}

private fun contentState(): ActivityUiState {
    val anchorDate = LocalDate.of(2026, 3, 24)
    return ActivityUiState(
        selectedPeriod = ActivityPeriod.WEEKLY,
        periodAnchorDate = anchorDate,
        periodStartDate = anchorDate.minusDays(1),
        periodEndDate = anchorDate.plusDays(5),
        selectedHabitId = null,
        availableHabitFilters = listOf(
            ActivityHabitFilterOption(habitId = null, name = "All Habits"),
            ActivityHabitFilterOption(habitId = "habit-walk", name = "Walk"),
            ActivityHabitFilterOption(habitId = "habit-read", name = "Read"),
        ),
        chartData = listOf(
            ActivityChartPoint(
                date = anchorDate.minusDays(1),
                totalProgress = 2.0,
                completionRatio = 1.0,
                isDoneDay = true,
            ),
            ActivityChartPoint(
                date = anchorDate,
                totalProgress = 1.5,
                completionRatio = 0.75,
                isDoneDay = false,
            ),
            ActivityChartPoint(
                date = anchorDate.plusDays(1),
                totalProgress = 0.0,
                completionRatio = 0.0,
                isDoneDay = false,
            ),
        ),
        analyticsSummary = ActivityAnalyticsSummary(
            averageCompletionRatio = 0.58,
            doneDays = 1,
            totalProgress = 3.5,
        ),
        screenState = ActivityScreenState.Content,
    )
}

private fun errorState(): ActivityUiState = contentState().copy(
    chartData = emptyList(),
    analyticsSummary = ActivityAnalyticsSummary(),
    screenState = ActivityScreenState.Error(
        error = com.threemdroid.habittracker.feature.activity.contract.ActivityLoadError.GENERIC,
    ),
)
