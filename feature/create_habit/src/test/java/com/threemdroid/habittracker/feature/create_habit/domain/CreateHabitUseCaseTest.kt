package com.threemdroid.habittracker.feature.create_habit.domain

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.testutil.CREATE_HABIT_TEST_INSTANT
import com.threemdroid.habittracker.feature.create_habit.testutil.TestCreateHabitHabitsRepository
import com.threemdroid.habittracker.feature.create_habit.testutil.createHabitReminderDraft
import com.threemdroid.habittracker.feature.create_habit.testutil.createHabitUseCase
import com.threemdroid.habittracker.feature.create_habit.testutil.reminderScheduleDays
import com.threemdroid.habittracker.feature.create_habit.testutil.storageError
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CreateHabitUseCaseTest {
    @Test
    fun invoke_validQuantityHabit_persistsHabitAndReminders() = runTest {
        val repository = TestCreateHabitHabitsRepository()
        val draft = ValidCreateHabitDraft(
            name = "Walk",
            selectedIconToken = "shoe",
            selectedColorToken = "green",
            habitType = HabitType.QUANTITY,
            targetValue = 5.0,
            defaultIncrement = 1.5,
            unitLabel = "km",
            allowsMultipleUpdatesPerDay = true,
            selectedDays = reminderScheduleDays(CreateHabitSchedulePreset.WEEKDAYS, emptySet()),
            reminders = listOf(
                createHabitReminderDraft(reminderId = "reminder-1"),
                createHabitReminderDraft(reminderId = "reminder-2"),
            ),
        )

        val result = createHabitUseCase(repository).invoke(draft)

        assertEquals(AppResult.Success("habit-created-id"), result)
        assertEquals(1, repository.savedHabits.size)
        assertEquals(2, repository.savedReminders.size)
        assertEquals(CREATE_HABIT_TEST_INSTANT, repository.savedHabits.single().createdAt)
        assertEquals("habit-created-id", repository.savedReminders.first().habitId)
        assertEquals(draft.selectedDays, repository.savedHabits.single().schedule.selectedDays)
    }

    @Test
    fun invoke_booleanHabit_normalizesNumericFieldsAndUnitLabel() = runTest {
        val repository = TestCreateHabitHabitsRepository()
        val draft = ValidCreateHabitDraft(
            name = "Meditate",
            selectedIconToken = "lotus",
            selectedColorToken = "blue",
            habitType = HabitType.BOOLEAN,
            targetValue = 1.0,
            defaultIncrement = 1.0,
            unitLabel = null,
            allowsMultipleUpdatesPerDay = false,
            selectedDays = reminderScheduleDays(CreateHabitSchedulePreset.EVERY_DAY, emptySet()),
            reminders = emptyList(),
        )

        createHabitUseCase(repository).invoke(draft)

        val savedHabit = repository.savedHabits.single()
        assertEquals(HabitType.BOOLEAN, savedHabit.type)
        assertEquals(1.0, savedHabit.targetValue, 0.0)
        assertEquals(1.0, savedHabit.defaultIncrement, 0.0)
        assertEquals(null, savedHabit.unitLabel)
    }

    @Test
    fun invoke_whenReminderSaveFails_rollsBackCreatedHabit() = runTest {
        val repository = TestCreateHabitHabitsRepository().apply {
            enqueueReminderResult(storageError("Failed to save reminder."))
        }
        val draft = ValidCreateHabitDraft(
            name = "Read",
            selectedIconToken = "book",
            selectedColorToken = "orange",
            habitType = HabitType.QUANTITY,
            targetValue = 30.0,
            defaultIncrement = 5.0,
            unitLabel = "pages",
            allowsMultipleUpdatesPerDay = false,
            selectedDays = reminderScheduleDays(CreateHabitSchedulePreset.EVERY_DAY, emptySet()),
            reminders = listOf(createHabitReminderDraft(reminderId = "reminder-1")),
        )

        val result = createHabitUseCase(repository).invoke(draft)

        assertTrue(result is AppResult.Failure)
        assertEquals(listOf("habit-created-id"), repository.deletedHabitIds)
        assertTrue(repository.savedHabits.isEmpty())
        assertTrue(repository.savedReminders.isEmpty())
    }
}
