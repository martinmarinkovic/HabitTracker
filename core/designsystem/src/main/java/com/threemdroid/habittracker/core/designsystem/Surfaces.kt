package com.threemdroid.habittracker.core.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class HabitTrackerSurfaceTone {
    Canvas,
    Base,
    Secondary,
    Raised,
    Tinted,
    Inverse,
}

@Composable
fun HabitTrackerSurface(
    modifier: Modifier = Modifier,
    tone: HabitTrackerSurfaceTone = HabitTrackerSurfaceTone.Base,
    content: @Composable () -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val elevations = HabitTrackerDesignSystem.elevations
    val shapes = HabitTrackerDesignSystem.shapes

    val containerColor = when (tone) {
        HabitTrackerSurfaceTone.Canvas -> colors.surfaceCanvas
        HabitTrackerSurfaceTone.Base -> colors.surfacePrimary
        HabitTrackerSurfaceTone.Secondary -> colors.surfaceSecondary
        HabitTrackerSurfaceTone.Raised -> colors.surfaceRaised
        HabitTrackerSurfaceTone.Tinted -> colors.surfaceTinted
        HabitTrackerSurfaceTone.Inverse -> colors.surfaceInverse
    }

    val contentColor = when (tone) {
        HabitTrackerSurfaceTone.Inverse -> colors.textOnInverse
        else -> colors.textPrimary
    }

    val shadowElevation = when (tone) {
        HabitTrackerSurfaceTone.Canvas -> elevations.flat
        HabitTrackerSurfaceTone.Base -> elevations.flat
        HabitTrackerSurfaceTone.Secondary -> elevations.flat
        HabitTrackerSurfaceTone.Raised -> elevations.raised
        HabitTrackerSurfaceTone.Tinted -> elevations.subtle
        HabitTrackerSurfaceTone.Inverse -> elevations.floating
    }

    val border = when (tone) {
        HabitTrackerSurfaceTone.Canvas -> null
        HabitTrackerSurfaceTone.Inverse -> null
        else -> BorderStroke(HabitTrackerDesignSystem.spacing.hairline, colors.strokeSubtle)
    }

    Surface(
        modifier = modifier,
        color = containerColor,
        contentColor = contentColor,
        shape = shapes.large,
        border = border,
        shadowElevation = shadowElevation,
    ) {
        content()
    }
}

@Composable
fun HabitTrackerCard(
    modifier: Modifier = Modifier,
    tone: HabitTrackerSurfaceTone = HabitTrackerSurfaceTone.Raised,
    contentPadding: PaddingValues = PaddingValues(HabitTrackerDesignSystem.spacing.lg),
    content: @Composable () -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val elevations = HabitTrackerDesignSystem.elevations
    val shapes = HabitTrackerDesignSystem.shapes

    val containerColor = when (tone) {
        HabitTrackerSurfaceTone.Canvas -> colors.surfaceCanvas
        HabitTrackerSurfaceTone.Base -> colors.surfacePrimary
        HabitTrackerSurfaceTone.Secondary -> colors.surfaceSecondary
        HabitTrackerSurfaceTone.Raised -> colors.surfaceRaised
        HabitTrackerSurfaceTone.Tinted -> colors.surfaceTinted
        HabitTrackerSurfaceTone.Inverse -> colors.surfaceInverse
    }

    val contentColor = when (tone) {
        HabitTrackerSurfaceTone.Inverse -> colors.textOnInverse
        else -> colors.textPrimary
    }

    Card(
        modifier = modifier,
        shape = shapes.large,
        border = BorderStroke(HabitTrackerDesignSystem.spacing.hairline, colors.strokeSubtle),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevations.raised,
        ),
    ) {
        Box(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }
}

@Composable
fun HabitTrackerGradientSurface(
    modifier: Modifier = Modifier,
    brush: Brush = HabitTrackerDesignSystem.gradients.primaryAccent,
    contentColor: Color = HabitTrackerDesignSystem.colors.textOnAccent,
    contentPadding: PaddingValues = PaddingValues(HabitTrackerDesignSystem.spacing.lg),
    content: @Composable BoxScope.() -> Unit,
) {
    val shapes = HabitTrackerDesignSystem.shapes
    val elevations = HabitTrackerDesignSystem.elevations

    Surface(
        modifier = modifier,
        color = Color.Transparent,
        contentColor = contentColor,
        shape = shapes.large,
        shadowElevation = elevations.raised,
    ) {
        Box(
            modifier = Modifier
                .clip(shapes.large)
                .background(brush)
                .padding(contentPadding),
            content = content,
        )
    }
}
