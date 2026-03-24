package com.threemdroid.habittracker.domain.activity

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.activity.MoodEntry
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    fun observeMoodEntries(): Flow<AppResult<List<MoodEntry>>>

    fun observeMoodEntriesForDate(date: LocalDate): Flow<AppResult<List<MoodEntry>>>

    suspend fun upsertMoodEntry(moodEntry: MoodEntry): AppResult<Unit>

    suspend fun deleteMoodEntry(moodEntryId: String): AppResult<Unit>
}
