package com.threemdroid.habittracker.feature.home.presentation

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingState
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingTimeOfDay
import com.threemdroid.habittracker.feature.home.contract.HomeHabitItem
import com.threemdroid.habittracker.feature.home.contract.HomeIntent
import com.threemdroid.habittracker.feature.home.contract.HomeMoodSelectionState
import com.threemdroid.habittracker.feature.home.contract.HomeQuickAddOverlayState
import com.threemdroid.habittracker.feature.home.contract.HomeScreenState
import com.threemdroid.habittracker.feature.home.contract.HomeUiState
import com.threemdroid.habittracker.feature.home.contract.QuickAddValidationError
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun contentState_primaryActionEmitsBooleanHabitIntent() {
        var lastIntent: HomeIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                HomeScreen(
                    uiState = contentState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onCreateHabitRequested = {},
                    onNavigateToActivity = {},
                    onNavigateToLearn = {},
                    onNavigateToProfile = {},
                    canEditHabit = false,
                )
            }
        }

        composeRule.onNodeWithTag("home_habit_primary_action_habit-boolean").performClick()

        assertEquals(HomeIntent.BooleanHabitTapped("habit-boolean"), lastIntent)
    }

    @Test
    fun dateSelectorClick_emitsSelectDateIntent() {
        var lastIntent: HomeIntent? = null
        val nextDate = selectedDate.plusDays(1)

        composeRule.setContent {
            HabitTrackerTheme {
                HomeScreen(
                    uiState = contentState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onCreateHabitRequested = {},
                    onNavigateToActivity = {},
                    onNavigateToLearn = {},
                    onNavigateToProfile = {},
                    canEditHabit = false,
                )
            }
        }

        composeRule.onNodeWithTag("home_date_selector_$nextDate").performClick()

        assertEquals(HomeIntent.SelectDate(nextDate), lastIntent)
    }

    @Test
    fun manageSheetStopAction_emitsStopHabitIntent() {
        var lastIntent: HomeIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                HomeScreen(
                    uiState = contentState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onCreateHabitRequested = {},
                    onNavigateToActivity = {},
                    onNavigateToLearn = {},
                    onNavigateToProfile = {},
                    canEditHabit = false,
                )
            }
        }

        composeRule.onNodeWithTag("home_habit_manage_action_habit-boolean").performClick()
        composeRule.onNodeWithTag("home_context_sheet").assertIsDisplayed()
        composeRule.onNodeWithText("Stop Habit").performClick()

        assertEquals(HomeIntent.StopHabitRequested("habit-boolean"), lastIntent)
    }

    @Test
    fun errorStateRetryButton_emitsRetryIntent() {
        var lastIntent: HomeIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                HomeScreen(
                    uiState = errorState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onCreateHabitRequested = {},
                    onNavigateToActivity = {},
                    onNavigateToLearn = {},
                    onNavigateToProfile = {},
                    canEditHabit = false,
                )
            }
        }

        composeRule.onNodeWithTag("home_retry_button").performClick()

        assertEquals(HomeIntent.RetryLoad, lastIntent)
    }

    @Test
    fun floatingCreateButton_invokesCreateHabitCallback() {
        var createClicks = 0

        composeRule.setContent {
            HabitTrackerTheme {
                HomeScreen(
                    uiState = contentState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = {},
                    onCreateHabitRequested = { createClicks += 1 },
                    onNavigateToActivity = {},
                    onNavigateToLearn = {},
                    onNavigateToProfile = {},
                    canEditHabit = false,
                )
            }
        }

        composeRule.onNodeWithTag(HomeUiTestTags.FLOATING_CREATE_HABIT_BUTTON).performClick()

        assertEquals(1, createClicks)
    }

    @Test
    fun quickAddState_rendersValidationMessage() {
        composeRule.setContent {
            HabitTrackerTheme {
                HomeScreen(
                    uiState = quickAddState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = {},
                    onCreateHabitRequested = {},
                    onNavigateToActivity = {},
                    onNavigateToLearn = {},
                    onNavigateToProfile = {},
                    canEditHabit = false,
                )
            }
        }

        composeRule.onNodeWithTag("home_quick_add_sheet").assertIsDisplayed()
        composeRule.onNodeWithText("Enter a value greater than zero.").assertIsDisplayed()
    }

    @Test
    fun manageSheet_hidesEditActionWhenEditFlowUnavailable() {
        composeRule.setContent {
            HabitTrackerTheme {
                HomeScreen(
                    uiState = contentState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = {},
                    onCreateHabitRequested = {},
                    onNavigateToActivity = {},
                    onNavigateToLearn = {},
                    onNavigateToProfile = {},
                    canEditHabit = false,
                )
            }
        }

        composeRule.onNodeWithTag("home_habit_manage_action_habit-boolean").performClick()
        composeRule.onAllNodesWithText("Edit Habit").assertCountEquals(0)
    }

    private fun contentState(): HomeUiState = HomeUiState(
        greetingAreaState = HomeGreetingState(
            timeOfDay = HomeGreetingTimeOfDay.MORNING,
            isSelectedDateToday = true,
        ),
        selectedDate = selectedDate,
        screenState = HomeScreenState.Content,
        habitsToday = listOf(
            HomeHabitItem(
                habitId = "habit-boolean",
                name = "Walk",
                type = HabitType.BOOLEAN,
                progressValue = 0.0,
                targetValue = 1.0,
                defaultIncrement = 1.0,
                unitLabel = null,
                allowsMultipleUpdatesPerDay = false,
                selectedIconToken = "shoe",
                selectedColorToken = "green",
                isCompleted = false,
            ),
            HomeHabitItem(
                habitId = "habit-quantity",
                name = "Water",
                type = HabitType.QUANTITY,
                progressValue = 4.0,
                targetValue = 8.0,
                defaultIncrement = 1.0,
                unitLabel = "cups",
                allowsMultipleUpdatesPerDay = true,
                selectedIconToken = "drop",
                selectedColorToken = "blue",
                isCompleted = false,
            ),
        ),
        quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
        moodSelectionState = HomeMoodSelectionState(selectedMoodToken = "Focused"),
    )

    private fun errorState(): HomeUiState = HomeUiState(
        greetingAreaState = HomeGreetingState(
            timeOfDay = HomeGreetingTimeOfDay.MORNING,
            isSelectedDateToday = true,
        ),
        selectedDate = selectedDate,
        screenState = HomeScreenState.Error(
            error = com.threemdroid.habittracker.feature.home.contract.HomeLoadError.GENERIC,
        ),
        habitsToday = emptyList(),
        quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
        moodSelectionState = HomeMoodSelectionState(),
    )

    private fun quickAddState(): HomeUiState = contentState().copy(
        quickAddOverlayState = HomeQuickAddOverlayState(
            isVisible = true,
            habitId = "habit-quantity",
            habitName = "Water",
            draftValue = "0",
            validationError = QuickAddValidationError.NON_POSITIVE,
        ),
    )

    companion object {
        private val selectedDate: LocalDate = LocalDate.of(2026, 3, 24)
    }
}
