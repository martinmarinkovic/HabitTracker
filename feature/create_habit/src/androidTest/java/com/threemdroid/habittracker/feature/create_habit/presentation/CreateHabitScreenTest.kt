package com.threemdroid.habittracker.feature.create_habit.presentation

import androidx.activity.ComponentActivity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitIntent
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitUiState
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitValidationState
import java.time.DayOfWeek
import java.time.LocalTime
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CreateHabitScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun mainForm_rendersCoreSectionsAndCta() {
        composeRule.setContent {
            HabitTrackerTheme {
                CreateHabitScreen(
                    uiState = createHabitUiState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = {},
                    onBackRequested = {},
                )
            }
        }

        composeRule.onNodeWithTag(CreateHabitUiTestTags.ROOT).assertIsDisplayed()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.NAME_FIELD).assertIsDisplayed()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.ICON_CARD).assertIsDisplayed()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.COLOR_CARD).assertIsDisplayed()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.GOAL_CARD).assertIsDisplayed()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.ADD_HABIT_BUTTON).assertIsDisplayed()
    }

    @Test
    fun goalCardTap_opensGoalSheetAndEmitsHabitTypeIntent() {
        var lastIntent: CreateHabitIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                CreateHabitScreen(
                    uiState = createHabitUiState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onBackRequested = {},
                )
            }
        }

        composeRule.onNodeWithTag(CreateHabitUiTestTags.GOAL_CARD).performClick()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.GOAL_SHEET).assertIsDisplayed()
        composeRule.onNodeWithText("Quantity").performClick()

        assertEquals(CreateHabitIntent.HabitTypeSelected(HabitType.QUANTITY), lastIntent)
    }

    @Test
    fun iconPicker_searchAndSelection_work() {
        var lastIntent: CreateHabitIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                CreateHabitScreen(
                    uiState = createHabitUiState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onBackRequested = {},
                )
            }
        }

        composeRule.onNodeWithTag(CreateHabitUiTestTags.ICON_CARD).performClick()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.ICON_PICKER).assertIsDisplayed()
        composeRule.onNode(
            hasSetTextAction().and(hasAnyAncestor(hasTestTag(CreateHabitUiTestTags.ICON_PICKER))),
        ).performTextInput("wat")
        composeRule.onNodeWithText("Water").assertIsDisplayed()
        composeRule.onAllNodesWithText("Walk").assertCountEquals(0)
        composeRule.onNodeWithTag(CreateHabitUiTestTags.iconOption("drop")).performClick()

        assertEquals(CreateHabitIntent.IconSelected("drop"), lastIntent)
    }

    @Test
    fun colorPicker_searchAndSelection_work() {
        var lastIntent: CreateHabitIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                CreateHabitScreen(
                    uiState = createHabitUiState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onBackRequested = {},
                )
            }
        }

        composeRule.onNodeWithTag(CreateHabitUiTestTags.COLOR_CARD).performClick()
        composeRule.onNodeWithTag(CreateHabitUiTestTags.COLOR_PICKER).assertIsDisplayed()
        composeRule.onNode(
            hasSetTextAction().and(hasAnyAncestor(hasTestTag(CreateHabitUiTestTags.COLOR_PICKER))),
        ).performTextInput("red")
        composeRule.onNodeWithText("Red").assertIsDisplayed()
        composeRule.onAllNodesWithText("Blue").assertCountEquals(0)
        composeRule.onNodeWithTag(CreateHabitUiTestTags.colorOption("red")).performClick()

        assertEquals(CreateHabitIntent.ColorSelected("red"), lastIntent)
    }

    @Test
    fun addReminderCard_emitsReminderAddedIntent() {
        var lastIntent: CreateHabitIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                CreateHabitScreen(
                    uiState = createHabitUiState(reminders = emptyList()),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onBackRequested = {},
                )
            }
        }

        composeRule.onNodeWithTag(CreateHabitUiTestTags.ADD_REMINDER_BUTTON).performClick()

        assertEquals(CreateHabitIntent.ReminderAdded, lastIntent)
    }

    @Test
    fun addHabitButton_emitsSaveRequestedIntent() {
        var lastIntent: CreateHabitIntent? = null

        composeRule.setContent {
            HabitTrackerTheme {
                CreateHabitScreen(
                    uiState = createHabitUiState(),
                    snackbarHostState = remember { SnackbarHostState() },
                    onIntent = { intent -> lastIntent = intent },
                    onBackRequested = {},
                )
            }
        }

        composeRule.onNodeWithTag(CreateHabitUiTestTags.ADD_HABIT_BUTTON).performClick()

        assertEquals(CreateHabitIntent.SaveRequested, lastIntent)
    }
}

private fun createHabitUiState(
    reminders: List<CreateHabitReminderItem> = listOf(
        CreateHabitReminderItem(
            reminderId = "reminder-1",
            time = LocalTime.of(9, 0),
            isEnabled = true,
        ),
    ),
): CreateHabitUiState = CreateHabitUiState(
    nameInput = "Read",
    selectedIconToken = "book",
    selectedColorToken = "blue",
    habitType = HabitType.BOOLEAN,
    targetValueInput = "1",
    defaultIncrementInput = "1",
    unitLabelInput = "",
    allowsMultipleUpdatesPerDay = false,
    schedulePreset = CreateHabitSchedulePreset.EVERY_DAY,
    selectedDays = DayOfWeek.values().toSet(),
    reminders = reminders,
    validation = CreateHabitValidationState(),
    isSaving = false,
)
