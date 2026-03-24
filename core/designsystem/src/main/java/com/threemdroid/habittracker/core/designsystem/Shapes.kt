package com.threemdroid.habittracker.core.designsystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class HabitTrackerShapes(
    val extraSmall: RoundedCornerShape = RoundedCornerShape(10.dp),
    val small: RoundedCornerShape = RoundedCornerShape(14.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(18.dp),
    val large: RoundedCornerShape = RoundedCornerShape(24.dp),
    val extraLarge: RoundedCornerShape = RoundedCornerShape(30.dp),
    val pill: RoundedCornerShape = RoundedCornerShape(999.dp),
)

internal fun HabitTrackerShapes.asMaterialShapes(): Shapes =
    Shapes(
        small = small,
        medium = medium,
        large = large,
    )

internal val LocalHabitTrackerShapes = staticCompositionLocalOf { HabitTrackerShapes() }
