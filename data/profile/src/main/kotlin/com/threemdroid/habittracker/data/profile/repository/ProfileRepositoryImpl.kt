package com.threemdroid.habittracker.data.profile.repository

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.common.result.asAppResult
import com.threemdroid.habittracker.core.database.dao.HabitDao
import com.threemdroid.habittracker.core.database.dao.HabitEntryDao
import com.threemdroid.habittracker.core.database.dao.MoodEntryDao
import com.threemdroid.habittracker.core.database.dao.ReminderDao
import com.threemdroid.habittracker.core.model.profile.ProfileSummary
import com.threemdroid.habittracker.domain.profile.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ProfileRepositoryImpl(
    private val habitDao: HabitDao,
    private val habitEntryDao: HabitEntryDao,
    private val moodEntryDao: MoodEntryDao,
    private val reminderDao: ReminderDao,
) : ProfileRepository {
    override fun observeProfileSummary(): Flow<AppResult<ProfileSummary>> =
        combine(
            combine(
                habitDao.observeTotalHabitCount(),
                habitDao.observeActiveHabitCount(),
                habitDao.observeStoppedHabitCount(),
            ) { totalHabitCount, activeHabitCount, stoppedHabitCount ->
                HabitCounts(
                    totalHabitCount = totalHabitCount,
                    activeHabitCount = activeHabitCount,
                    stoppedHabitCount = stoppedHabitCount,
                )
            },
            combine(
                habitEntryDao.observeTotalEntryCount(),
                moodEntryDao.observeTotalMoodEntryCount(),
                reminderDao.observeEnabledReminderCount(),
            ) { totalHabitEntryCount, totalMoodEntryCount, enabledReminderCount ->
                ActivityCounts(
                    totalHabitEntryCount = totalHabitEntryCount,
                    totalMoodEntryCount = totalMoodEntryCount,
                    enabledReminderCount = enabledReminderCount,
                )
            },
        ) { habitCounts, activityCounts ->
            ProfileSummary(
                totalHabitCount = habitCounts.totalHabitCount,
                activeHabitCount = habitCounts.activeHabitCount,
                stoppedHabitCount = habitCounts.stoppedHabitCount,
                totalHabitEntryCount = activityCounts.totalHabitEntryCount,
                totalMoodEntryCount = activityCounts.totalMoodEntryCount,
                enabledReminderCount = activityCounts.enabledReminderCount,
            )
        }.asAppResult { throwable ->
            AppError.Storage(
                source = "ProfileRepositoryImpl.observeProfileSummary",
                message = throwable.message,
                cause = throwable,
            )
        }
}

private data class HabitCounts(
    val totalHabitCount: Int,
    val activeHabitCount: Int,
    val stoppedHabitCount: Int,
)

private data class ActivityCounts(
    val totalHabitEntryCount: Int,
    val totalMoodEntryCount: Int,
    val enabledReminderCount: Int,
)
