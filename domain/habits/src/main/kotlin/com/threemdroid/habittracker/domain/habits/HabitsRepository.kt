package com.threemdroid.habittracker.domain.habits

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.Reminder
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    fun observeHabits(): Flow<AppResult<List<Habit>>>

    fun observeHabit(habitId: String): Flow<AppResult<Habit?>>

    fun observeHabitEntries(habitId: String): Flow<AppResult<List<HabitEntry>>>

    fun observeHabitEntries(
        habitId: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<AppResult<List<HabitEntry>>>

    suspend fun upsertHabit(habit: Habit): AppResult<Unit>

    suspend fun deleteHabit(habitId: String): AppResult<Unit>

    suspend fun upsertHabitEntry(habitEntry: HabitEntry): AppResult<Unit>

    suspend fun deleteHabitEntry(habitEntryId: String): AppResult<Unit>

    suspend fun upsertReminder(reminder: Reminder): AppResult<Unit>

    suspend fun deleteReminder(reminderId: String): AppResult<Unit>
}
