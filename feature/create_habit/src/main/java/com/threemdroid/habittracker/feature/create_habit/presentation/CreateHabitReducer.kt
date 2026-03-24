package com.threemdroid.habittracker.feature.create_habit.presentation

import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitUiState
import com.threemdroid.habittracker.feature.create_habit.contract.clearColorError
import com.threemdroid.habittracker.feature.create_habit.contract.clearDefaultIncrementError
import com.threemdroid.habittracker.feature.create_habit.contract.clearIconError
import com.threemdroid.habittracker.feature.create_habit.contract.clearNameError
import com.threemdroid.habittracker.feature.create_habit.contract.clearScheduleError
import com.threemdroid.habittracker.feature.create_habit.contract.clearTargetValueError
import com.threemdroid.habittracker.feature.create_habit.contract.withQuantityErrorsCleared
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitValidationState
import java.time.DayOfWeek
import java.time.LocalTime

internal object CreateHabitReducer {
    fun reduce(
        currentState: CreateHabitUiState,
        result: CreateHabitResult,
    ): CreateHabitUiState = when (result) {
        is CreateHabitResult.NameChanged -> currentState.copy(
            nameInput = result.value,
            validation = currentState.validation.clearNameError(),
        )

        is CreateHabitResult.IconSelected -> currentState.copy(
            selectedIconToken = result.iconToken,
            validation = currentState.validation.clearIconError(),
        )

        is CreateHabitResult.ColorSelected -> currentState.copy(
            selectedColorToken = result.colorToken,
            validation = currentState.validation.clearColorError(),
        )

        is CreateHabitResult.HabitTypeSelected -> currentState.copy(
            habitType = result.habitType,
            validation = currentState.validation.withQuantityErrorsCleared(),
        )

        is CreateHabitResult.TargetValueChanged -> currentState.copy(
            targetValueInput = result.value,
            validation = currentState.validation.clearTargetValueError(),
        )

        is CreateHabitResult.DefaultIncrementChanged -> currentState.copy(
            defaultIncrementInput = result.value,
            validation = currentState.validation.clearDefaultIncrementError(),
        )

        is CreateHabitResult.UnitLabelChanged -> currentState.copy(
            unitLabelInput = result.value,
        )

        is CreateHabitResult.MultipleUpdatesPerDayChanged -> currentState.copy(
            allowsMultipleUpdatesPerDay = result.isEnabled,
        )

        is CreateHabitResult.SchedulePresetSelected -> currentState.copy(
            schedulePreset = result.preset,
            selectedDays = result.selectedDays,
            validation = currentState.validation.clearScheduleError(),
        )

        is CreateHabitResult.CustomSelectedDaysChanged -> currentState.copy(
            schedulePreset = CreateHabitSchedulePreset.CUSTOM,
            selectedDays = result.selectedDays,
            validation = currentState.validation.clearScheduleError(),
        )

        is CreateHabitResult.ReminderAdded -> currentState.copy(
            reminders = currentState.reminders + result.reminder,
        )

        is CreateHabitResult.ReminderRemoved -> currentState.copy(
            reminders = currentState.reminders.filterNot { reminder -> reminder.reminderId == result.reminderId },
        )

        is CreateHabitResult.ReminderTimeChanged -> currentState.copy(
            reminders = currentState.reminders.map { reminder ->
                if (reminder.reminderId == result.reminderId) {
                    reminder.copy(time = result.time)
                } else {
                    reminder
                }
            },
        )

        is CreateHabitResult.ReminderEnabledChanged -> currentState.copy(
            reminders = currentState.reminders.map { reminder ->
                if (reminder.reminderId == result.reminderId) {
                    reminder.copy(isEnabled = result.isEnabled)
                } else {
                    reminder
                }
            },
        )

        is CreateHabitResult.ValidationUpdated -> currentState.copy(
            validation = result.validation,
        )

        is CreateHabitResult.SavingStateChanged -> currentState.copy(
            isSaving = result.isSaving,
        )
    }
}

internal sealed interface CreateHabitResult {
    data class NameChanged(
        val value: String,
    ) : CreateHabitResult

    data class IconSelected(
        val iconToken: String,
    ) : CreateHabitResult

    data class ColorSelected(
        val colorToken: String,
    ) : CreateHabitResult

    data class HabitTypeSelected(
        val habitType: HabitType,
    ) : CreateHabitResult

    data class TargetValueChanged(
        val value: String,
    ) : CreateHabitResult

    data class DefaultIncrementChanged(
        val value: String,
    ) : CreateHabitResult

    data class UnitLabelChanged(
        val value: String,
    ) : CreateHabitResult

    data class MultipleUpdatesPerDayChanged(
        val isEnabled: Boolean,
    ) : CreateHabitResult

    data class SchedulePresetSelected(
        val preset: CreateHabitSchedulePreset,
        val selectedDays: Set<DayOfWeek>,
    ) : CreateHabitResult

    data class CustomSelectedDaysChanged(
        val selectedDays: Set<DayOfWeek>,
    ) : CreateHabitResult

    data class ReminderAdded(
        val reminder: CreateHabitReminderItem,
    ) : CreateHabitResult

    data class ReminderRemoved(
        val reminderId: String,
    ) : CreateHabitResult

    data class ReminderTimeChanged(
        val reminderId: String,
        val time: LocalTime,
    ) : CreateHabitResult

    data class ReminderEnabledChanged(
        val reminderId: String,
        val isEnabled: Boolean,
    ) : CreateHabitResult

    data class ValidationUpdated(
        val validation: CreateHabitValidationState,
    ) : CreateHabitResult

    data class SavingStateChanged(
        val isSaving: Boolean,
    ) : CreateHabitResult
}
