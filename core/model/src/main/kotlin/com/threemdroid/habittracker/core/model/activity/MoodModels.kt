package com.threemdroid.habittracker.core.model.activity

import java.time.Instant
import java.time.LocalDate

data class MoodEntry(
    val id: String,
    val entryDate: LocalDate,
    val loggedAt: Instant,
    val moodToken: String,
)
