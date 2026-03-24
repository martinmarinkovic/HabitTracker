package com.threemdroid.habittracker.core.model.habits

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

enum class HabitType {
    BOOLEAN,
    QUANTITY,
}

enum class HabitState {
    ACTIVE,
    STOPPED,
}

data class HabitSchedule(
    val selectedDays: Set<DayOfWeek>,
)

data class Reminder(
    val id: String,
    val habitId: String,
    val time: LocalTime,
    val schedule: HabitSchedule,
    val isEnabled: Boolean,
)

data class Habit(
    val id: String,
    val name: String,
    val type: HabitType,
    val targetValue: Double,
    val defaultIncrement: Double,
    val unitLabel: String?,
    val allowsMultipleUpdatesPerDay: Boolean,
    val selectedIconToken: String,
    val selectedColorToken: String,
    val schedule: HabitSchedule,
    val reminders: List<Reminder>,
    val state: HabitState,
    val createdAt: Instant,
    val stoppedAt: Instant?,
)

data class HabitEntry(
    val id: String,
    val habitId: String,
    val entryDate: LocalDate,
    val loggedAt: Instant,
    val value: Double,
)
