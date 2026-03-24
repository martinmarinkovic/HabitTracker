package com.threemdroid.habittracker.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem

data class SegmentedControlOption<T>(
    val value: T,
    val label: String,
)

@Composable
fun <T> SegmentedControl(
    options: List<SegmentedControlOption<T>>,
    selectedValue: T,
    onValueSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val shapes = HabitTrackerDesignSystem.shapes
    val typography = HabitTrackerDesignSystem.typography

    Surface(
        modifier = modifier,
        color = colors.fillMuted,
        shape = shapes.pill,
        border = BorderStroke(spacing.hairline, colors.strokeSubtle),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.xxs)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(spacing.xxs),
        ) {
            options.forEach { option ->
                val selected = option.value == selectedValue
                val interactionSource = remember(option.value) { MutableInteractionSource() }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(shapes.pill)
                        .clickable(
                            enabled = enabled,
                            interactionSource = interactionSource,
                            indication = null,
                            role = Role.Tab,
                            onClick = { onValueSelected(option.value) },
                        ),
                    color = if (selected) colors.surfacePrimary else Color.Transparent,
                    shape = shapes.pill,
                    shadowElevation = if (selected) HabitTrackerDesignSystem.elevations.subtle else HabitTrackerDesignSystem.elevations.flat,
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = spacing.md, vertical = spacing.sm),
                        text = option.label,
                        textAlign = TextAlign.Center,
                        style = if (selected) typography.bodyStrong else typography.callout,
                        color = if (selected) colors.textPrimary else colors.textSecondary,
                    )
                }
            }
        }
    }
}

@Composable
fun DateCapsuleDaySelector(
    primaryLabel: String,
    secondaryLabel: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val spacing = HabitTrackerDesignSystem.spacing
    val shapes = HabitTrackerDesignSystem.shapes
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .width(60.dp)
            .heightIn(min = 82.dp)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            ),
        color = if (selected) colors.surfaceInverse else colors.surfacePrimary,
        contentColor = if (selected) colors.textOnInverse else colors.textPrimary,
        shape = shapes.large,
        border = BorderStroke(spacing.hairline, if (selected) Color.Transparent else colors.strokeSubtle),
        shadowElevation = if (selected) HabitTrackerDesignSystem.elevations.raised else HabitTrackerDesignSystem.elevations.flat,
    ) {
        Column(
            modifier = Modifier.padding(vertical = spacing.md, horizontal = spacing.sm),
            verticalArrangement = Arrangement.spacedBy(spacing.xxs),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = primaryLabel,
                style = typography.caption,
                color = if (selected) colors.textOnInverse else colors.textSecondary,
            )
            Text(
                text = secondaryLabel,
                style = typography.cardTitle,
                color = if (selected) colors.textOnInverse else colors.textPrimary,
            )
        }
    }
}

@Composable
fun SelectableIconTile(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    enabled: Boolean = true,
    supportingText: String? = null,
    icon: @Composable BoxScope.() -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography
    val shapes = HabitTrackerDesignSystem.shapes
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = modifier
            .clip(shapes.large)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            ),
        color = if (selected) colors.surfaceTinted else colors.surfacePrimary,
        shape = shapes.large,
        border = BorderStroke(
            spacing.hairline,
            if (selected) colors.accentPrimary else colors.strokeSubtle,
        ),
        shadowElevation = if (selected) HabitTrackerDesignSystem.elevations.subtle else HabitTrackerDesignSystem.elevations.flat,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.sm),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                color = if (selected) colors.accentPrimary else colors.fillMuted,
                contentColor = if (selected) colors.textOnAccent else colors.textSecondary,
                shape = CircleShape,
            ) {
                Box(contentAlignment = Alignment.Center, content = icon)
            }
            Text(
                text = title,
                style = typography.label,
                textAlign = TextAlign.Center,
                color = colors.textPrimary,
            )
            if (supportingText != null) {
                Text(
                    text = supportingText,
                    style = typography.caption,
                    textAlign = TextAlign.Center,
                    color = colors.textSecondary,
                )
            }
        }
    }
}

@Composable
fun SelectableColorTile(
    color: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
) {
    val tokens = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            modifier = Modifier
                .size(44.dp)
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null,
                    role = Role.RadioButton,
                    onClick = onClick,
                ),
            color = color,
            shape = CircleShape,
            border = BorderStroke(
                if (selected) 2.dp else spacing.hairline,
                if (selected) tokens.accentPrimary else tokens.strokeSubtle,
            ),
            shadowElevation = if (selected) HabitTrackerDesignSystem.elevations.subtle else HabitTrackerDesignSystem.elevations.flat,
        ) {}

        if (label != null) {
            Text(
                text = label,
                style = typography.caption,
                color = tokens.textSecondary,
                textAlign = TextAlign.Center,
            )
        }
    }
}
