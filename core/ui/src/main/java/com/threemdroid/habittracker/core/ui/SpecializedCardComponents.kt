package com.threemdroid.habittracker.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem
import com.threemdroid.habittracker.core.designsystem.HabitTrackerGradientSurface
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone

@Composable
fun LearnContentCard(
    title: String,
    modifier: Modifier = Modifier,
    eyebrow: String? = null,
    description: String? = null,
    metadata: String? = null,
    onClick: (() -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    media: (@Composable BoxScope.() -> Unit)? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier,
        tone = HabitTrackerSurfaceTone.Raised,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            HabitTrackerGradientSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(156.dp),
                brush = HabitTrackerDesignSystem.gradients.raisedSurface,
                contentPadding = androidx.compose.foundation.layout.PaddingValues(spacing.lg),
            ) {
                if (badge != null) {
                    Box(modifier = Modifier.align(Alignment.TopStart)) {
                        badge()
                    }
                }
                if (media != null) {
                    Box(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        content = media,
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.xs),
            ) {
                if (eyebrow != null) {
                    Text(
                        text = eyebrow,
                        style = typography.caption,
                        color = colors.textSecondary,
                    )
                }

                Text(
                    text = title,
                    style = typography.sectionTitle,
                    color = colors.textPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                if (description != null) {
                    Text(
                        text = description,
                        style = typography.callout,
                        color = colors.textSecondary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                if (metadata != null) {
                    Text(
                        text = metadata,
                        style = typography.label,
                        color = colors.accentPrimary,
                    )
                }
            }
        }
    }
}

@Composable
fun ChartCardShell(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    footerText: String? = null,
    headerContent: (@Composable RowScope.() -> Unit)? = null,
    footerContent: (@Composable () -> Unit)? = null,
    chartContent: @Composable BoxScope.() -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier,
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.Top,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(spacing.xxs),
                ) {
                    Text(
                        text = title,
                        style = typography.cardTitle,
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

                if (headerContent != null) {
                    Row(content = headerContent)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(148.dp),
                content = chartContent,
            )

            if (footerText != null || footerContent != null) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                    if (footerText != null) {
                        Text(
                            text = footerText,
                            style = typography.label,
                            color = colors.textSecondary,
                        )
                    }
                    footerContent?.invoke()
                }
            }
        }
    }
}
