package com.threemdroid.habittracker.feature.create_habit.domain

import com.threemdroid.habittracker.core.model.habits.HabitType

internal class ValidateCreateHabitDraftUseCase {
    operator fun invoke(draft: CreateHabitDraft): CreateHabitValidationResult {
        val trimmedName = draft.name.trim()
        val trimmedIconToken = draft.selectedIconToken?.trim().orEmpty()
        val trimmedColorToken = draft.selectedColorToken?.trim().orEmpty()
        val selectedDays = resolveSelectedDays(
            preset = draft.schedulePreset,
            customSelectedDays = draft.selectedDays,
        )

        val validation = CreateHabitValidationState(
            nameError = if (trimmedName.isEmpty()) {
                CreateHabitNameValidationError.REQUIRED
            } else {
                null
            },
            iconError = if (trimmedIconToken.isEmpty()) {
                CreateHabitSelectionValidationError.REQUIRED
            } else {
                null
            },
            colorError = if (trimmedColorToken.isEmpty()) {
                CreateHabitSelectionValidationError.REQUIRED
            } else {
                null
            },
            targetValueError = targetValueErrorFor(draft),
            defaultIncrementError = defaultIncrementErrorFor(draft),
            scheduleError = if (selectedDays.isEmpty()) {
                CreateHabitScheduleValidationError.NO_DAYS_SELECTED
            } else {
                null
            },
        )

        if (validation.hasErrors) {
            return CreateHabitValidationResult.Invalid(validation)
        }

        return CreateHabitValidationResult.Valid(
            draft = ValidCreateHabitDraft(
                name = trimmedName,
                selectedIconToken = trimmedIconToken,
                selectedColorToken = trimmedColorToken,
                habitType = draft.habitType,
                targetValue = resolveTargetValue(draft),
                defaultIncrement = resolveDefaultIncrement(draft),
                unitLabel = resolveUnitLabel(draft),
                allowsMultipleUpdatesPerDay = draft.allowsMultipleUpdatesPerDay,
                selectedDays = selectedDays,
                reminders = draft.reminders,
            ),
        )
    }

    private fun targetValueErrorFor(
        draft: CreateHabitDraft,
    ): CreateHabitNumericValidationError? {
        if (draft.habitType != HabitType.QUANTITY) {
            return null
        }

        return numericErrorFor(draft.targetValueInput)
    }

    private fun defaultIncrementErrorFor(
        draft: CreateHabitDraft,
    ): CreateHabitNumericValidationError? {
        if (draft.habitType != HabitType.QUANTITY) {
            return null
        }

        return numericErrorFor(draft.defaultIncrementInput)
    }

    private fun numericErrorFor(
        rawValue: String,
    ): CreateHabitNumericValidationError? {
        val trimmedValue = rawValue.trim()
        if (trimmedValue.isEmpty()) {
            return CreateHabitNumericValidationError.REQUIRED
        }

        val parsedValue = trimmedValue.toDoubleOrNull()
            ?: return CreateHabitNumericValidationError.INVALID_NUMBER

        return if (parsedValue <= 0) {
            CreateHabitNumericValidationError.NON_POSITIVE
        } else {
            null
        }
    }

    private fun resolveTargetValue(
        draft: CreateHabitDraft,
    ): Double = if (draft.habitType == HabitType.QUANTITY) {
        draft.targetValueInput.trim().toDouble()
    } else {
        1.0
    }

    private fun resolveDefaultIncrement(
        draft: CreateHabitDraft,
    ): Double = if (draft.habitType == HabitType.QUANTITY) {
        draft.defaultIncrementInput.trim().toDouble()
    } else {
        1.0
    }

    private fun resolveUnitLabel(
        draft: CreateHabitDraft,
    ): String? = if (draft.habitType == HabitType.QUANTITY) {
        draft.unitLabelInput.trim().ifEmpty { null }
    } else {
        null
    }
}
