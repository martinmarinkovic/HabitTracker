package com.threemdroid.habittracker.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.threemdroid.habittracker.core.database.model.HabitEntryEntity
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitEntryDao {
    @Query(
        """
        SELECT * FROM habit_entries
        WHERE habit_id = :habitId
        ORDER BY logged_at_epoch_millis DESC
        """,
    )
    fun observeEntriesForHabit(habitId: String): Flow<List<HabitEntryEntity>>

    @Query(
        """
        SELECT * FROM habit_entries
        WHERE habit_id = :habitId
        AND entry_date_epoch_day BETWEEN :startDate AND :endDate
        ORDER BY logged_at_epoch_millis DESC
        """,
    )
    fun observeEntriesForHabit(
        habitId: String,
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<List<HabitEntryEntity>>

    @Query(
        """
        SELECT * FROM habit_entries
        WHERE entry_date_epoch_day = :date
        ORDER BY logged_at_epoch_millis DESC
        """,
    )
    fun observeEntriesForDate(date: LocalDate): Flow<List<HabitEntryEntity>>

    @Upsert
    suspend fun upsertEntry(habitEntry: HabitEntryEntity)

    @Query("DELETE FROM habit_entries WHERE entry_id = :habitEntryId")
    suspend fun deleteEntry(habitEntryId: String)

    @Query("SELECT COUNT(*) FROM habit_entries")
    fun observeTotalEntryCount(): Flow<Int>
}
