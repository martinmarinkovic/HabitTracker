package com.threemdroid.habittracker.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerCard
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem

@Composable
fun PlaceholderScreen(
    title: String,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceCanvas),
        contentAlignment = Alignment.Center,
    ) {
        HabitTrackerCard(
            modifier = Modifier.widthIn(max = 280.dp),
        ) {
            Text(
                text = title,
                style = typography.screenTitle,
                color = colors.textPrimary,
            )
        }
    }
}
