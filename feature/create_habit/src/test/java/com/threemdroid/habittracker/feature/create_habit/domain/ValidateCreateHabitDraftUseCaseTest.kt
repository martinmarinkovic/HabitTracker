package com.threemdroid.habittracker.feature.create_habit.domain

import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.testutil.createHabitDraft
import com.threemdroid.habittracker.feature.create_habit.testutil.createHabitValidator
import java.time.DayOfWeek
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ValidateCreateHabitDraftUseCaseTest {
    @Test
    fun invoke_missingCoreSelections_returnsFieldErrors() = runTest {
        val result = createHabitValidator().invoke(
            createHabitDraft(
                name = " ",
                selectedIconToken = " ",
                selectedColorToken = null,
                schedulePreset = CreateHabitSchedulePreset.CUSTOM,
                selectedDays = emptySet(),
            ),
        )

        val invalid = result as CreateHabitValidationResult.Invalid
        assertEquals(CreateHabitNameValidationError.REQUIRED, invalid.validation.nameError)
        assertEquals(CreateHabitSelectionValidationError.REQUIRED, invalid.validation.iconError)
        assertEquals(CreateHabitSelectionValidationError.REQUIRED, invalid.validation.colorError)
        assertEquals(CreateHabitScheduleValidationError.NO_DAYS_SELECTED, invalid.validation.scheduleError)
    }

    @Test
    fun invoke_quantityHabitWithInvalidNumbers_returnsNumericErrors() = runTest {
        val result = createHabitValidator().invoke(
            createHabitDraft(
                habitType = HabitType.QUANTITY,
                targetValueInput = "zero",
                defaultIncrementInput = "0",
            ),
        )

        val invalid = result as CreateHabitValidationResult.Invalid
        assertEquals(CreateHabitNumericValidationError.INVALID_NUMBER, invalid.validation.targetValueError)
        assertEquals(CreateHabitNumericValidationError.NON_POSITIVE, invalid.validation.defaultIncrementError)
    }

    @Test
    fun invoke_booleanHabit_ignoresQuantityInputsAndNormalizesOutput() = runTest {
        val result = createHabitValidator().invoke(
            createHabitDraft(
                habitType = HabitType.BOOLEAN,
                targetValueInput = "",
                defaultIncrementInput = "",
                unitLabelInput = "km",
                schedulePreset = CreateHabitSchedulePreset.CUSTOM,
                selectedDays = setOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY),
            ),
        )

        val valid = result as CreateHabitValidationResult.Valid
        assertEquals(1.0, valid.draft.targetValue, 0.0)
        assertEquals(1.0, valid.draft.defaultIncrement, 0.0)
        assertNull(valid.draft.unitLabel)
        assertEquals(setOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY), valid.draft.selectedDays)
    }
}
