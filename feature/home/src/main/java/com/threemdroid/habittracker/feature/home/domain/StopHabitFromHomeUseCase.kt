package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import java.time.Clock
import kotlinx.coroutines.flow.first

class StopHabitFromHomeUseCase(
    private val habitsRepository: HabitsRepository,
    private val clock: Clock,
) {
    suspend operator fun invoke(habitId: String): AppResult<Unit> {
        val habit = when (val habitResult = habitsRepository.observeHabit(habitId).first()) {
            is AppResult.Failure -> return habitResult
            is AppResult.Success -> habitResult.value ?: return AppResult.Failure(
                AppError.NotFound(message = "Habit $habitId was not found."),
            )
        }

        if (habit.state == HabitState.STOPPED) {
            return AppResult.Success(Unit)
        }

        return habitsRepository.upsertHabit(
            habit.copy(
                state = HabitState.STOPPED,
                stoppedAt = clock.instant(),
            ),
        )
    }
}
