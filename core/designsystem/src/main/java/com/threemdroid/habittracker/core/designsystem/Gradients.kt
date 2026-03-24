package com.threemdroid.habittracker.core.designsystem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class HabitTrackerGradients(
    val primaryAccent: Brush,
    val positiveAccent: Brush,
    val raisedSurface: Brush,
    val topScrim: Brush,
)

internal fun habitTrackerGradients(colors: HabitTrackerColors): HabitTrackerGradients =
    HabitTrackerGradients(
        primaryAccent = Brush.linearGradient(
            colors = listOf(colors.accentPrimary, colors.accentSecondary),
        ),
        positiveAccent = Brush.linearGradient(
            colors = listOf(colors.accentPositive, colors.accentPrimary),
        ),
        raisedSurface = Brush.linearGradient(
            colors = listOf(colors.surfacePrimary, colors.surfaceTinted),
        ),
        topScrim = Brush.verticalGradient(
            colors = listOf(colors.overlayScrim, Color.Transparent),
        ),
    )

internal val LocalHabitTrackerGradients = staticCompositionLocalOf {
    habitTrackerGradients(lightHabitTrackerColors())
}
