package com.threemdroid.habittracker.feature.home.presentation

import com.threemdroid.habittracker.feature.home.contract.HomeGreetingState
import com.threemdroid.habittracker.feature.home.contract.HomeLoadError
import com.threemdroid.habittracker.feature.home.contract.HomeMoodSelectionState
import com.threemdroid.habittracker.feature.home.contract.HomeQuickAddOverlayState
import com.threemdroid.habittracker.feature.home.contract.HomeScreenState
import com.threemdroid.habittracker.feature.home.contract.HomeUiState
import com.threemdroid.habittracker.feature.home.contract.QuickAddValidationError
import com.threemdroid.habittracker.feature.home.domain.HomeSnapshot
import java.time.LocalDate

internal object HomeReducer {
    fun reduce(
        currentState: HomeUiState,
        result: HomeResult,
    ): HomeUiState = when (result) {
        is HomeResult.SelectedDateChanged -> currentState.copy(
            selectedDate = result.date,
            greetingAreaState = result.greetingAreaState,
            screenState = HomeScreenState.Loading,
            habitsToday = emptyList(),
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
            moodSelectionState = HomeMoodSelectionState(),
        )

        HomeResult.HomeDataLoading -> currentState.copy(
            screenState = HomeScreenState.Loading,
            habitsToday = emptyList(),
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
            moodSelectionState = HomeMoodSelectionState(),
        )

        is HomeResult.HomeDataLoaded -> currentState.copy(
            screenState = result.snapshot.habitsToday.toHomeScreenState(),
            habitsToday = result.snapshot.habitsToday,
            moodSelectionState = HomeMoodSelectionState(
                selectedMoodToken = result.snapshot.selectedMoodToken,
            ),
        )

        HomeResult.HomeDataLoadFailed -> currentState.copy(
            screenState = HomeScreenState.Error(HomeLoadError.GENERIC),
            habitsToday = emptyList(),
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
            moodSelectionState = HomeMoodSelectionState(),
        )

        is HomeResult.QuickAddOpened -> currentState.copy(
            quickAddOverlayState = HomeQuickAddOverlayState(
                isVisible = true,
                habitId = result.habitId,
                habitName = result.habitName,
                draftValue = result.draftValue,
            ),
        )

        HomeResult.QuickAddDismissed -> currentState.copy(
            quickAddOverlayState = HomeQuickAddOverlayState.hidden(),
        )

        is HomeResult.QuickAddValueChanged -> currentState.copy(
            quickAddOverlayState = currentState.quickAddOverlayState.copy(
                draftValue = result.value,
                validationError = null,
            ),
        )

        is HomeResult.QuickAddValidationFailed -> currentState.copy(
            quickAddOverlayState = currentState.quickAddOverlayState.copy(
                validationError = result.error,
            ),
        )

        is HomeResult.MoodSelectionUpdated -> currentState.copy(
            moodSelectionState = HomeMoodSelectionState(
                selectedMoodToken = result.moodToken,
            ),
        )
    }
}

internal sealed interface HomeResult {
    data class SelectedDateChanged(
        val date: LocalDate,
        val greetingAreaState: HomeGreetingState,
    ) : HomeResult

    data object HomeDataLoading : HomeResult

    data class HomeDataLoaded(
        val snapshot: HomeSnapshot,
    ) : HomeResult

    data object HomeDataLoadFailed : HomeResult

    data class QuickAddOpened(
        val habitId: String,
        val habitName: String,
        val draftValue: String,
    ) : HomeResult

    data object QuickAddDismissed : HomeResult

    data class QuickAddValueChanged(
        val value: String,
    ) : HomeResult

    data class QuickAddValidationFailed(
        val error: QuickAddValidationError,
    ) : HomeResult

    data class MoodSelectionUpdated(
        val moodToken: String,
    ) : HomeResult
}

private fun List<com.threemdroid.habittracker.feature.home.contract.HomeHabitItem>.toHomeScreenState(): HomeScreenState =
    if (isEmpty()) {
        HomeScreenState.Empty
    } else {
        HomeScreenState.Content
    }
