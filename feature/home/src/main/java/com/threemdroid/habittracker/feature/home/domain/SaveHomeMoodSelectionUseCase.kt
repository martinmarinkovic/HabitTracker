package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.activity.MoodEntry
import com.threemdroid.habittracker.domain.activity.ActivityRepository
import java.time.Clock
import java.time.LocalDate
import kotlinx.coroutines.flow.first

class SaveHomeMoodSelectionUseCase(
    private val activityRepository: ActivityRepository,
    private val clock: Clock,
) {
    suspend operator fun invoke(
        entryDate: LocalDate,
        moodToken: String,
    ): AppResult<Unit> {
        if (moodToken.isBlank()) {
            return AppResult.Failure(
                AppError.Validation(message = "Mood token must not be blank."),
            )
        }

        val existingEntries = when (val moodEntriesResult = activityRepository.observeMoodEntriesForDate(entryDate).first()) {
            is AppResult.Failure -> return moodEntriesResult
            is AppResult.Success -> moodEntriesResult.value
        }

        val moodEntryId = existingEntries.firstOrNull()?.id ?: "mood-$entryDate"

        return activityRepository.upsertMoodEntry(
            MoodEntry(
                id = moodEntryId,
                entryDate = entryDate,
                loggedAt = clock.instant(),
                moodToken = moodToken,
            ),
        )
    }
}
