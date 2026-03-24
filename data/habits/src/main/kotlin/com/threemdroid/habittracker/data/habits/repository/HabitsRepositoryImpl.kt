package com.threemdroid.habittracker.data.habits.repository

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.asAppResult
import com.threemdroid.habittracker.core.common.result.suspendAppResultOf
import com.threemdroid.habittracker.core.database.dao.HabitDao
import com.threemdroid.habittracker.core.database.dao.HabitEntryDao
import com.threemdroid.habittracker.core.database.dao.ReminderDao
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.Reminder
import com.threemdroid.habittracker.data.habits.mapper.toEntity
import com.threemdroid.habittracker.data.habits.mapper.toModel
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitsRepositoryImpl(
    private val habitDao: HabitDao,
    private val habitEntryDao: HabitEntryDao,
    private val reminderDao: ReminderDao,
) : HabitsRepository {
    override fun observeHabits(): Flow<AppResult<List<Habit>>> =
        habitDao.observeHabitsWithReminders()
            .map { habitsWithReminders -> habitsWithReminders.map { it.toModel() } }
            .asAppResult { throwable ->
                AppError.Storage(
                    source = "HabitDao.observeHabitsWithReminders",
                    message = throwable.message,
                    cause = throwable,
                )
            }

    override fun observeHabit(habitId: String): Flow<AppResult<Habit?>> =
        habitDao.observeHabitWithReminders(habitId)
            .map { habitWithReminders -> habitWithReminders?.toModel() }
            .asAppResult { throwable ->
                AppError.Storage(
                    source = "HabitDao.observeHabitWithReminders",
                    message = throwable.message,
                    cause = throwable,
                )
            }

    override fun observeHabitEntries(habitId: String): Flow<AppResult<List<HabitEntry>>> =
        habitEntryDao.observeEntriesForHabit(habitId)
            .map { entries -> entries.map { it.toModel() } }
            .asAppResult { throwable ->
                AppError.Storage(
                    source = "HabitEntryDao.observeEntriesForHabit",
                    message = throwable.message,
                    cause = throwable,
                )
            }

    override fun observeHabitEntries(date: LocalDate): Flow<AppResult<List<HabitEntry>>> =
        habitEntryDao.observeEntriesForDate(date)
            .map { entries -> entries.map { it.toModel() } }
            .asAppResult { throwable ->
                AppError.Storage(
                    source = "HabitEntryDao.observeEntriesForDate",
                    message = throwable.message,
                    cause = throwable,
                )
            }

    override fun observeHabitEntries(
        habitId: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<AppResult<List<HabitEntry>>> =
        habitEntryDao.observeEntriesForHabit(
            habitId = habitId,
            startDate = startDate,
            endDate = endDate,
        ).map { entries ->
            entries.map { it.toModel() }
        }.asAppResult { throwable ->
            AppError.Storage(
                source = "HabitEntryDao.observeEntriesForHabitBetweenDates",
                message = throwable.message,
                cause = throwable,
            )
        }

    override suspend fun upsertHabit(habit: Habit): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "HabitDao.upsertHabit",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        habitDao.upsertHabit(habit.toEntity())
    }

    override suspend fun deleteHabit(habitId: String): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "HabitDao.deleteHabit",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        habitDao.deleteHabit(habitId)
    }

    override suspend fun upsertHabitEntry(habitEntry: HabitEntry): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "HabitEntryDao.upsertEntry",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        habitEntryDao.upsertEntry(habitEntry.toEntity())
    }

    override suspend fun deleteHabitEntry(habitEntryId: String): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "HabitEntryDao.deleteEntry",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        habitEntryDao.deleteEntry(habitEntryId)
    }

    override suspend fun upsertReminder(reminder: Reminder): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "ReminderDao.upsertReminder",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        reminderDao.upsertReminder(reminder.toEntity())
    }

    override suspend fun deleteReminder(reminderId: String): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "ReminderDao.deleteReminder",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        reminderDao.deleteReminder(reminderId)
    }
}
