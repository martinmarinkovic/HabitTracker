package com.threemdroid.habittracker.data.activity.mapper

import com.threemdroid.habittracker.core.database.model.MoodEntryEntity
import com.threemdroid.habittracker.core.model.activity.MoodEntry

internal fun MoodEntryEntity.toModel(): MoodEntry = MoodEntry(
    id = moodEntryId,
    entryDate = entryDate,
    loggedAt = loggedAt,
    moodToken = moodToken,
)

internal fun MoodEntry.toEntity(): MoodEntryEntity = MoodEntryEntity(
    moodEntryId = id,
    entryDate = entryDate,
    loggedAt = loggedAt,
    moodToken = moodToken,
)
