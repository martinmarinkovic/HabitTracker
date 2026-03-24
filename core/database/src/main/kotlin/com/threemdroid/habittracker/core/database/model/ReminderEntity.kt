package com.threemdroid.habittracker.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime

@Entity(
    tableName = "reminders",
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
        Index(value = ["habit_id", "is_enabled"]),
    ],
)
data class ReminderEntity(
    @PrimaryKey
    @ColumnInfo(name = "reminder_id")
    val reminderId: String,
    @ColumnInfo(name = "habit_id")
    val habitId: String,
    @ColumnInfo(name = "time_second_of_day")
    val time: LocalTime,
    @ColumnInfo(name = "selected_days")
    val selectedDays: Set<DayOfWeek>,
    @ColumnInfo(name = "is_enabled")
    val isEnabled: Boolean,
)
