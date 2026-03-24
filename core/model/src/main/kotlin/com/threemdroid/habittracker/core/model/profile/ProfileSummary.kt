package com.threemdroid.habittracker.core.model.profile

data class ProfileSummary(
    val totalHabitCount: Int,
    val activeHabitCount: Int,
    val stoppedHabitCount: Int,
    val totalHabitEntryCount: Int,
    val totalMoodEntryCount: Int,
    val enabledReminderCount: Int,
)
