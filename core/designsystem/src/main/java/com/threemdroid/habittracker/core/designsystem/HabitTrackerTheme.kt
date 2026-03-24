package com.threemdroid.habittracker.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun HabitTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) darkHabitTrackerColors() else lightHabitTrackerColors()
    val spacing = remember { HabitTrackerSpacing() }
    val shapes = remember { HabitTrackerShapes() }
    val elevations = remember { HabitTrackerElevations() }
    val typography = remember { habitTrackerTypography() }
    val gradients = remember(colors) { habitTrackerGradients(colors) }

    CompositionLocalProvider(
        LocalHabitTrackerColors provides colors,
        LocalHabitTrackerSpacing provides spacing,
        LocalHabitTrackerShapes provides shapes,
        LocalHabitTrackerElevations provides elevations,
        LocalHabitTrackerTypography provides typography,
        LocalHabitTrackerGradients provides gradients,
    ) {
        MaterialTheme(
            colorScheme = colors.toMaterialColorScheme(),
            typography = typography.asMaterialTypography(),
            shapes = shapes.asMaterialShapes(),
            content = content,
        )
    }
}

object HabitTrackerDesignSystem {
    val colors: HabitTrackerColors
        @Composable
        @ReadOnlyComposable
        get() = LocalHabitTrackerColors.current

    val spacing: HabitTrackerSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalHabitTrackerSpacing.current

    val shapes: HabitTrackerShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalHabitTrackerShapes.current

    val elevations: HabitTrackerElevations
        @Composable
        @ReadOnlyComposable
        get() = LocalHabitTrackerElevations.current

    val typography: HabitTrackerTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalHabitTrackerTypography.current

    val gradients: HabitTrackerGradients
        @Composable
        @ReadOnlyComposable
        get() = LocalHabitTrackerGradients.current
}
