package com.threemdroid.habittracker.core.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.threemdroid.habittracker.core.database.model.HabitEntity
import com.threemdroid.habittracker.core.database.model.ReminderEntity

data class HabitWithReminders(
    @Embedded
    val habit: HabitEntity,
    @Relation(
        parentColumn = "habit_id",
        entityColumn = "habit_id",
    )
    val reminders: List<ReminderEntity>,
)
