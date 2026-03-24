package com.threemdroid.habittracker.feature.create_habit

import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitNumericValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitValidationState
import com.threemdroid.habittracker.feature.create_habit.presentation.CreateHabitReducer
import com.threemdroid.habittracker.feature.create_habit.presentation.CreateHabitResult
import com.threemdroid.habittracker.feature.create_habit.testutil.createHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.testutil.createHabitUiState
import java.time.DayOfWeek
import java.time.LocalTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class CreateHabitReducerTest {
    @Test
    fun habitTypeSelected_clearsQuantityValidationErrors() {
        val initialState = createHabitUiState().copy(
            validation = CreateHabitValidationState(
                targetValueError = CreateHabitNumericValidationError.INVALID_NUMBER,
                defaultIncrementError = CreateHabitNumericValidationError.NON_POSITIVE,
            ),
        )

        val state = CreateHabitReducer.reduce(
            currentState = initialState,
            result = CreateHabitResult.HabitTypeSelected(HabitType.BOOLEAN),
        )

        assertEquals(HabitType.BOOLEAN, state.habitType)
        assertEquals(null, state.validation.targetValueError)
        assertEquals(null, state.validation.defaultIncrementError)
    }

    @Test
    fun schedulePresetSelected_updatesPresetAndDays() {
        val state = CreateHabitReducer.reduce(
            currentState = createHabitUiState(),
            result = CreateHabitResult.SchedulePresetSelected(
                preset = CreateHabitSchedulePreset.WEEKENDS,
                selectedDays = setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
            ),
        )

        assertEquals(CreateHabitSchedulePreset.WEEKENDS, state.schedulePreset)
        assertEquals(setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY), state.selectedDays)
    }

    @Test
    fun customSelectedDaysChanged_forcesCustomPreset() {
        val state = CreateHabitReducer.reduce(
            currentState = createHabitUiState(),
            result = CreateHabitResult.CustomSelectedDaysChanged(
                selectedDays = setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
            ),
        )

        assertEquals(CreateHabitSchedulePreset.CUSTOM, state.schedulePreset)
        assertEquals(setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), state.selectedDays)
    }

    @Test
    fun reminderChanges_updateReminderCollection() {
        val addedState = CreateHabitReducer.reduce(
            currentState = createHabitUiState().copy(reminders = emptyList()),
            result = CreateHabitResult.ReminderAdded(createHabitReminderItem()),
        )

        val updatedState = CreateHabitReducer.reduce(
            currentState = addedState,
            result = CreateHabitResult.ReminderTimeChanged(
                reminderId = "reminder-1",
                time = LocalTime.of(18, 30),
            ),
        )
        val removedState = CreateHabitReducer.reduce(
            currentState = updatedState,
            result = CreateHabitResult.ReminderRemoved("reminder-1"),
        )

        assertEquals(1, addedState.reminders.size)
        assertEquals(LocalTime.of(18, 30), updatedState.reminders.single().time)
        assertFalse(removedState.reminders.any { reminder -> reminder.reminderId == "reminder-1" })
    }
}
