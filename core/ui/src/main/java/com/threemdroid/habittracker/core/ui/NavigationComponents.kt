package com.threemdroid.habittracker.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem

@Composable
fun FloatingBottomNavigationContainer(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val shapes = HabitTrackerDesignSystem.shapes
    val elevations = HabitTrackerDesignSystem.elevations

    Surface(
        modifier = modifier,
        color = colors.surfaceRaised,
        shape = shapes.pill,
        border = BorderStroke(spacing.hairline, colors.strokeSubtle),
        shadowElevation = elevations.floating,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.sm, vertical = spacing.xs),
            horizontalArrangement = Arrangement.spacedBy(spacing.xs),
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

@Composable
fun FloatingBottomNavigationItem(
    selected: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    badge: (@Composable BoxScope.() -> Unit)? = null,
    icon: @Composable BoxScope.() -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography
    val shapes = HabitTrackerDesignSystem.shapes
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .semantics { role = Role.Tab }
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Tab,
                onClick = onClick,
            ),
        color = if (selected) colors.surfaceTinted else colors.surfaceRaised,
        contentColor = when {
            !enabled -> colors.textTertiary
            selected -> colors.accentPrimary
            else -> colors.textSecondary
        },
        shape = shapes.pill,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = spacing.md, vertical = spacing.sm),
            verticalArrangement = Arrangement.spacedBy(spacing.xxs),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center,
            ) {
                icon()
                if (badge != null) {
                    Box(
                        modifier = Modifier.align(Alignment.TopEnd),
                        content = badge,
                    )
                }
            }
            Text(
                text = label,
                style = typography.caption,
                color = when {
                    !enabled -> colors.textTertiary
                    selected -> colors.accentPrimary
                    else -> colors.textSecondary
                },
            )
        }
    }
}
