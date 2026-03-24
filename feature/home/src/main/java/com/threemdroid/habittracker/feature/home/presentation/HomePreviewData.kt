package com.threemdroid.habittracker.feature.home.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingState
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingTimeOfDay
import com.threemdroid.habittracker.feature.home.contract.HomeHabitItem
import com.threemdroid.habittracker.feature.home.contract.HomeMoodSelectionState
import com.threemdroid.habittracker.feature.home.contract.HomeQuickAddOverlayState
import com.threemdroid.habittracker.feature.home.contract.HomeScreenState
import com.threemdroid.habittracker.feature.home.contract.HomeUiState
import com.threemdroid.habittracker.feature.home.contract.QuickAddValidationError
import java.time.LocalDate

private val previewSelectedDate: LocalDate = LocalDate.of(2026, 3, 24)

private val previewGreetingState = HomeGreetingState(
    timeOfDay = HomeGreetingTimeOfDay.MORNING,
    isSelectedDateToday = true,
)

private val previewHabits = listOf(
    HomeHabitItem(
        habitId = "habit-1",
        name = "Walk",
        type = HabitType.BOOLEAN,
        progressValue = 1.0,
        targetValue = 1.0,
        defaultIncrement = 1.0,
        unitLabel = null,
        allowsMultipleUpdatesPerDay = false,
        selectedIconToken = "shoe",
        selectedColorToken = "green",
        isCompleted = true,
    ),
    HomeHabitItem(
        habitId = "habit-2",
        name = "Water",
        type = HabitType.QUANTITY,
        progressValue = 5.0,
        targetValue = 8.0,
        defaultIncrement = 1.0,
        unitLabel = "cups",
        allowsMultipleUpdatesPerDay = true,
        selectedIconToken = "drop",
        selectedColorToken = "blue",
        isCompleted = false,
    ),
)

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun HomeContentPreview() {
    PreviewHomeScreen(
        uiState = HomeUiState(
            greetingAreaState = previewGreetingState,
            selectedDate = previewSelectedDate,
            screenState = HomeScreenState.Content,
            habitsToday = previewHabits,
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
            moodSelectionState = HomeMoodSelectionState(selectedMoodToken = "Focused"),
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun HomeEmptyPreview() {
    PreviewHomeScreen(
        uiState = HomeUiState(
            greetingAreaState = previewGreetingState,
            selectedDate = previewSelectedDate,
            screenState = HomeScreenState.Empty,
            habitsToday = emptyList(),
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
            moodSelectionState = HomeMoodSelectionState(),
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun HomeErrorPreview() {
    PreviewHomeScreen(
        uiState = HomeUiState(
            greetingAreaState = previewGreetingState,
            selectedDate = previewSelectedDate,
            screenState = HomeScreenState.Error(
                error = com.threemdroid.habittracker.feature.home.contract.HomeLoadError.GENERIC,
            ),
            habitsToday = emptyList(),
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
            moodSelectionState = HomeMoodSelectionState(),
        ),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F5F7)
@Composable
private fun HomeQuickAddPreview() {
    PreviewHomeScreen(
        uiState = HomeUiState(
            greetingAreaState = previewGreetingState,
            selectedDate = previewSelectedDate,
            screenState = HomeScreenState.Content,
            habitsToday = previewHabits,
            quickAddOverlayState = HomeQuickAddOverlayState(
                isVisible = true,
                habitId = "habit-2",
                habitName = "Water",
                draftValue = "0",
                validationError = QuickAddValidationError.NON_POSITIVE,
            ),
            moodSelectionState = HomeMoodSelectionState(selectedMoodToken = "Focused"),
        ),
    )
}

@Composable
private fun PreviewHomeScreen(
    uiState: HomeUiState,
) {
    HabitTrackerTheme {
        HomeScreen(
            uiState = uiState,
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
