package com.threemdroid.habittracker.feature.home.contract

import com.threemdroid.habittracker.core.model.habits.HabitType
import java.time.LocalDate

enum class HomeGreetingTimeOfDay {
    MORNING,
    AFTERNOON,
    EVENING,
}

data class HomeGreetingState(
    val timeOfDay: HomeGreetingTimeOfDay,
    val isSelectedDateToday: Boolean,
)

enum class HomeLoadError {
    GENERIC,
}

enum class HomeActionError {
    HABIT_PROGRESS_SAVE_FAILED,
    MOOD_SAVE_FAILED,
    STOP_HABIT_FAILED,
}

enum class QuickAddValidationError {
    INVALID_NUMBER,
    NON_POSITIVE,
}

data class HomeQuickAddOverlayState(
    val isVisible: Boolean = false,
    val habitId: String? = null,
    val habitName: String? = null,
    val draftValue: String = "",
    val validationError: QuickAddValidationError? = null,
) {
    companion object {
        fun hidden(): HomeQuickAddOverlayState = HomeQuickAddOverlayState()
    }
}

data class HomeMoodSelectionState(
    val selectedMoodToken: String? = null,
)

data class HomeHabitItem(
    val habitId: String,
    val name: String,
    val type: HabitType,
    val progressValue: Double,
    val targetValue: Double,
    val defaultIncrement: Double,
    val unitLabel: String?,
    val allowsMultipleUpdatesPerDay: Boolean,
    val selectedIconToken: String,
    val selectedColorToken: String,
    val isCompleted: Boolean,
)

sealed interface HomeScreenState {
    data object Loading : HomeScreenState

    data object Content : HomeScreenState

    data object Empty : HomeScreenState

    data class Error(
        val error: HomeLoadError,
    ) : HomeScreenState
}

data class HomeUiState(
    val greetingAreaState: HomeGreetingState,
    val selectedDate: LocalDate,
    val screenState: HomeScreenState,
    val habitsToday: List<HomeHabitItem>,
    val quickAddOverlayState: HomeQuickAddOverlayState,
    val moodSelectionState: HomeMoodSelectionState,
) {
    companion object {
        fun initial(
            selectedDate: LocalDate,
            greetingAreaState: HomeGreetingState,
        ): HomeUiState = HomeUiState(
            greetingAreaState = greetingAreaState,
            selectedDate = selectedDate,
            screenState = HomeScreenState.Loading,
            habitsToday = emptyList(),
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
            moodSelectionState = HomeMoodSelectionState(),
        )
    }
}

sealed interface HomeIntent {
    data class SelectDate(
        val date: LocalDate,
    ) : HomeIntent

    data class BooleanHabitTapped(
        val habitId: String,
    ) : HomeIntent

    data class QuantityHabitTapped(
        val habitId: String,
    ) : HomeIntent

    data class QuickAddValueChanged(
        val value: String,
    ) : HomeIntent

    data object ConfirmQuickAdd : HomeIntent

    data object DismissQuickAdd : HomeIntent

    data class MoodSelected(
        val moodToken: String,
    ) : HomeIntent

    data class EditHabitRequested(
        val habitId: String,
    ) : HomeIntent

    data class StopHabitRequested(
        val habitId: String,
    ) : HomeIntent

    data object RetryLoad : HomeIntent
}

sealed interface HomeEffect {
    data class NavigateToEditHabit(
        val habitId: String,
    ) : HomeEffect

    data class ShowActionError(
        val error: HomeActionError,
    ) : HomeEffect
}
