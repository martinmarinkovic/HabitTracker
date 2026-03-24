package com.threemdroid.habittracker.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.draw.clip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone

@Composable
fun HabitTrackerSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val shapes = HabitTrackerDesignSystem.shapes
    val spacing = HabitTrackerDesignSystem.spacing

    Surface(
        modifier = modifier,
        color = colors.surfacePrimary,
        contentColor = colors.textPrimary,
        shape = shapes.pill,
        border = BorderStroke(spacing.hairline, colors.strokeSubtle),
        shadowElevation = HabitTrackerDesignSystem.elevations.subtle,
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 52.dp)
                .padding(horizontal = spacing.lg, vertical = spacing.sm),
            enabled = enabled,
            readOnly = readOnly,
            singleLine = true,
            textStyle = typography.body.copy(color = if (enabled) colors.textPrimary else colors.textTertiary),
            cursorBrush = SolidColor(colors.accentPrimary),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    leadingContent?.invoke()
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 1.dp),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = typography.body,
                                color = colors.textTertiary,
                            )
                        }
                        innerTextField()
                    }
                    trailingContent?.invoke()
                }
            },
        )
    }
}

@Composable
fun RoundedCardSurface(
    modifier: Modifier = Modifier,
    tone: HabitTrackerSurfaceTone = HabitTrackerSurfaceTone.Raised,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val shapes = HabitTrackerDesignSystem.shapes
    val interactionSource = remember { MutableInteractionSource() }
    val clickableModifier = if (onClick != null) {
        Modifier
            .clip(shapes.large)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            )
    } else {
        Modifier
    }

    com.threemdroid.habittracker.core.designsystem.HabitTrackerCard(
        modifier = modifier.then(clickableModifier),
        tone = tone,
        content = content,
    )
}
