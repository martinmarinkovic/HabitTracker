package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.domain.activity.ActivityRepository
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import com.threemdroid.habittracker.feature.home.contract.HomeHabitItem
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class ObserveHomeDataUseCase(
    private val habitsRepository: HabitsRepository,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(date: LocalDate): Flow<AppResult<HomeSnapshot>> = combine(
        habitsRepository.observeHabits(),
        habitsRepository.observeHabitEntries(date),
        activityRepository.observeMoodEntriesForDate(date),
    ) { habitsResult, entriesResult, moodEntriesResult ->
        when {
            habitsResult is AppResult.Failure -> habitsResult
            entriesResult is AppResult.Failure -> entriesResult
            moodEntriesResult is AppResult.Failure -> moodEntriesResult
            habitsResult is AppResult.Success &&
                entriesResult is AppResult.Success &&
                moodEntriesResult is AppResult.Success -> {
                AppResult.Success(
                    HomeSnapshot(
                        habitsToday = habitsResult.value
                            .filter { habit -> habit.isActiveFor(date) }
                            .map { habit ->
                                val progressValue = entriesResult.value
                                    .asSequence()
                                    .filter { entry -> entry.habitId == habit.id }
                                    .sumOf(HabitEntry::value)

                                HomeHabitItem(
                                    habitId = habit.id,
                                    name = habit.name,
                                    type = habit.type,
                                    progressValue = progressValue,
                                    targetValue = habit.targetValue,
                                    defaultIncrement = habit.defaultIncrement,
                                    unitLabel = habit.unitLabel,
                                    allowsMultipleUpdatesPerDay = habit.allowsMultipleUpdatesPerDay,
                                    selectedIconToken = habit.selectedIconToken,
                                    selectedColorToken = habit.selectedColorToken,
                                    isCompleted = habit.targetValue > 0 && progressValue >= habit.targetValue,
                                )
                            },
                        selectedMoodToken = moodEntriesResult.value.firstOrNull()?.moodToken,
                    ),
                )
            }

            else -> AppResult.Failure(
                error = com.threemdroid.habittracker.core.common.result.AppError.Unknown(
                    message = "Home data could not be resolved.",
                ),
            )
        }
    }
}

private fun Habit.isActiveFor(date: LocalDate): Boolean {
    if (state != HabitState.ACTIVE) {
        return false
    }

    return schedule.selectedDays.isEmpty() || schedule.selectedDays.contains(date.dayOfWeek)
}
