package com.threemdroid.habittracker.core.designsystem

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class HabitTrackerTypography(
    val display: TextStyle,
    val screenTitle: TextStyle,
    val sectionTitle: TextStyle,
    val cardTitle: TextStyle,
    val body: TextStyle,
    val bodyStrong: TextStyle,
    val callout: TextStyle,
    val label: TextStyle,
    val caption: TextStyle,
)

internal fun habitTrackerTypography(): HabitTrackerTypography {
    val baseFamily = FontFamily.SansSerif

    return HabitTrackerTypography(
        display = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 34.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.5).sp,
        ),
        screenTitle = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.3).sp,
        ),
        sectionTitle = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
        cardTitle = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp,
        ),
        body = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 24.sp,
        ),
        bodyStrong = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp,
        ),
        callout = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 22.sp,
        ),
        label = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        ),
        caption = TextStyle(
            fontFamily = baseFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
        ),
    )
}

internal fun HabitTrackerTypography.asMaterialTypography(): Typography =
    Typography(
        displayLarge = display,
        headlineLarge = screenTitle,
        titleLarge = sectionTitle,
        titleMedium = cardTitle,
        bodyLarge = body,
        bodyMedium = callout,
        labelLarge = bodyStrong,
        labelMedium = label,
        labelSmall = caption,
    )

internal val LocalHabitTrackerTypography = staticCompositionLocalOf { habitTrackerTypography() }
