package com.threemdroid.habittracker.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.threemdroid.habittracker.core.model.habits.HabitState
import com.threemdroid.habittracker.core.model.habits.HabitType
import java.time.DayOfWeek
import java.time.Instant

@Entity(
    tableName = "habits",
    indices = [
        Index(value = ["state"]),
        Index(value = ["created_at_epoch_millis"]),
    ],
)
data class HabitEntity(
    @PrimaryKey
    @ColumnInfo(name = "habit_id")
    val habitId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "habit_type")
    val habitType: HabitType,
    @ColumnInfo(name = "target_value")
    val targetValue: Double,
    @ColumnInfo(name = "default_increment")
    val defaultIncrement: Double,
    @ColumnInfo(name = "unit_label")
    val unitLabel: String?,
    @ColumnInfo(name = "allows_multiple_updates_per_day")
    val allowsMultipleUpdatesPerDay: Boolean,
    @ColumnInfo(name = "selected_icon_token")
    val selectedIconToken: String,
    @ColumnInfo(name = "selected_color_token")
    val selectedColorToken: String,
    @ColumnInfo(name = "selected_days")
    val selectedDays: Set<DayOfWeek>,
    @ColumnInfo(name = "state")
    val state: HabitState,
    @ColumnInfo(name = "created_at_epoch_millis")
    val createdAt: Instant,
    @ColumnInfo(name = "stopped_at_epoch_millis")
    val stoppedAt: Instant?,
)
