package com.threemdroid.habittracker.core.designsystem

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun DesignSystemPreview() {
    HabitTrackerTheme {
        val colors = HabitTrackerDesignSystem.colors
        val spacing = HabitTrackerDesignSystem.spacing
        val typography = HabitTrackerDesignSystem.typography

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.surfaceCanvas)
                .padding(spacing.screenEdge),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            Text(
                text = "Habit Tracker",
                style = typography.display,
                color = colors.textPrimary,
            )

            HabitTrackerGradientSurface(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Primary gradient surface",
                    style = typography.cardTitle,
                )
            }

            HabitTrackerCard(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                    Text(
                        text = "Raised card",
                        style = typography.cardTitle,
                    )
                    Text(
                        text = "Semantic tokens, spacing, shapes, and typography live in core/designsystem.",
                        style = typography.callout,
                        color = colors.textSecondary,
                    )
                }
            }

            HabitTrackerSurface(
                modifier = Modifier.fillMaxWidth(),
                tone = HabitTrackerSurfaceTone.Secondary,
            ) {
                Text(
                    modifier = Modifier.padding(spacing.lg),
                    text = "Secondary surface",
                    style = typography.bodyStrong,
                )
            }
        }
    }
}
