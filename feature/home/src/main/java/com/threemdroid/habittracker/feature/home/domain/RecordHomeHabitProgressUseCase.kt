package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import java.time.Clock
import java.time.LocalDate
import java.util.UUID
import kotlinx.coroutines.flow.first

class RecordHomeHabitProgressUseCase(
    private val habitsRepository: HabitsRepository,
    private val clock: Clock,
    private val idGenerator: () -> String = { UUID.randomUUID().toString() },
) {
    suspend operator fun invoke(
        habitId: String,
        entryDate: LocalDate,
        requestedValue: Double? = null,
    ): AppResult<Unit> {
        val habit = when (val habitResult = habitsRepository.observeHabit(habitId).first()) {
            is AppResult.Failure -> return habitResult
            is AppResult.Success -> habitResult.value ?: return AppResult.Failure(
                AppError.NotFound(message = "Habit $habitId was not found."),
            )
        }

        if (habit.state != HabitState.ACTIVE) {
            return AppResult.Failure(
                AppError.Validation(message = "Stopped habits cannot be updated from Home."),
            )
        }

        val entryValue = requestedValue ?: habit.defaultIncrement
        if (entryValue <= 0) {
            return AppResult.Failure(
                AppError.Validation(message = "Habit progress value must be positive."),
            )
        }

        val existingEntries = when (
            val entriesResult = habitsRepository.observeHabitEntries(
                habitId = habitId,
                startDate = entryDate,
                endDate = entryDate,
            ).first()
        ) {
            is AppResult.Failure -> return entriesResult
            is AppResult.Success -> entriesResult.value
        }

        if (
            habit.type == HabitType.BOOLEAN &&
            !habit.allowsMultipleUpdatesPerDay &&
            existingEntries.isNotEmpty()
        ) {
            return AppResult.Success(Unit)
        }

        val entryId = resolveEntryId(
            habit = habit,
            entryDate = entryDate,
            existingEntries = existingEntries,
        )

        return habitsRepository.upsertHabitEntry(
            HabitEntry(
                id = entryId,
                habitId = habit.id,
                entryDate = entryDate,
                loggedAt = clock.instant(),
                value = entryValue,
            ),
        )
    }

    private fun resolveEntryId(
        habit: Habit,
        entryDate: LocalDate,
        existingEntries: List<HabitEntry>,
    ): String = when {
        habit.allowsMultipleUpdatesPerDay -> idGenerator()
        existingEntries.isNotEmpty() -> existingEntries.first().id
        else -> "${habit.id}-$entryDate"
    }
}
