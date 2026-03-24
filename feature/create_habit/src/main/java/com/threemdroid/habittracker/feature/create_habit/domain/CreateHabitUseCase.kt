package com.threemdroid.habittracker.feature.create_habit.domain

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitSchedule
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.Reminder
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import java.time.Clock
import java.time.Instant
import java.util.UUID

internal class CreateHabitUseCase(
    private val habitsRepository: HabitsRepository,
    private val clock: Clock = Clock.systemDefaultZone(),
    private val habitIdGenerator: () -> String = { UUID.randomUUID().toString() },
) {
    suspend operator fun invoke(
        draft: ValidCreateHabitDraft,
    ): AppResult<String> {
        val habitId = habitIdGenerator()
        val createdAt = Instant.now(clock)
        val schedule = HabitSchedule(selectedDays = draft.selectedDays)
        val reminders = draft.reminders.map { reminder ->
            Reminder(
                id = reminder.reminderId,
                habitId = habitId,
                time = reminder.time,
                schedule = schedule,
                isEnabled = reminder.isEnabled,
            )
        }
        val habit = Habit(
            id = habitId,
            name = draft.name,
            type = draft.habitType,
            targetValue = draft.targetValue,
            defaultIncrement = draft.defaultIncrement,
            unitLabel = draft.unitLabel,
            allowsMultipleUpdatesPerDay = draft.allowsMultipleUpdatesPerDay,
            selectedIconToken = draft.selectedIconToken,
            selectedColorToken = draft.selectedColorToken,
            schedule = schedule,
            reminders = reminders,
            state = HabitState.ACTIVE,
            createdAt = createdAt,
            stoppedAt = null,
        )

        when (val saveHabitResult = habitsRepository.upsertHabit(habit)) {
            is AppResult.Failure -> return saveHabitResult
            is AppResult.Success -> Unit
        }

        reminders.forEach { reminder ->
            when (val reminderResult = habitsRepository.upsertReminder(reminder)) {
                is AppResult.Failure -> {
                    habitsRepository.deleteHabit(habitId)
                    return reminderResult
                }
                is AppResult.Success -> Unit
            }
        }

        return AppResult.Success(habitId)
    }
}
