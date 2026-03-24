package com.threemdroid.habittracker.feature.create_habit.contract

import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitNumericValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitScheduleValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSelectionValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitValidationState
import java.time.DayOfWeek
import java.time.LocalTime

data class CreateHabitReminderItem(
    val reminderId: String,
    val time: LocalTime,
    val isEnabled: Boolean,
)

data class CreateHabitUiState(
    val nameInput: String,
    val selectedIconToken: String?,
    val selectedColorToken: String?,
    val habitType: HabitType,
    val targetValueInput: String,
    val defaultIncrementInput: String,
    val unitLabelInput: String,
    val allowsMultipleUpdatesPerDay: Boolean,
    val schedulePreset: CreateHabitSchedulePreset,
    val selectedDays: Set<DayOfWeek>,
    val reminders: List<CreateHabitReminderItem>,
    val validation: CreateHabitValidationState,
    val isSaving: Boolean,
) {
    companion object {
        fun initial(
            selectedDays: Set<DayOfWeek>,
        ): CreateHabitUiState = CreateHabitUiState(
            nameInput = "",
            selectedIconToken = null,
            selectedColorToken = null,
            habitType = HabitType.BOOLEAN,
            targetValueInput = "1",
            defaultIncrementInput = "1",
            unitLabelInput = "",
            allowsMultipleUpdatesPerDay = false,
            schedulePreset = CreateHabitSchedulePreset.EVERY_DAY,
            selectedDays = selectedDays,
            reminders = emptyList(),
            validation = CreateHabitValidationState(),
            isSaving = false,
        )
    }
}

sealed interface CreateHabitIntent {
    data class NameChanged(
        val value: String,
    ) : CreateHabitIntent

    data class IconSelected(
        val iconToken: String,
    ) : CreateHabitIntent

    data class ColorSelected(
        val colorToken: String,
    ) : CreateHabitIntent

    data class HabitTypeSelected(
        val habitType: HabitType,
    ) : CreateHabitIntent

    data class TargetValueChanged(
        val value: String,
    ) : CreateHabitIntent

    data class DefaultIncrementChanged(
        val value: String,
    ) : CreateHabitIntent

    data class UnitLabelChanged(
        val value: String,
    ) : CreateHabitIntent

    data class MultipleUpdatesPerDayChanged(
        val isEnabled: Boolean,
    ) : CreateHabitIntent

    data class SchedulePresetSelected(
        val preset: CreateHabitSchedulePreset,
    ) : CreateHabitIntent

    data class CustomWeekdayToggled(
        val dayOfWeek: DayOfWeek,
    ) : CreateHabitIntent

    data object ReminderAdded : CreateHabitIntent

    data class ReminderRemoved(
        val reminderId: String,
    ) : CreateHabitIntent

    data class ReminderTimeChanged(
        val reminderId: String,
        val time: LocalTime,
    ) : CreateHabitIntent

    data class ReminderEnabledChanged(
        val reminderId: String,
        val isEnabled: Boolean,
    ) : CreateHabitIntent

    data object SaveRequested : CreateHabitIntent
}

enum class CreateHabitSaveError {
    GENERIC,
}

sealed interface CreateHabitEffect {
    data class HabitCreated(
        val habitId: String,
    ) : CreateHabitEffect

    data class ShowSaveError(
        val error: CreateHabitSaveError,
    ) : CreateHabitEffect
}

internal fun CreateHabitValidationState.clearNameError(): CreateHabitValidationState = copy(nameError = null)

internal fun CreateHabitValidationState.clearIconError(): CreateHabitValidationState = copy(iconError = null)

internal fun CreateHabitValidationState.clearColorError(): CreateHabitValidationState = copy(colorError = null)

internal fun CreateHabitValidationState.clearTargetValueError(): CreateHabitValidationState = copy(targetValueError = null)

internal fun CreateHabitValidationState.clearDefaultIncrementError(): CreateHabitValidationState = copy(defaultIncrementError = null)

internal fun CreateHabitValidationState.clearScheduleError(): CreateHabitValidationState = copy(scheduleError = null)

internal fun CreateHabitValidationState.withQuantityErrorsCleared(): CreateHabitValidationState = copy(
    targetValueError = null,
    defaultIncrementError = null,
)
