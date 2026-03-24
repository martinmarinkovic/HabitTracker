package com.threemdroid.habittracker.data.activity.repository

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.asAppResult
import com.threemdroid.habittracker.core.common.result.suspendAppResultOf
import com.threemdroid.habittracker.core.database.dao.MoodEntryDao
import com.threemdroid.habittracker.core.model.activity.MoodEntry
import com.threemdroid.habittracker.data.activity.mapper.toEntity
import com.threemdroid.habittracker.data.activity.mapper.toModel
import com.threemdroid.habittracker.domain.activity.ActivityRepository
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ActivityRepositoryImpl(
    private val moodEntryDao: MoodEntryDao,
) : ActivityRepository {
    override fun observeMoodEntries(): Flow<AppResult<List<MoodEntry>>> =
        moodEntryDao.observeMoodEntries()
            .map { entries -> entries.map { it.toModel() } }
            .asAppResult { throwable ->
                AppError.Storage(
                    source = "MoodEntryDao.observeMoodEntries",
                    message = throwable.message,
                    cause = throwable,
                )
            }

    override fun observeMoodEntriesForDate(date: LocalDate): Flow<AppResult<List<MoodEntry>>> =
        moodEntryDao.observeMoodEntriesForDate(date)
            .map { entries -> entries.map { it.toModel() } }
            .asAppResult { throwable ->
                AppError.Storage(
                    source = "MoodEntryDao.observeMoodEntriesForDate",
                    message = throwable.message,
                    cause = throwable,
                )
            }

    override suspend fun upsertMoodEntry(moodEntry: MoodEntry): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "MoodEntryDao.upsertMoodEntry",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        moodEntryDao.upsertMoodEntry(moodEntry.toEntity())
    }

    override suspend fun deleteMoodEntry(moodEntryId: String): AppResult<Unit> = suspendAppResultOf(
        mapError = { throwable ->
            AppError.Storage(
                source = "MoodEntryDao.deleteMoodEntry",
                message = throwable.message,
                cause = throwable,
            )
        },
    ) {
        moodEntryDao.deleteMoodEntry(moodEntryId)
    }
}
