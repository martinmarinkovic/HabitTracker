package com.threemdroid.habittracker.feature.create_habit.testutil

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.HabitSchedule
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.core.model.habits.Reminder
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitUiState
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitDraft
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitReminderDraft
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitUseCase
import com.threemdroid.habittracker.feature.create_habit.domain.ValidateCreateHabitDraftUseCase
import com.threemdroid.habittracker.feature.create_habit.domain.resolveSelectedDays
import java.time.Clock
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val CREATE_HABIT_TEST_DATE: LocalDate = LocalDate.of(2026, 3, 24)
val CREATE_HABIT_TEST_INSTANT: Instant = Instant.parse("2026-03-24T08:00:00Z")
val CREATE_HABIT_TEST_ZONE: ZoneId = ZoneId.of("Europe/Belgrade")

fun createHabitTestClock(): Clock = Clock.fixed(CREATE_HABIT_TEST_INSTANT, CREATE_HABIT_TEST_ZONE)

fun createHabitDraft(
    name: String = "Walk",
    selectedIconToken: String? = "shoe",
    selectedColorToken: String? = "green",
    habitType: HabitType = HabitType.QUANTITY,
    targetValueInput: String = "5",
    defaultIncrementInput: String = "1",
    unitLabelInput: String = "km",
    allowsMultipleUpdatesPerDay: Boolean = false,
    schedulePreset: CreateHabitSchedulePreset = CreateHabitSchedulePreset.EVERY_DAY,
    selectedDays: Set<DayOfWeek> = resolveSelectedDays(
        preset = schedulePreset,
        customSelectedDays = setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
    ),
    reminders: List<CreateHabitReminderDraft> = emptyList(),
): CreateHabitDraft = CreateHabitDraft(
    name = name,
    selectedIconToken = selectedIconToken,
    selectedColorToken = selectedColorToken,
    habitType = habitType,
    targetValueInput = targetValueInput,
    defaultIncrementInput = defaultIncrementInput,
    unitLabelInput = unitLabelInput,
    allowsMultipleUpdatesPerDay = allowsMultipleUpdatesPerDay,
    schedulePreset = schedulePreset,
    selectedDays = selectedDays,
    reminders = reminders,
)

fun createHabitUiState(
    selectedDays: Set<DayOfWeek> = resolveSelectedDays(
        preset = CreateHabitSchedulePreset.EVERY_DAY,
        customSelectedDays = emptySet(),
    ),
): CreateHabitUiState = CreateHabitUiState.initial(selectedDays).copy(
    selectedIconToken = "shoe",
    selectedColorToken = "green",
)

fun createHabitReminderItem(
    reminderId: String = "reminder-1",
    time: LocalTime = LocalTime.of(9, 0),
    isEnabled: Boolean = true,
): CreateHabitReminderItem = CreateHabitReminderItem(
    reminderId = reminderId,
    time = time,
    isEnabled = isEnabled,
)

fun createHabitReminderDraft(
    reminderId: String = "reminder-1",
    time: LocalTime = LocalTime.of(9, 0),
    isEnabled: Boolean = true,
): CreateHabitReminderDraft = CreateHabitReminderDraft(
    reminderId = reminderId,
    time = time,
    isEnabled = isEnabled,
)

class TestCreateHabitHabitsRepository : HabitsRepository {
    var upsertHabitResult: AppResult<Unit> = AppResult.Success(Unit)
    private val reminderResults = ArrayDeque<AppResult<Unit>>()

    val savedHabits = mutableListOf<Habit>()
    val savedReminders = mutableListOf<Reminder>()
    val deletedHabitIds = mutableListOf<String>()

    override fun observeHabits(): Flow<AppResult<List<Habit>>> = flowOf(AppResult.Success(savedHabits.toList()))

    override fun observeHabit(habitId: String): Flow<AppResult<Habit?>> = flowOf(
        AppResult.Success(savedHabits.firstOrNull { habit -> habit.id == habitId }),
    )

    override fun observeHabitEntries(habitId: String): Flow<AppResult<List<HabitEntry>>> =
        flowOf(AppResult.Success(emptyList()))

    override fun observeHabitEntries(date: LocalDate): Flow<AppResult<List<HabitEntry>>> =
        flowOf(AppResult.Success(emptyList()))

    override fun observeHabitEntries(
        habitId: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<AppResult<List<HabitEntry>>> = flowOf(AppResult.Success(emptyList()))

    override suspend fun upsertHabit(habit: Habit): AppResult<Unit> {
        return when (val result = upsertHabitResult) {
            is AppResult.Failure -> result
            is AppResult.Success -> {
                savedHabits.removeAll { existing -> existing.id == habit.id }
                savedHabits += habit
                result
            }
        }
    }

    override suspend fun deleteHabit(habitId: String): AppResult<Unit> {
        deletedHabitIds += habitId
        savedHabits.removeAll { habit -> habit.id == habitId }
        savedReminders.removeAll { reminder -> reminder.habitId == habitId }
        return AppResult.Success(Unit)
    }

    override suspend fun upsertHabitEntry(habitEntry: HabitEntry): AppResult<Unit> = AppResult.Success(Unit)

    override suspend fun deleteHabitEntry(habitEntryId: String): AppResult<Unit> = AppResult.Success(Unit)

    override suspend fun upsertReminder(reminder: Reminder): AppResult<Unit> {
        val result = reminderResults.removeFirstOrNull() ?: AppResult.Success(Unit)
        return when (result) {
            is AppResult.Failure -> result
            is AppResult.Success -> {
                savedReminders.removeAll { existing -> existing.id == reminder.id }
                savedReminders += reminder
                result
            }
        }
    }

    override suspend fun deleteReminder(reminderId: String): AppResult<Unit> {
        savedReminders.removeAll { reminder -> reminder.id == reminderId }
        return AppResult.Success(Unit)
    }

    fun enqueueReminderResult(result: AppResult<Unit>) {
        reminderResults.addLast(result)
    }
}

internal fun createHabitValidator(): ValidateCreateHabitDraftUseCase = ValidateCreateHabitDraftUseCase()

internal fun createHabitUseCase(
    repository: TestCreateHabitHabitsRepository,
    habitIdGenerator: () -> String = { "habit-created-id" },
): CreateHabitUseCase = CreateHabitUseCase(
    habitsRepository = repository,
    clock = createHabitTestClock(),
    habitIdGenerator = habitIdGenerator,
)

fun testSavedQuantityHabit(
    id: String = "habit-created-id",
): Habit = Habit(
    id = id,
    name = "Walk",
    type = HabitType.QUANTITY,
    targetValue = 5.0,
    defaultIncrement = 1.0,
    unitLabel = "km",
    allowsMultipleUpdatesPerDay = false,
    selectedIconToken = "shoe",
    selectedColorToken = "green",
    schedule = HabitSchedule(resolveSelectedDays(CreateHabitSchedulePreset.EVERY_DAY, emptySet())),
    reminders = emptyList(),
    state = com.threemdroid.habittracker.core.model.habits.HabitState.ACTIVE,
    createdAt = CREATE_HABIT_TEST_INSTANT,
    stoppedAt = null,
)

fun reminderScheduleDays(
    preset: CreateHabitSchedulePreset,
    selectedDays: Set<DayOfWeek>,
): Set<DayOfWeek> = resolveSelectedDays(
    preset = preset,
    customSelectedDays = selectedDays,
)

fun storageError(
    message: String,
): AppResult.Failure = AppResult.Failure(
    AppError.Storage(
        source = "TestCreateHabitHabitsRepository",
        message = message,
    ),
)
