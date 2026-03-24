package com.threemdroid.habittracker.feature.create_habit.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitUiState
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitNameValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitValidationState
import java.time.DayOfWeek
import java.time.LocalTime

internal fun createHabitPreviewState(): CreateHabitUiState = CreateHabitUiState(
    nameInput = "Read",
    selectedIconToken = "book",
    selectedColorToken = "blue",
    habitType = HabitType.QUANTITY,
    targetValueInput = "30",
    defaultIncrementInput = "5",
    unitLabelInput = "pages",
    allowsMultipleUpdatesPerDay = false,
    schedulePreset = CreateHabitSchedulePreset.WEEKDAYS,
    selectedDays = setOf(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
    ),
    reminders = listOf(
        CreateHabitReminderItem(
            reminderId = "reminder-1",
            time = LocalTime.of(9, 0),
            isEnabled = true,
        ),
    ),
    validation = CreateHabitValidationState(),
    isSaving = false,
)

internal fun createHabitValidationPreviewState(): CreateHabitUiState = CreateHabitUiState(
    nameInput = "",
    selectedIconToken = null,
    selectedColorToken = null,
    habitType = HabitType.BOOLEAN,
    targetValueInput = "1",
    defaultIncrementInput = "1",
    unitLabelInput = "",
    allowsMultipleUpdatesPerDay = false,
    schedulePreset = CreateHabitSchedulePreset.CUSTOM,
    selectedDays = emptySet(),
    reminders = emptyList(),
    validation = CreateHabitValidationState(
        nameError = CreateHabitNameValidationError.REQUIRED,
    ),
    isSaving = false,
)

@Preview(showBackground = true, widthDp = 393, heightDp = 852)
@Composable
private fun CreateHabitScreenPreview() {
    HabitTrackerTheme {
        CreateHabitScreen(
            uiState = createHabitPreviewState(),
            snackbarHostState = remember { SnackbarHostState() },
            onIntent = {},
            onBackRequested = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 852)
@Composable
private fun CreateHabitValidationScreenPreview() {
    HabitTrackerTheme {
        CreateHabitScreen(
            uiState = createHabitValidationPreviewState(),
            snackbarHostState = remember { SnackbarHostState() },
            onIntent = {},
            onBackRequested = {},
        )
    }
}
