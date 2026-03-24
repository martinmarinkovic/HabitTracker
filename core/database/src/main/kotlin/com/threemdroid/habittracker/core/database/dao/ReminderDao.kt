package com.threemdroid.habittracker.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.threemdroid.habittracker.core.database.model.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query(
        """
        SELECT * FROM reminders
        WHERE habit_id = :habitId
        ORDER BY time_second_of_day ASC
        """,
    )
    fun observeRemindersForHabit(habitId: String): Flow<List<ReminderEntity>>

    @Upsert
    suspend fun upsertReminder(reminder: ReminderEntity)

    @Query("DELETE FROM reminders WHERE reminder_id = :reminderId")
    suspend fun deleteReminder(reminderId: String)

    @Query("SELECT COUNT(*) FROM reminders WHERE is_enabled = 1")
    fun observeEnabledReminderCount(): Flow<Int>
}
