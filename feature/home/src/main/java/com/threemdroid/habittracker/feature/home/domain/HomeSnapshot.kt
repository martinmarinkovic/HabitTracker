package com.threemdroid.habittracker.feature.home.domain

import com.threemdroid.habittracker.feature.home.contract.HomeHabitItem

internal data class HomeSnapshot(
    val habitsToday: List<HomeHabitItem>,
    val selectedMoodToken: String?,
)
