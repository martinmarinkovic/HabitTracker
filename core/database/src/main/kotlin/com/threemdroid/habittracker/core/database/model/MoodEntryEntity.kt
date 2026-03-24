package com.threemdroid.habittracker.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate

@Entity(
    tableName = "mood_entries",
    indices = [
        Index(value = ["entry_date_epoch_day"]),
        Index(value = ["logged_at_epoch_millis"]),
    ],
)
data class MoodEntryEntity(
    @PrimaryKey
    @ColumnInfo(name = "mood_entry_id")
    val moodEntryId: String,
    @ColumnInfo(name = "entry_date_epoch_day")
    val entryDate: LocalDate,
    @ColumnInfo(name = "logged_at_epoch_millis")
    val loggedAt: Instant,
    @ColumnInfo(name = "mood_token")
    val moodToken: String,
)
