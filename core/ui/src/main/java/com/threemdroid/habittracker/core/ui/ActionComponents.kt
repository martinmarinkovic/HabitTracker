package com.threemdroid.habittracker.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem

@Composable
fun GradientActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val shapes = HabitTrackerDesignSystem.shapes
    val elevations = HabitTrackerDesignSystem.elevations
    val brush = if (enabled) {
        HabitTrackerDesignSystem.gradients.primaryAccent
    } else {
        Brush.linearGradient(listOf(colors.fillStrong, colors.fillStrong))
    }
    val contentColor = if (enabled) colors.textOnAccent else colors.textSecondary
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .clip(shapes.pill)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            ),
        color = Color.Transparent,
        contentColor = contentColor,
        shape = shapes.pill,
        shadowElevation = if (enabled) elevations.raised else elevations.flat,
    ) {
        Row(
            modifier = Modifier
                .background(brush = brush, shape = shapes.pill)
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(horizontal = HabitTrackerDesignSystem.spacing.xl, vertical = HabitTrackerDesignSystem.spacing.md),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingContent != null) {
                leadingContent()
                Spacer(modifier = Modifier.width(HabitTrackerDesignSystem.spacing.sm))
            }

            Text(
                text = text,
                style = typography.bodyStrong,
                color = contentColor,
            )

            if (trailingContent != null) {
                Spacer(modifier = Modifier.width(HabitTrackerDesignSystem.spacing.sm))
                trailingContent()
            }
        }
    }
}

@Composable
fun CircularIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    size: Dp = 44.dp,
    icon: @Composable BoxScope.() -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val elevations = HabitTrackerDesignSystem.elevations
    val containerColor = when {
        !enabled -> colors.fillMuted
        selected -> colors.surfaceTinted
        else -> colors.surfacePrimary
    }
    val borderColor = when {
        selected -> colors.accentPrimary
        else -> colors.strokeSubtle
    }
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .size(size)
            .semantics { role = Role.Button }
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            ),
        color = containerColor,
        contentColor = if (enabled) colors.textPrimary else colors.textTertiary,
        shape = CircleShape,
        shadowElevation = if (selected) elevations.subtle else elevations.flat,
        border = androidx.compose.foundation.BorderStroke(HabitTrackerDesignSystem.spacing.hairline, borderColor),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
            content = icon,
        )
    }
}
