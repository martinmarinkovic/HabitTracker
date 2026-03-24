package com.threemdroid.habittracker.feature.home

import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingState
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingTimeOfDay
import com.threemdroid.habittracker.feature.home.contract.HomeHabitItem
import com.threemdroid.habittracker.feature.home.contract.HomeLoadError
import com.threemdroid.habittracker.feature.home.contract.HomeQuickAddOverlayState
import com.threemdroid.habittracker.feature.home.contract.HomeScreenState
import com.threemdroid.habittracker.feature.home.contract.HomeUiState
import com.threemdroid.habittracker.feature.home.contract.QuickAddValidationError
import com.threemdroid.habittracker.feature.home.domain.HomeSnapshot
import com.threemdroid.habittracker.feature.home.presentation.HomeReducer
import com.threemdroid.habittracker.feature.home.presentation.HomeResult
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeReducerTest {
    @Test
    fun homeDataLoadedWithHabits_setsContentState() {
        val initialState = testState()

        val reducedState = HomeReducer.reduce(
            currentState = initialState,
            result = HomeResult.HomeDataLoaded(
                snapshot = HomeSnapshot(
                    habitsToday = listOf(testHabitItem()),
                    selectedMoodToken = "calm",
                ),
            ),
        )

        assertEquals(HomeScreenState.Content, reducedState.screenState)
        assertEquals("calm", reducedState.moodSelectionState.selectedMoodToken)
        assertEquals(1, reducedState.habitsToday.size)
    }

    @Test
    fun homeDataLoadedWithoutHabits_setsEmptyState() {
        val initialState = testState()

        val reducedState = HomeReducer.reduce(
            currentState = initialState,
            result = HomeResult.HomeDataLoaded(
                snapshot = HomeSnapshot(
                    habitsToday = emptyList(),
                    selectedMoodToken = null,
                ),
            ),
        )

        assertEquals(HomeScreenState.Empty, reducedState.screenState)
        assertEquals(emptyList<HomeHabitItem>(), reducedState.habitsToday)
    }

    @Test
    fun quickAddValidationFailed_keepsOverlayVisibleAndAddsValidationError() {
        val initialState = testState().copy(
            quickAddOverlayState = HomeQuickAddOverlayState(
                isVisible = true,
                habitId = "habit-1",
                habitName = "Walk",
                draftValue = "abc",
            ),
        )

        val reducedState = HomeReducer.reduce(
            currentState = initialState,
            result = HomeResult.QuickAddValidationFailed(QuickAddValidationError.INVALID_NUMBER),
        )

        assertEquals(true, reducedState.quickAddOverlayState.isVisible)
        assertEquals(
            QuickAddValidationError.INVALID_NUMBER,
            reducedState.quickAddOverlayState.validationError,
        )
    }

    @Test
    fun homeDataLoadFailed_setsErrorState() {
        val reducedState = HomeReducer.reduce(
            currentState = testState().copy(
                quickAddOverlayState = HomeQuickAddOverlayState(
                    isVisible = true,
                    habitId = "habit-1",
                    habitName = "Walk",
                    draftValue = "2",
                ),
            ),
            result = HomeResult.HomeDataLoadFailed,
        )

        assertEquals(HomeScreenState.Error(HomeLoadError.GENERIC), reducedState.screenState)
        assertEquals(false, reducedState.quickAddOverlayState.isVisible)
        assertEquals(null, reducedState.moodSelectionState.selectedMoodToken)
    }

    @Test
    fun homeDataLoading_clearsTransientInteractiveState() {
        val reducedState = HomeReducer.reduce(
            currentState = testState().copy(
                quickAddOverlayState = HomeQuickAddOverlayState(
                    isVisible = true,
                    habitId = "habit-1",
                    habitName = "Walk",
                    draftValue = "4",
                ),
            ),
            result = HomeResult.HomeDataLoading,
        )

        assertEquals(HomeScreenState.Loading, reducedState.screenState)
        assertEquals(false, reducedState.quickAddOverlayState.isVisible)
        assertEquals(null, reducedState.moodSelectionState.selectedMoodToken)
    }

    private fun testState(): HomeUiState = HomeUiState.initial(
        selectedDate = LocalDate.of(2026, 3, 24),
        greetingAreaState = HomeGreetingState(
            timeOfDay = HomeGreetingTimeOfDay.MORNING,
            isSelectedDateToday = true,
        ),
    )

    private fun testHabitItem(): HomeHabitItem = HomeHabitItem(
        habitId = "habit-1",
        name = "Walk",
        type = HabitType.QUANTITY,
        progressValue = 2.0,
        targetValue = 4.0,
        defaultIncrement = 1.0,
        unitLabel = "km",
        allowsMultipleUpdatesPerDay = true,
        selectedIconToken = "shoe",
        selectedColorToken = "green",
        isCompleted = false,
    )
}
