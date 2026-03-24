package com.threemdroid.habittracker.data.habits.mapper

import com.threemdroid.habittracker.core.database.model.HabitEntity
import com.threemdroid.habittracker.core.database.model.HabitEntryEntity
import com.threemdroid.habittracker.core.database.model.ReminderEntity
import com.threemdroid.habittracker.core.database.relation.HabitWithReminders
import com.threemdroid.habittracker.core.model.habits.Habit
import com.threemdroid.habittracker.core.model.habits.HabitEntry
import com.threemdroid.habittracker.core.model.habits.HabitSchedule
import com.threemdroid.habittracker.core.model.habits.Reminder

internal fun HabitWithReminders.toModel(): Habit = habit.toModel(
    reminders = reminders.map(ReminderEntity::toModel),
)

internal fun HabitEntity.toModel(
    reminders: List<Reminder>,
): Habit = Habit(
    id = habitId,
    name = name,
    type = habitType,
    targetValue = targetValue,
    defaultIncrement = defaultIncrement,
    unitLabel = unitLabel,
    allowsMultipleUpdatesPerDay = allowsMultipleUpdatesPerDay,
    selectedIconToken = selectedIconToken,
    selectedColorToken = selectedColorToken,
    schedule = HabitSchedule(selectedDays = selectedDays),
    reminders = reminders,
    state = state,
    createdAt = createdAt,
    stoppedAt = stoppedAt,
)

internal fun Habit.toEntity(): HabitEntity = HabitEntity(
    habitId = id,
    name = name,
    habitType = type,
    targetValue = targetValue,
    defaultIncrement = defaultIncrement,
    unitLabel = unitLabel,
    allowsMultipleUpdatesPerDay = allowsMultipleUpdatesPerDay,
    selectedIconToken = selectedIconToken,
    selectedColorToken = selectedColorToken,
    selectedDays = schedule.selectedDays,
    state = state,
    createdAt = createdAt,
    stoppedAt = stoppedAt,
)

internal fun ReminderEntity.toModel(): Reminder = Reminder(
    id = reminderId,
    habitId = habitId,
    time = time,
    schedule = HabitSchedule(selectedDays = selectedDays),
    isEnabled = isEnabled,
)

internal fun Reminder.toEntity(): ReminderEntity = ReminderEntity(
    reminderId = id,
    habitId = habitId,
    time = time,
    selectedDays = schedule.selectedDays,
    isEnabled = isEnabled,
)

internal fun HabitEntryEntity.toModel(): HabitEntry = HabitEntry(
    id = entryId,
    habitId = habitId,
    entryDate = entryDate,
    loggedAt = loggedAt,
    value = value,
)

internal fun HabitEntry.toEntity(): HabitEntryEntity = HabitEntryEntity(
    entryId = id,
    habitId = habitId,
    entryDate = entryDate,
    loggedAt = loggedAt,
    value = value,
)
