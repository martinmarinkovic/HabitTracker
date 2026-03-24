package com.threemdroid.habittracker.feature.home.testutil

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.getOrNull
import com.threemdroid.habittracker.core.common.result.map
import com.threemdroid.habittracker.core.model.activity.MoodEntry
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.HabitSchedule
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.core.model.habits.Reminder
import com.threemdroid.habittracker.domain.activity.ActivityRepository
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import java.time.Clock
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

val HOME_TEST_DATE: LocalDate = LocalDate.of(2026, 3, 24)
val HOME_TEST_INSTANT: Instant = Instant.parse("2026-03-24T08:00:00Z")
val HOME_TEST_ZONE: ZoneId = ZoneId.of("Europe/Belgrade")

fun homeTestClock(): Clock = Clock.fixed(HOME_TEST_INSTANT, HOME_TEST_ZONE)

fun homeTestHabit(
    id: String = "habit-1",
    name: String = "Walk",
    type: HabitType = HabitType.BOOLEAN,
    targetValue: Double = 1.0,
    defaultIncrement: Double = 1.0,
    unitLabel: String? = if (type == HabitType.QUANTITY) "km" else null,
    allowsMultipleUpdatesPerDay: Boolean = type == HabitType.QUANTITY,
    selectedIconToken: String = "shoe",
    selectedColorToken: String = "green",
    selectedDays: Set<DayOfWeek> = setOf(HOME_TEST_DATE.dayOfWeek),
    reminders: List<Reminder> = emptyList(),
    state: HabitState = HabitState.ACTIVE,
    createdAt: Instant = HOME_TEST_INSTANT,
    stoppedAt: Instant? = null,
): Habit = Habit(
    id = id,
    name = name,
    type = type,
    targetValue = targetValue,
    defaultIncrement = defaultIncrement,
    unitLabel = unitLabel,
    allowsMultipleUpdatesPerDay = allowsMultipleUpdatesPerDay,
    selectedIconToken = selectedIconToken,
    selectedColorToken = selectedColorToken,
    schedule = HabitSchedule(selectedDays = selectedDays),
    reminders = reminders,
    state = state,
    createdAt = createdAt,
    stoppedAt = stoppedAt,
)

fun homeTestHabitEntry(
    id: String = "entry-1",
    habitId: String = "habit-1",
    entryDate: LocalDate = HOME_TEST_DATE,
    loggedAt: Instant = HOME_TEST_INSTANT,
    value: Double = 1.0,
): HabitEntry = HabitEntry(
    id = id,
    habitId = habitId,
    entryDate = entryDate,
    loggedAt = loggedAt,
    value = value,
)

fun homeTestMoodEntry(
    id: String = "mood-$HOME_TEST_DATE",
    entryDate: LocalDate = HOME_TEST_DATE,
    loggedAt: Instant = HOME_TEST_INSTANT,
    moodToken: String = "focused",
): MoodEntry = MoodEntry(
    id = id,
    entryDate = entryDate,
    loggedAt = loggedAt,
    moodToken = moodToken,
)

class TestHomeHabitsRepository : HabitsRepository {
    private val habitsState = MutableStateFlow<AppResult<List<Habit>>>(AppResult.Success(emptyList()))
    private val entriesState = MutableStateFlow<AppResult<List<HabitEntry>>>(AppResult.Success(emptyList()))

    var upsertHabitResult: AppResult<Unit> = AppResult.Success(Unit)
    var upsertHabitEntryResult: AppResult<Unit> = AppResult.Success(Unit)
    var upsertHabitCallCount: Int = 0
        private set
    var upsertHabitEntryCallCount: Int = 0
        private set

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
        result.map { entries ->
            entries.filter { entry ->
                entry.habitId == habitId &&
                    !entry.entryDate.isBefore(startDate) &&
                    !entry.entryDate.isAfter(endDate)
            }
        }
    }

    override suspend fun upsertHabit(habit: Habit): AppResult<Unit> {
        upsertHabitCallCount += 1
        return when (val result = upsertHabitResult) {
            is AppResult.Failure -> result
            is AppResult.Success -> {
                habitsState.value = AppResult.Success(
                    currentHabits()
                        .filterNot { existing -> existing.id == habit.id } + habit,
                )
                result
            }
        }
    }

    override suspend fun deleteHabit(habitId: String): AppResult<Unit> {
        habitsState.value = AppResult.Success(
            currentHabits().filterNot { habit -> habit.id == habitId },
        )
        return AppResult.Success(Unit)
    }

    override suspend fun upsertHabitEntry(habitEntry: HabitEntry): AppResult<Unit> {
        upsertHabitEntryCallCount += 1
        return when (val result = upsertHabitEntryResult) {
            is AppResult.Failure -> result
            is AppResult.Success -> {
                entriesState.value = AppResult.Success(
                    currentEntries()
                        .filterNot { existing -> existing.id == habitEntry.id } + habitEntry,
                )
                result
            }
        }
    }

    override suspend fun deleteHabitEntry(habitEntryId: String): AppResult<Unit> {
        entriesState.value = AppResult.Success(
            currentEntries().filterNot { entry -> entry.id == habitEntryId },
        )
        return AppResult.Success(Unit)
    }

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

    fun currentHabits(): List<Habit> = habitsState.value.getOrNull().orEmpty()

    fun currentEntries(): List<HabitEntry> = entriesState.value.getOrNull().orEmpty()
}

class TestHomeActivityRepository : ActivityRepository {
    private val moodEntriesState = MutableStateFlow<AppResult<List<MoodEntry>>>(AppResult.Success(emptyList()))

    var upsertMoodEntryResult: AppResult<Unit> = AppResult.Success(Unit)
    var upsertMoodEntryCallCount: Int = 0
        private set

    override fun observeMoodEntries(): Flow<AppResult<List<MoodEntry>>> = moodEntriesState

    override fun observeMoodEntriesForDate(date: LocalDate): Flow<AppResult<List<MoodEntry>>> = moodEntriesState.map { result ->
        result.map { moodEntries ->
            moodEntries.filter { moodEntry -> moodEntry.entryDate == date }
        }
    }

    override suspend fun upsertMoodEntry(moodEntry: MoodEntry): AppResult<Unit> {
        upsertMoodEntryCallCount += 1
        return when (val result = upsertMoodEntryResult) {
            is AppResult.Failure -> result
            is AppResult.Success -> {
                moodEntriesState.value = AppResult.Success(
                    currentMoodEntries()
                        .filterNot { existing -> existing.id == moodEntry.id } + moodEntry,
                )
                result
            }
        }
    }

    override suspend fun deleteMoodEntry(moodEntryId: String): AppResult<Unit> {
        moodEntriesState.value = AppResult.Success(
            currentMoodEntries().filterNot { moodEntry -> moodEntry.id == moodEntryId },
        )
        return AppResult.Success(Unit)
    }

    fun setMoodEntries(moodEntries: List<MoodEntry>) {
        moodEntriesState.value = AppResult.Success(moodEntries)
    }

    fun setMoodEntriesFailure(error: AppError) {
        moodEntriesState.value = AppResult.Failure(error)
    }

    fun currentMoodEntries(): List<MoodEntry> = moodEntriesState.value.getOrNull().orEmpty()
}
