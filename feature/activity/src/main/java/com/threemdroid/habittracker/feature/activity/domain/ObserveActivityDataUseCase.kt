package com.threemdroid.habittracker.feature.activity.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import java.time.Clock
import java.time.LocalDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
internal class ObserveActivityDataUseCase(
    private val habitsRepository: HabitsRepository,
    private val clock: Clock = Clock.systemDefaultZone(),
) {
    operator fun invoke(
        period: ActivityPeriod,
        anchorDate: LocalDate,
        selectedHabitId: String?,
    ): Flow<AppResult<ActivitySnapshot>> {
        val periodWindow = period.windowFor(anchorDate)
        return habitsRepository.observeHabits().flatMapLatest { habitsResult ->
            when (habitsResult) {
                is AppResult.Failure -> flowOf(habitsResult)
                is AppResult.Success -> observeEntriesForSelection(
                    habits = habitsResult.value,
                    period = period,
                    anchorDate = anchorDate,
                    periodWindow = periodWindow,
                    selectedHabitId = selectedHabitId,
                )
            }
        }
    }

    private fun observeEntriesForSelection(
        habits: List<Habit>,
        period: ActivityPeriod,
        anchorDate: LocalDate,
        periodWindow: ActivityPeriodWindow,
        selectedHabitId: String?,
    ): Flow<AppResult<ActivitySnapshot>> {
        val availableHabitFilters = habits.toHabitFilterOptions()
        val resolvedSelectedHabitId = selectedHabitId.takeIf { requestedHabitId ->
            habits.any { habit -> habit.id == requestedHabitId }
        }
        val filteredHabits = if (resolvedSelectedHabitId == null) {
            habits
        } else {
            habits.filter { habit -> habit.id == resolvedSelectedHabitId }
        }

        val entryFlows = filteredHabits.map { habit ->
            habitsRepository.observeHabitEntries(
                habitId = habit.id,
                startDate = periodWindow.startDate,
                endDate = periodWindow.endDate,
            )
        }

        val combinedEntriesFlow: Flow<List<AppResult<List<HabitEntry>>>> = if (entryFlows.isEmpty()) {
            flowOf(emptyList())
        } else {
            combine(entryFlows) { entryResults ->
                entryResults.toList()
            }
        }

        return combinedEntriesFlow.map { entryResults ->
            val failure = entryResults.firstOrNull { result -> result is AppResult.Failure } as? AppResult.Failure
            if (failure != null) {
                failure
            } else {
                val filteredEntries = entryResults
                    .mapNotNull { result -> (result as? AppResult.Success)?.value }
                    .flatten()

                AppResult.Success(
                    buildSnapshot(
                        period = period,
                        anchorDate = anchorDate,
                        periodWindow = periodWindow,
                        habits = habits,
                        filteredHabits = filteredHabits,
                        filteredEntries = filteredEntries,
                        selectedHabitId = resolvedSelectedHabitId,
                        availableHabitFilters = availableHabitFilters,
                    ),
                )
            }
        }
    }

    private fun buildSnapshot(
        period: ActivityPeriod,
        anchorDate: LocalDate,
        periodWindow: ActivityPeriodWindow,
        habits: List<Habit>,
        filteredHabits: List<Habit>,
        filteredEntries: List<HabitEntry>,
        selectedHabitId: String?,
        availableHabitFilters: List<ActivityHabitFilterOption>,
    ): ActivitySnapshot {
        val zoneId = clock.zone
        val dates = datesBetween(periodWindow.startDate, periodWindow.endDate)
        val progressByDateAndHabitId = filteredEntries
            .groupBy(HabitEntry::entryDate)
            .mapValues { (_, dateEntries) ->
                dateEntries.groupBy(HabitEntry::habitId).mapValues { (_, habitEntries) ->
                    habitEntries.sumOf(HabitEntry::value)
                }
            }

        val chartData = dates.map { date ->
            val scheduledHabits = filteredHabits.filter { habit ->
                habitWasScheduledOn(
                    habit = habit,
                    date = date,
                    zoneId = zoneId,
                )
            }
            val dailyProgress = progressByDateAndHabitId[date].orEmpty()
            val completionRatios = scheduledHabits.map { habit ->
                progressRatio(
                    progressValue = dailyProgress[habit.id] ?: 0.0,
                    targetValue = habit.targetValue,
                )
            }

            ActivityChartPoint(
                date = date,
                totalProgress = dailyProgress.values.sum(),
                completionRatio = if (completionRatios.isEmpty()) {
                    0.0
                } else {
                    completionRatios.average()
                },
                isDoneDay = completionRatios.isNotEmpty() && completionRatios.all { ratio -> ratio >= 1.0 },
            )
        }

        val summaryCompletionRatios = dates.flatMap { date ->
            val dailyProgress = progressByDateAndHabitId[date].orEmpty()
            filteredHabits.mapNotNull { habit ->
                if (!habitWasScheduledOn(habit = habit, date = date, zoneId = zoneId)) {
                    null
                } else {
                    progressRatio(
                        progressValue = dailyProgress[habit.id] ?: 0.0,
                        targetValue = habit.targetValue,
                    )
                }
            }
        }

        return ActivitySnapshot(
            selectedPeriod = period,
            periodAnchorDate = anchorDate,
            periodStartDate = periodWindow.startDate,
            periodEndDate = periodWindow.endDate,
            selectedHabitId = selectedHabitId,
            availableHabitFilters = availableHabitFilters,
            chartData = chartData,
            analyticsSummary = ActivityAnalyticsSummary(
                averageCompletionRatio = if (summaryCompletionRatios.isEmpty()) {
                    0.0
                } else {
                    summaryCompletionRatios.average()
                },
                doneDays = chartData.count(ActivityChartPoint::isDoneDay),
                totalProgress = filteredEntries.sumOf(HabitEntry::value),
            ),
        )
    }
}

private fun List<Habit>.toHabitFilterOptions(): List<ActivityHabitFilterOption> {
    if (isEmpty()) {
        return emptyList()
    }

    return buildList {
        add(ActivityHabitFilterOption(habitId = null, name = "All Habits"))
        addAll(
            this@toHabitFilterOptions.map { habit ->
                ActivityHabitFilterOption(
                    habitId = habit.id,
                    name = habit.name,
                )
            },
        )
    }
}
