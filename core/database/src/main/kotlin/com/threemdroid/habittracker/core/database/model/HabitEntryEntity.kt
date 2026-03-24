package com.threemdroid.habittracker.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDate

@Entity(
    tableName = "habit_entries",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = ["habit_id"],
            childColumns = ["habit_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["habit_id"]),
        Index(value = ["habit_id", "entry_date_epoch_day"]),
        Index(value = ["logged_at_epoch_millis"]),
    ],
)
data class HabitEntryEntity(
    @PrimaryKey
    @ColumnInfo(name = "entry_id")
    val entryId: String,
    @ColumnInfo(name = "habit_id")
    val habitId: String,
    @ColumnInfo(name = "entry_date_epoch_day")
    val entryDate: LocalDate,
    @ColumnInfo(name = "logged_at_epoch_millis")
    val loggedAt: Instant,
    @ColumnInfo(name = "value")
    val value: Double,
)
