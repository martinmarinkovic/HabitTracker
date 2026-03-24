package com.threemdroid.habittracker.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.threemdroid.habittracker.core.database.model.HabitEntity
import com.threemdroid.habittracker.core.database.relation.HabitWithReminders
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Transaction
    @Query("SELECT * FROM habits ORDER BY created_at_epoch_millis DESC")
    fun observeHabitsWithReminders(): Flow<List<HabitWithReminders>>

    @Transaction
    @Query("SELECT * FROM habits WHERE habit_id = :habitId LIMIT 1")
    fun observeHabitWithReminders(habitId: String): Flow<HabitWithReminders?>

    @Upsert
    suspend fun upsertHabit(habit: HabitEntity)

    @Query("DELETE FROM habits WHERE habit_id = :habitId")
    suspend fun deleteHabit(habitId: String)

    @Query("SELECT COUNT(*) FROM habits")
    fun observeTotalHabitCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM habits WHERE state = 'ACTIVE'")
    fun observeActiveHabitCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM habits WHERE state = 'STOPPED'")
    fun observeStoppedHabitCount(): Flow<Int>
}
