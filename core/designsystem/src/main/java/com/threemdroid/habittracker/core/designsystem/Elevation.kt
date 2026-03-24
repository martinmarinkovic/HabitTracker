package com.threemdroid.habittracker.core.designsystem

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class HabitTrackerElevations(
    val flat: Dp = 0.dp,
    val subtle: Dp = 2.dp,
    val raised: Dp = 8.dp,
    val floating: Dp = 14.dp,
)

internal val LocalHabitTrackerElevations = staticCompositionLocalOf { HabitTrackerElevations() }
