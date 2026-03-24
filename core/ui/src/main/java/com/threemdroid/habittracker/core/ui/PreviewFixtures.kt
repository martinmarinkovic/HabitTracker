package com.threemdroid.habittracker.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme

@Composable
private fun PreviewGlyph(
    modifier: Modifier = Modifier,
    color: Color = HabitTrackerDesignSystem.colors.accentPrimary,
) {
    Box(
        modifier = modifier
            .size(18.dp)
            .clip(CircleShape)
            .background(color),
    )
}

@Composable
private fun PreviewMiniChart() {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            verticalAlignment = Alignment.Bottom,
        ) {
        listOf(40.dp, 68.dp, 92.dp, 56.dp, 112.dp, 84.dp, 72.dp).forEachIndexed { index, height ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(height)
                    .clip(HabitTrackerDesignSystem.shapes.medium)
                    .background(
                        if (index == 4) colors.accentPrimary else colors.fillStrong,
                    ),
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SharedComponentsPreview() {
    HabitTrackerTheme {
        val colors = HabitTrackerDesignSystem.colors
        val spacing = HabitTrackerDesignSystem.spacing
        val typography = HabitTrackerDesignSystem.typography
        var query by remember { mutableStateOf("") }
        var segment by remember { mutableStateOf("Week") }
        var toggleEnabled by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.surfaceCanvas)
                .verticalScroll(rememberScrollState())
                .padding(spacing.screenEdge),
            verticalArrangement = Arrangement.spacedBy(spacing.xl),
        ) {
            SectionHeader(
                title = "Shared Components",
                supportingText = "Core module preview only",
                actionLabel = "Manage",
                onActionClick = {},
            )

            FloatingBottomNavigationContainer {
                FloatingBottomNavigationItem(
                    modifier = Modifier.weight(1f),
                    selected = true,
                    label = "Home",
                    onClick = {},
                    icon = { PreviewGlyph() },
                )
                FloatingBottomNavigationItem(
                    modifier = Modifier.weight(1f),
                    selected = false,
                    label = "Learn",
                    onClick = {},
                    icon = { PreviewGlyph(color = colors.textSecondary) },
                )
                FloatingBottomNavigationItem(
                    modifier = Modifier.weight(1f),
                    selected = false,
                    label = "Settings",
                    onClick = {},
                    icon = { PreviewGlyph(color = colors.textSecondary) },
                )
            }

            GradientActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Create habit",
                onClick = {},
                leadingContent = { PreviewGlyph(color = colors.textOnAccent) },
            )

            Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                CircularIconButton(
                    onClick = {},
                    icon = { PreviewGlyph() },
                )
                CircularIconButton(
                    onClick = {},
                    selected = true,
                    icon = { PreviewGlyph(color = colors.textOnAccent) },
                )
            }

            HabitTrackerSearchBar(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = { query = it },
                leadingContent = { PreviewGlyph(color = colors.textTertiary) },
                trailingContent = { Text("Go", style = typography.label, color = colors.accentPrimary) },
            )

            SegmentedControl(
                modifier = Modifier.fillMaxWidth(),
                options = listOf("Week", "Month", "Year").map { SegmentedControlOption(it, it) },
                selectedValue = segment,
                onValueSelected = { segment = it },
            )

            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                DateCapsuleDaySelector("Mon", "12", selected = true, onClick = {})
                DateCapsuleDaySelector("Tue", "13", selected = false, onClick = {})
                DateCapsuleDaySelector("Wed", "14", selected = false, onClick = {})
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HabitTrackerProgressRing(
                    progress = 0.72f,
                    centerContent = {
                        Text(
                            text = "72%",
                            style = typography.label,
                            color = colors.textPrimary,
                        )
                    },
                )

                SelectableIconTile(
                    modifier = Modifier.weight(1f),
                    title = "Reminder",
                    supportingText = "Enabled",
                    selected = true,
                    onClick = {},
                    icon = { PreviewGlyph(color = colors.textOnAccent) },
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                SelectableColorTile(color = Color(0xFF3D63FF), selected = true, onClick = {}, label = "Blue")
                SelectableColorTile(color = Color(0xFF1FA971), selected = false, onClick = {}, label = "Green")
                SelectableColorTile(color = Color(0xFFE85C4A), selected = false, onClick = {}, label = "Red")
            }

            ModalSheetHeader(
                title = "Edit habit",
                subtitle = "Shared sheet chrome",
                leadingContent = { PreviewGlyph() },
                trailingContent = { CircularIconButton(onClick = {}, icon = { PreviewGlyph() }) },
            )

            RoundedCardSurface(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    Text("Rounded card surface", style = typography.cardTitle, color = colors.textPrimary)
                    Text(
                        "Generic shared shell for cards and grouped content.",
                        style = typography.callout,
                        color = colors.textSecondary,
                    )
                }
            }

            LearnContentCard(
                modifier = Modifier.fillMaxWidth(),
                eyebrow = "5 MIN",
                title = "Build consistent habits with daily prompts",
                description = "Structural preview only. Final artwork and copy must come from sourced references.",
                metadata = "Watch lesson",
                badge = {
                    Box(
                        modifier = Modifier
                            .clip(HabitTrackerDesignSystem.shapes.pill)
                            .background(colors.surfacePrimary.copy(alpha = 0.88f))
                            .padding(horizontal = spacing.sm, vertical = spacing.xs),
                    ) {
                        Text("New", style = typography.caption, color = colors.textPrimary)
                    }
                },
                media = {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(HabitTrackerDesignSystem.shapes.large)
                            .background(colors.surfacePrimary.copy(alpha = 0.4f)),
                        contentAlignment = Alignment.Center,
                    ) {
                        PreviewGlyph(color = colors.textOnAccent)
                    }
                },
            )

            ChartCardShell(
                modifier = Modifier.fillMaxWidth(),
                title = "Completion",
                subtitle = "This week",
                footerText = "Shared shell only",
                headerContent = {
                    Text("72%", style = typography.label, color = colors.accentPrimary)
                },
                chartContent = {
                    PreviewMiniChart()
                },
            )

            Column {
                SettingsRow(
                    title = "Notifications",
                    supportingText = "Daily reminders at 8:00",
                    onClick = {},
                    leadingContent = { PreviewGlyph() },
                    trailingContent = {
                        Text("On", style = typography.label, color = colors.textSecondary)
                    },
                )
                InsetSeparator()
                ToggleRow(
                    title = "Weekly summary",
                    supportingText = "Send on Sunday evening",
                    checked = toggleEnabled,
                    onCheckedChange = { toggleEnabled = it },
                    leadingContent = { PreviewGlyph() },
                )
            }

            HabitTrackerSeparator()

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Preview only",
                textAlign = TextAlign.Center,
                style = typography.caption,
                color = colors.textTertiary,
            )
        }
    }
}
