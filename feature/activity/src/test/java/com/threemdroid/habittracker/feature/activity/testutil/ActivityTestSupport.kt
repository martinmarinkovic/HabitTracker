package com.threemdroid.habittracker.feature.activity.testutil

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.getOrNull
import com.threemdroid.habittracker.core.common.result.map
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.HabitSchedule
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.core.model.habits.Reminder
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase
import java.time.Clock
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

val ACTIVITY_TEST_DATE: LocalDate = LocalDate.of(2026, 3, 24)
val ACTIVITY_TEST_INSTANT: Instant = Instant.parse("2026-03-24T08:00:00Z")
val ACTIVITY_TEST_ZONE: ZoneId = ZoneId.of("Europe/Belgrade")

fun activityTestClock(): Clock = Clock.fixed(ACTIVITY_TEST_INSTANT, ACTIVITY_TEST_ZONE)

fun activityTestHabit(
    id: String = "habit-1",
    name: String = "Walk",
    type: HabitType = HabitType.QUANTITY,
    targetValue: Double = if (type == HabitType.BOOLEAN) 1.0 else 2.0,
    defaultIncrement: Double = 1.0,
    unitLabel: String? = if (type == HabitType.QUANTITY) "km" else null,
    allowsMultipleUpdatesPerDay: Boolean = type == HabitType.QUANTITY,
    selectedDays: Set<DayOfWeek> = emptySet(),
    state: HabitState = HabitState.ACTIVE,
    createdAt: Instant = ACTIVITY_TEST_INSTANT.minusSeconds(86_400),
    stoppedAt: Instant? = null,
): Habit = Habit(
    id = id,
    name = name,
    type = type,
    targetValue = targetValue,
    defaultIncrement = defaultIncrement,
    unitLabel = unitLabel,
    allowsMultipleUpdatesPerDay = allowsMultipleUpdatesPerDay,
    selectedIconToken = "shoe",
    selectedColorToken = "green",
    schedule = HabitSchedule(selectedDays = selectedDays),
    reminders = emptyList(),
    state = state,
    createdAt = createdAt,
    stoppedAt = stoppedAt,
)

fun activityTestEntry(
    id: String,
    habitId: String,
    entryDate: LocalDate,
    value: Double,
    loggedAt: Instant = ACTIVITY_TEST_INSTANT,
): HabitEntry = HabitEntry(
    id = id,
    habitId = habitId,
    entryDate = entryDate,
    loggedAt = loggedAt,
    value = value,
)

internal fun activityObserveUseCase(
    repository: TestActivityHabitsRepository,
): ObserveActivityDataUseCase = ObserveActivityDataUseCase(
    habitsRepository = repository,
    clock = activityTestClock(),
)

class TestActivityHabitsRepository : HabitsRepository {
    private val habitsState = MutableStateFlow<AppResult<List<Habit>>>(AppResult.Success(emptyList()))
    private val entriesState = MutableStateFlow<AppResult<List<HabitEntry>>>(AppResult.Success(emptyList()))
    private val rangeEntryErrors = mutableMapOf<String, AppError>()

    override fun observeHabits(): Flow<AppResult<List<Habit>>> = habitsState

    override fun observeHabit(habitId: String): Flow<AppResult<Habit?>> = habitsState.map { result ->
        result.map { habits ->
            habits.firstOrNull { habit -> habit.id == habitId }
        }
    }

    override fun observeHabitEntries(habitId: String): Flow<AppResult<List<HabitEntry>>> = entriesState.map { result ->
        result.map { entries ->
            entries.filter { entry -> entry.habitId == habitId }
        }
    }

    override fun observeHabitEntries(date: LocalDate): Flow<AppResult<List<HabitEntry>>> = entriesState.map { result ->
        result.map { entries ->
            entries.filter { entry -> entry.entryDate == date }
        }
    }

    override fun observeHabitEntries(
        habitId: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<AppResult<List<HabitEntry>>> = entriesState.map { result ->
        rangeEntryErrors[habitId]?.let { error ->
            return@map AppResult.Failure(error)
        }

        result.map { entries ->
            entries.filter { entry ->
                entry.habitId == habitId &&
                    !entry.entryDate.isBefore(startDate) &&
                    !entry.entryDate.isAfter(endDate)
            }
        }
    }

    override suspend fun upsertHabit(habit: Habit): AppResult<Unit> = AppResult.Success(Unit)

    override suspend fun deleteHabit(habitId: String): AppResult<Unit> = AppResult.Success(Unit)

    override suspend fun upsertHabitEntry(habitEntry: HabitEntry): AppResult<Unit> = AppResult.Success(Unit)

    override suspend fun deleteHabitEntry(habitEntryId: String): AppResult<Unit> = AppResult.Success(Unit)

    override suspend fun upsertReminder(reminder: Reminder): AppResult<Unit> = AppResult.Success(Unit)

    override suspend fun deleteReminder(reminderId: String): AppResult<Unit> = AppResult.Success(Unit)

    fun setHabits(habits: List<Habit>) {
        habitsState.value = AppResult.Success(habits)
    }

    fun setHabitsFailure(error: AppError) {
        habitsState.value = AppResult.Failure(error)
    }

    fun setEntries(entries: List<HabitEntry>) {
        entriesState.value = AppResult.Success(entries)
    }

    fun setEntriesFailure(error: AppError) {
        entriesState.value = AppResult.Failure(error)
    }

    fun setRangeEntryError(
        habitId: String,
        error: AppError,
    ) {
        rangeEntryErrors[habitId] = error
    }

    fun currentHabits(): List<Habit> = habitsState.value.getOrNull().orEmpty()
}
