package com.threemdroid.habittracker.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
    trailingContent: (@Composable RowScope.() -> Unit)? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val spacing = HabitTrackerDesignSystem.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.xxs),
        ) {
            Text(
                text = title,
                style = typography.sectionTitle,
                color = colors.textPrimary,
            )
            if (supportingText != null) {
                Text(
                    text = supportingText,
                    style = typography.callout,
                    color = colors.textSecondary,
                )
            }
        }

        if (actionLabel != null && onActionClick != null) {
            val interactionSource = remember { MutableInteractionSource() }
            Text(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        role = Role.Button,
                        onClick = onActionClick,
                    )
                    .padding(vertical = spacing.xs),
                text = actionLabel,
                style = typography.label,
                color = colors.accentPrimary,
            )
        }

        if (trailingContent != null) {
            Row(content = trailingContent)
        }
    }
}

@Composable
fun ModalSheetHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val spacing = HabitTrackerDesignSystem.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.invoke()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.xxs),
        ) {
            Text(
                text = title,
                style = typography.sectionTitle,
                color = colors.textPrimary,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = typography.callout,
                    color = colors.textSecondary,
                )
            }
        }

        trailingContent?.invoke()
    }
}

@Composable
fun HabitTrackerProgressRing(
    progress: Float,
    modifier: Modifier = Modifier,
    ringSize: Dp = 72.dp,
    strokeWidth: Dp = 8.dp,
    progressBrush: Brush = HabitTrackerDesignSystem.gradients.primaryAccent,
    centerContent: (@Composable BoxScope.() -> Unit)? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val clampedProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier.size(ringSize),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokePx = strokeWidth.toPx()
            drawArc(
                color = colors.fillStrong,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokePx, cap = StrokeCap.Round),
                size = Size(size.width, size.height),
            )
            drawArc(
                brush = progressBrush,
                startAngle = -90f,
                sweepAngle = 360f * clampedProgress,
                useCenter = false,
                style = Stroke(width = strokePx, cap = StrokeCap.Round),
                size = Size(size.width, size.height),
            )
        }

        if (centerContent != null) {
            Box(content = centerContent)
        }
    }
}

@Composable
fun SettingsRow(
    title: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    enabled: Boolean = true,
    destructive: Boolean = false,
    onClick: (() -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable RowScope.() -> Unit)? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        enabled = enabled,
                        interactionSource = interactionSource,
                        indication = null,
                        role = Role.Button,
                        onClick = onClick,
                    )
                } else {
                    Modifier
                },
            )
            .padding(vertical = spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
        leadingContent?.invoke()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.xxs),
        ) {
            Text(
                text = title,
                style = typography.body,
                color = when {
                    !enabled -> colors.textTertiary
                    destructive -> colors.accentDanger
                    else -> colors.textPrimary
                },
            )
            if (supportingText != null) {
                Text(
                    text = supportingText,
                    style = typography.callout,
                    color = if (enabled) colors.textSecondary else colors.textTertiary,
                )
            }
        }

        if (trailingContent != null) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = trailingContent,
            )
        }
    }
}

@Composable
fun ToggleRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    enabled: Boolean = true,
    leadingContent: (@Composable () -> Unit)? = null,
) {
    SettingsRow(
        title = title,
        modifier = modifier,
        supportingText = supportingText,
        enabled = enabled,
        onClick = { onCheckedChange(!checked) },
        leadingContent = leadingContent,
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        },
    )
}

@Composable
fun HabitTrackerSeparator(
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color = HabitTrackerDesignSystem.colors.strokeSubtle,
    thickness: Dp = HabitTrackerDesignSystem.spacing.hairline,
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color,
    )
}

@Composable
fun InsetSeparator(
    modifier: Modifier = Modifier,
    insetStart: Dp = HabitTrackerDesignSystem.spacing.xl,
    insetEnd: Dp = HabitTrackerDesignSystem.spacing.xl,
) {
    HabitTrackerSeparator(
        modifier = modifier.padding(start = insetStart, end = insetEnd),
    )
}

@Composable
fun VerticalSeparator(
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color = HabitTrackerDesignSystem.colors.strokeSubtle,
    thickness: Dp = HabitTrackerDesignSystem.spacing.hairline,
    height: Dp = 24.dp,
) {
    Spacer(
        modifier = modifier
            .height(height)
            .background(color = color)
            .width(thickness),
    )
}
