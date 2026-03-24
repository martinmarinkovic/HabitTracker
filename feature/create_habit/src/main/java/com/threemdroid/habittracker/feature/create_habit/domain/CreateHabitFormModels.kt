package com.threemdroid.habittracker.feature.create_habit.domain

import com.threemdroid.habittracker.core.model.habits.HabitType
import java.time.DayOfWeek
import java.time.LocalTime

enum class CreateHabitSchedulePreset {
    EVERY_DAY,
    WEEKDAYS,
    WEEKENDS,
    CUSTOM,
}

enum class CreateHabitNameValidationError {
    REQUIRED,
}

enum class CreateHabitSelectionValidationError {
    REQUIRED,
}

enum class CreateHabitNumericValidationError {
    REQUIRED,
    INVALID_NUMBER,
    NON_POSITIVE,
}

enum class CreateHabitScheduleValidationError {
    NO_DAYS_SELECTED,
}

data class CreateHabitValidationState(
    val nameError: CreateHabitNameValidationError? = null,
    val iconError: CreateHabitSelectionValidationError? = null,
    val colorError: CreateHabitSelectionValidationError? = null,
    val targetValueError: CreateHabitNumericValidationError? = null,
    val defaultIncrementError: CreateHabitNumericValidationError? = null,
    val scheduleError: CreateHabitScheduleValidationError? = null,
) {
    val hasErrors: Boolean
        get() = nameError != null ||
            iconError != null ||
            colorError != null ||
            targetValueError != null ||
            defaultIncrementError != null ||
            scheduleError != null
}

data class CreateHabitReminderDraft(
    val reminderId: String,
    val time: LocalTime,
    val isEnabled: Boolean,
)

data class CreateHabitDraft(
    val name: String,
    val selectedIconToken: String?,
    val selectedColorToken: String?,
    val habitType: HabitType,
    val targetValueInput: String,
    val defaultIncrementInput: String,
    val unitLabelInput: String,
    val allowsMultipleUpdatesPerDay: Boolean,
    val schedulePreset: CreateHabitSchedulePreset,
    val selectedDays: Set<DayOfWeek>,
    val reminders: List<CreateHabitReminderDraft>,
)

data class ValidCreateHabitDraft(
    val name: String,
    val selectedIconToken: String,
    val selectedColorToken: String,
    val habitType: HabitType,
    val targetValue: Double,
    val defaultIncrement: Double,
    val unitLabel: String?,
    val allowsMultipleUpdatesPerDay: Boolean,
    val selectedDays: Set<DayOfWeek>,
    val reminders: List<CreateHabitReminderDraft>,
)

sealed interface CreateHabitValidationResult {
    data class Valid(
        val draft: ValidCreateHabitDraft,
    ) : CreateHabitValidationResult

    data class Invalid(
        val validation: CreateHabitValidationState,
    ) : CreateHabitValidationResult
}

internal fun resolveSelectedDays(
    preset: CreateHabitSchedulePreset,
    customSelectedDays: Set<DayOfWeek>,
): Set<DayOfWeek> = when (preset) {
    CreateHabitSchedulePreset.EVERY_DAY -> DayOfWeek.values().toSet()
    CreateHabitSchedulePreset.WEEKDAYS -> setOf(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
    )
    CreateHabitSchedulePreset.WEEKENDS -> setOf(
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY,
    )
    CreateHabitSchedulePreset.CUSTOM -> customSelectedDays
}
