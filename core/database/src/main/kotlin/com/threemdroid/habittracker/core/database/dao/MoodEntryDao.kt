package com.threemdroid.habittracker.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.threemdroid.habittracker.core.database.model.MoodEntryEntity
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodEntryDao {
    @Query("SELECT * FROM mood_entries ORDER BY logged_at_epoch_millis DESC")
    fun observeMoodEntries(): Flow<List<MoodEntryEntity>>

    @Query(
        """
        SELECT * FROM mood_entries
        WHERE entry_date_epoch_day = :date
        ORDER BY logged_at_epoch_millis DESC
        """,
    )
    fun observeMoodEntriesForDate(date: LocalDate): Flow<List<MoodEntryEntity>>

    @Upsert
    suspend fun upsertMoodEntry(moodEntry: MoodEntryEntity)

    @Query("DELETE FROM mood_entries WHERE mood_entry_id = :moodEntryId")
    suspend fun deleteMoodEntry(moodEntryId: String)

    @Query("SELECT COUNT(*) FROM mood_entries")
    fun observeTotalMoodEntryCount(): Flow<Int>
}
