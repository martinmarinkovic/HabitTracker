package com.threemdroid.habittracker.core.designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class HabitTrackerColors(
    val isLight: Boolean,
    val accentPrimary: Color,
    val accentSecondary: Color,
    val accentPositive: Color,
    val accentWarning: Color,
    val accentDanger: Color,
    val surfaceCanvas: Color,
    val surfacePrimary: Color,
    val surfaceSecondary: Color,
    val surfaceRaised: Color,
    val surfaceTinted: Color,
    val surfaceInverse: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textOnAccent: Color,
    val textOnInverse: Color,
    val strokeSubtle: Color,
    val strokeStrong: Color,
    val fillMuted: Color,
    val fillStrong: Color,
    val shadowAmbient: Color,
    val overlayScrim: Color,
)

internal fun lightHabitTrackerColors(): HabitTrackerColors =
    HabitTrackerColors(
        isLight = true,
        accentPrimary = Color(0xFF3D63FF),
        accentSecondary = Color(0xFF7F8FFF),
        accentPositive = Color(0xFF1FA971),
        accentWarning = Color(0xFFD68A00),
        accentDanger = Color(0xFFE85C4A),
        surfaceCanvas = Color(0xFFF4F5F7),
        surfacePrimary = Color(0xFFFFFFFF),
        surfaceSecondary = Color(0xFFF1F4F8),
        surfaceRaised = Color(0xFFFFFFFF),
        surfaceTinted = Color(0xFFEEF3FF),
        surfaceInverse = Color(0xFF111827),
        textPrimary = Color(0xFF111827),
        textSecondary = Color(0xFF5B6472),
        textTertiary = Color(0xFF8A93A3),
        textOnAccent = Color(0xFFFFFFFF),
        textOnInverse = Color(0xFFFFFFFF),
        strokeSubtle = Color(0xFFE5EAF1),
        strokeStrong = Color(0xFFC9D2DE),
        fillMuted = Color(0xFFEFF3F8),
        fillStrong = Color(0xFFDCE4F2),
        shadowAmbient = Color(0x1F0F172A),
        overlayScrim = Color(0x66101828),
    )

internal fun darkHabitTrackerColors(): HabitTrackerColors =
    HabitTrackerColors(
        isLight = false,
        accentPrimary = Color(0xFF88A1FF),
        accentSecondary = Color(0xFFB2BEFF),
        accentPositive = Color(0xFF57D6A3),
        accentWarning = Color(0xFFF0B44D),
        accentDanger = Color(0xFFFF8C7A),
        surfaceCanvas = Color(0xFF0E1116),
        surfacePrimary = Color(0xFF151A22),
        surfaceSecondary = Color(0xFF1A2130),
        surfaceRaised = Color(0xFF1E2635),
        surfaceTinted = Color(0xFF1B2333),
        surfaceInverse = Color(0xFFF4F5F7),
        textPrimary = Color(0xFFF5F7FB),
        textSecondary = Color(0xFFC3CAD6),
        textTertiary = Color(0xFF8D96A8),
        textOnAccent = Color(0xFF0E1116),
        textOnInverse = Color(0xFF111827),
        strokeSubtle = Color(0xFF253041),
        strokeStrong = Color(0xFF3B485C),
        fillMuted = Color(0xFF171D28),
        fillStrong = Color(0xFF232C3D),
        shadowAmbient = Color(0x66000000),
        overlayScrim = Color(0xA6000000),
    )

internal fun HabitTrackerColors.toMaterialColorScheme(): ColorScheme =
    if (isLight) {
        lightColorScheme(
            primary = accentPrimary,
            onPrimary = textOnAccent,
            primaryContainer = surfaceTinted,
            onPrimaryContainer = textPrimary,
            secondary = accentSecondary,
            onSecondary = textOnAccent,
            tertiary = accentPositive,
            onTertiary = textOnAccent,
            background = surfaceCanvas,
            onBackground = textPrimary,
            surface = surfacePrimary,
            onSurface = textPrimary,
            surfaceVariant = surfaceSecondary,
            onSurfaceVariant = textSecondary,
            outline = strokeSubtle,
            outlineVariant = strokeStrong,
            inverseSurface = surfaceInverse,
            inverseOnSurface = textOnInverse,
            inversePrimary = accentSecondary,
            error = accentDanger,
            onError = textOnAccent,
            scrim = overlayScrim,
        )
    } else {
        darkColorScheme(
            primary = accentPrimary,
            onPrimary = textOnAccent,
            primaryContainer = surfaceTinted,
            onPrimaryContainer = textPrimary,
            secondary = accentSecondary,
            onSecondary = textOnAccent,
            tertiary = accentPositive,
            onTertiary = textOnAccent,
            background = surfaceCanvas,
            onBackground = textPrimary,
            surface = surfacePrimary,
            onSurface = textPrimary,
            surfaceVariant = surfaceSecondary,
            onSurfaceVariant = textSecondary,
            outline = strokeSubtle,
            outlineVariant = strokeStrong,
            inverseSurface = surfaceInverse,
            inverseOnSurface = textOnInverse,
            inversePrimary = accentSecondary,
            error = accentDanger,
            onError = textOnAccent,
            scrim = overlayScrim,
        )
    }

internal val LocalHabitTrackerColors = staticCompositionLocalOf { lightHabitTrackerColors() }
