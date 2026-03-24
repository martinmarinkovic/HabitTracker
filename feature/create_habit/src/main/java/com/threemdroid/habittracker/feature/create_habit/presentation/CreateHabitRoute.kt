@file:OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)

package com.threemdroid.habittracker.feature.create_habit.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.core.ui.CircularIconButton
import com.threemdroid.habittracker.core.ui.GradientActionButton
import com.threemdroid.habittracker.core.ui.HabitTrackerSearchBar
import com.threemdroid.habittracker.core.ui.HabitTrackerSeparator
import com.threemdroid.habittracker.core.ui.ModalSheetHeader
import com.threemdroid.habittracker.core.ui.RoundedCardSurface
import com.threemdroid.habittracker.core.ui.SectionHeader
import com.threemdroid.habittracker.core.ui.SegmentedControl
import com.threemdroid.habittracker.core.ui.SegmentedControlOption
import com.threemdroid.habittracker.core.ui.SelectableColorTile
import com.threemdroid.habittracker.core.ui.SelectableIconTile
import com.threemdroid.habittracker.core.ui.ToggleRow
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitEffect
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitIntent
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitSaveError
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitUiState
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitNameValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitNumericValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitScheduleValidationError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSelectionValidationError
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
internal fun CreateHabitRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onBackRequested: () -> Unit,
    onHabitCreated: (String) -> Unit,
) {
    val viewModel: CreateHabitViewModel = viewModel(factory = viewModelFactory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    is CreateHabitEffect.HabitCreated -> onHabitCreated(effect.habitId)
                    is CreateHabitEffect.ShowSaveError -> snackbarHostState.showSnackbar(effect.error.message())
                }
            }
        }
    }

    CreateHabitScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onIntent = viewModel::handleIntent,
        onBackRequested = onBackRequested,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateHabitScreen(
    uiState: CreateHabitUiState,
    snackbarHostState: SnackbarHostState,
    onIntent: (CreateHabitIntent) -> Unit,
    onBackRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    var activePanel by remember { mutableStateOf(CreateHabitPanel.FORM) }
    var isGoalSheetVisible by remember { mutableStateOf(false) }
    var iconSearchQuery by remember { mutableStateOf("") }
    var colorSearchQuery by remember { mutableStateOf("") }

    fun dismissTransientChrome() {
        when {
            isGoalSheetVisible -> isGoalSheetVisible = false
            activePanel == CreateHabitPanel.ICON_PICKER -> {
                iconSearchQuery = ""
                activePanel = CreateHabitPanel.FORM
            }

            activePanel == CreateHabitPanel.COLOR_PICKER -> {
                colorSearchQuery = ""
                activePanel = CreateHabitPanel.FORM
            }
        }
    }

    val canHandleInternalBack = isGoalSheetVisible || activePanel != CreateHabitPanel.FORM

    BackHandler(enabled = canHandleInternalBack) {
        dismissTransientChrome()
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceCanvas)
            .testTag(CreateHabitUiTestTags.ROOT),
        containerColor = colors.surfaceCanvas,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            CreateHabitTopBar(
                title = when (activePanel) {
                    CreateHabitPanel.FORM -> "Create Habit"
                    CreateHabitPanel.ICON_PICKER -> "Select Icon"
                    CreateHabitPanel.COLOR_PICKER -> "Select Color"
                },
                onBackRequested = {
                    if (canHandleInternalBack) {
                        dismissTransientChrome()
                    } else {
                        onBackRequested()
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.navigationBarsPadding(),
            )
        },
        bottomBar = {
            if (activePanel == CreateHabitPanel.FORM) {
                GradientActionButton(
                    text = if (uiState.isSaving) "Saving..." else "Add Habit",
                    onClick = { onIntent(CreateHabitIntent.SaveRequested) },
                    enabled = !uiState.isSaving,
                    modifier = Modifier
                        .safeDrawingPadding()
                        .padding(horizontal = spacing.screenEdge, vertical = spacing.md)
                        .testTag(CreateHabitUiTestTags.ADD_HABIT_BUTTON),
                    trailingContent = if (uiState.isSaving) {
                        {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                color = colors.textOnAccent,
                                strokeWidth = 2.dp,
                            )
                        }
                    } else {
                        null
                    },
                )
            }
        },
    ) { paddingValues ->
        when (activePanel) {
            CreateHabitPanel.FORM -> CreateHabitFormScreen(
                uiState = uiState,
                onIntent = onIntent,
                onOpenGoalSheet = { isGoalSheetVisible = true },
                onOpenIconPicker = {
                    isGoalSheetVisible = false
                    activePanel = CreateHabitPanel.ICON_PICKER
                },
                onOpenColorPicker = {
                    isGoalSheetVisible = false
                    activePanel = CreateHabitPanel.COLOR_PICKER
                },
                modifier = Modifier.padding(paddingValues),
            )

            CreateHabitPanel.ICON_PICKER -> CreateHabitIconPickerScreen(
                selectedIconToken = uiState.selectedIconToken,
                searchQuery = iconSearchQuery,
                onSearchQueryChanged = { iconSearchQuery = it },
                onIconSelected = { token -> onIntent(CreateHabitIntent.IconSelected(token)) },
                modifier = Modifier.padding(paddingValues),
            )

            CreateHabitPanel.COLOR_PICKER -> CreateHabitColorPickerScreen(
                selectedColorToken = uiState.selectedColorToken,
                searchQuery = colorSearchQuery,
                onSearchQueryChanged = { colorSearchQuery = it },
                onColorSelected = { token -> onIntent(CreateHabitIntent.ColorSelected(token)) },
                modifier = Modifier.padding(paddingValues),
            )
        }
    }

    if (isGoalSheetVisible) {
        CreateHabitGoalSheet(
            uiState = uiState,
            onDismissRequest = { isGoalSheetVisible = false },
            onIntent = onIntent,
        )
    }
}

@Composable
private fun CreateHabitTopBar(
    title: String,
    onBackRequested: () -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = spacing.screenEdge, vertical = spacing.md),
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularIconButton(
            onClick = onBackRequested,
        ) {
            Text(
                text = "<",
                style = typography.bodyStrong,
                color = colors.textPrimary,
            )
        }
        Text(
            text = title,
            style = typography.screenTitle,
            color = colors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun CreateHabitFormScreen(
    uiState: CreateHabitUiState,
    onIntent: (CreateHabitIntent) -> Unit,
    onOpenGoalSheet: () -> Unit,
    onOpenIconPicker: () -> Unit,
    onOpenColorPicker: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = spacing.screenEdge,
            top = spacing.sm,
            end = spacing.screenEdge,
            bottom = 148.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
    ) {
        item {
            CreateHabitInputField(
                label = "Name",
                value = uiState.nameInput,
                onValueChange = { onIntent(CreateHabitIntent.NameChanged(it)) },
                placeholder = "Habit name",
                errorText = uiState.validation.nameError?.message(),
                fieldTestTag = CreateHabitUiTestTags.NAME_FIELD,
            )
        }
        item {
            SectionHeader(title = "Style")
        }
        item {
            CreateHabitStyleSection(
                uiState = uiState,
                onOpenIconPicker = onOpenIconPicker,
                onOpenColorPicker = onOpenColorPicker,
            )
        }
        item {
            SectionHeader(title = "Goal")
        }
        item {
            CreateHabitGoalSummaryCard(
                uiState = uiState,
                onClick = onOpenGoalSheet,
            )
        }
        item {
            SectionHeader(title = "Schedule")
        }
        item {
            CreateHabitScheduleCard(
                uiState = uiState,
                onIntent = onIntent,
            )
        }
        item {
            SectionHeader(title = "Reminders")
        }
        item {
            CreateHabitAddReminderCard(
                onClick = { onIntent(CreateHabitIntent.ReminderAdded) },
            )
        }
        items(
            items = uiState.reminders,
            key = CreateHabitReminderItem::reminderId,
        ) { reminder ->
            CreateHabitReminderCard(
                reminder = reminder,
                selectedDays = uiState.selectedDays,
                onEnabledChanged = { enabled ->
                    onIntent(
                        CreateHabitIntent.ReminderEnabledChanged(
                            reminderId = reminder.reminderId,
                            isEnabled = enabled,
                        ),
                    )
                },
                onRemove = {
                    onIntent(CreateHabitIntent.ReminderRemoved(reminder.reminderId))
                },
            )
        }
    }
}

@Composable
private fun CreateHabitStyleSection(
    uiState: CreateHabitUiState,
    onOpenIconPicker: () -> Unit,
    onOpenColorPicker: () -> Unit,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        CreateHabitSelectionCard(
            title = "Icon",
            value = uiState.selectedIconToken.iconLabel(),
            placeholder = "Select",
            onClick = onOpenIconPicker,
            errorText = uiState.validation.iconError?.message(),
            modifier = Modifier
                .weight(1f)
                .testTag(CreateHabitUiTestTags.ICON_CARD),
        ) {
            CreateHabitTokenCircle(
                token = uiState.selectedIconToken,
                content = uiState.selectedIconToken.iconMonogram(),
            )
        }
        CreateHabitSelectionCard(
            title = "Color",
            value = uiState.selectedColorToken.colorLabel(),
            placeholder = "Select",
            onClick = onOpenColorPicker,
            errorText = uiState.validation.colorError?.message(),
            modifier = Modifier
                .weight(1f)
                .testTag(CreateHabitUiTestTags.COLOR_CARD),
        ) {
            CreateHabitColorCircle(
                token = uiState.selectedColorToken,
            )
        }
    }
}

@Composable
private fun CreateHabitSelectionCard(
    title: String,
    value: String?,
    placeholder: String,
    onClick: () -> Unit,
    errorText: String?,
    modifier: Modifier = Modifier,
    previewContent: @Composable () -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        RoundedCardSurface(
            onClick = onClick,
            modifier = modifier,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.md),
            ) {
                previewContent()
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.xxs),
                ) {
                    Text(
                        text = title,
                        style = typography.label,
                        color = colors.textSecondary,
                    )
                    Text(
                        text = value ?: placeholder,
                        style = typography.bodyStrong,
                        color = if (value == null) colors.textTertiary else colors.textPrimary,
                    )
                }
            }
        }
        CreateHabitInlineError(errorText = errorText)
    }
}

@Composable
private fun CreateHabitGoalSummaryCard(
    uiState: CreateHabitUiState,
    onClick: () -> Unit,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        RoundedCardSurface(
            onClick = onClick,
            modifier = Modifier.testTag(CreateHabitUiTestTags.GOAL_CARD),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.md),
            ) {
                CreateHabitSummaryRow(
                    label = "Type",
                    value = uiState.habitType.summaryLabel(),
                )
                CreateHabitSummaryRow(
                    label = "Target",
                    value = uiState.targetValueSummary(),
                )
                CreateHabitSummaryRow(
                    label = "Increment",
                    value = uiState.defaultIncrementSummary(),
                )
            }
        }
        CreateHabitInlineError(
            errorText = uiState.validation.targetValueError?.message() ?: uiState.validation.defaultIncrementError?.message(),
        )
    }
}

@Composable
private fun CreateHabitSummaryRow(
    label: String,
    value: String,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = typography.callout,
            color = colors.textSecondary,
        )
        Text(
            text = value,
            style = typography.bodyStrong,
            color = colors.textPrimary,
        )
    }
}

@Composable
private fun CreateHabitScheduleCard(
    uiState: CreateHabitUiState,
    onIntent: (CreateHabitIntent) -> Unit,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    RoundedCardSurface {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            SegmentedControl(
                options = createHabitScheduleOptions,
                selectedValue = uiState.schedulePreset,
                onValueSelected = { preset ->
                    onIntent(CreateHabitIntent.SchedulePresetSelected(preset))
                },
            )
            if (uiState.schedulePreset == CreateHabitSchedulePreset.CUSTOM) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                ) {
                    DayOfWeek.values().forEach { dayOfWeek ->
                        CreateHabitWeekdayChip(
                            dayOfWeek = dayOfWeek,
                            selected = dayOfWeek in uiState.selectedDays,
                            onClick = { onIntent(CreateHabitIntent.CustomWeekdayToggled(dayOfWeek)) },
                            modifier = Modifier.testTag(CreateHabitUiTestTags.weekdayChip(dayOfWeek)),
                        )
                    }
                }
                CreateHabitInlineError(errorText = uiState.validation.scheduleError?.message())
            }
            HabitTrackerSeparator()
            ToggleRow(
                title = "Multiple updates per day",
                supportingText = "Allow more than one update on the same date.",
                checked = uiState.allowsMultipleUpdatesPerDay,
                onCheckedChange = { onIntent(CreateHabitIntent.MultipleUpdatesPerDayChanged(it)) },
            )
        }
    }
}

@Composable
private fun CreateHabitWeekdayChip(
    dayOfWeek: DayOfWeek,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val shapes = HabitTrackerDesignSystem.shapes
    val spacing = HabitTrackerDesignSystem.spacing
    val interactionSource = remember(dayOfWeek) { MutableInteractionSource() }

    Surface(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
        ),
        color = if (selected) colors.surfaceInverse else colors.surfacePrimary,
        contentColor = if (selected) colors.textOnInverse else colors.textPrimary,
        shape = shapes.pill,
        border = BorderStroke(spacing.hairline, if (selected) Color.Transparent else colors.strokeSubtle),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = spacing.md, vertical = spacing.sm),
            text = dayOfWeek.shortLabel(),
            style = typography.label,
            color = if (selected) colors.textOnInverse else colors.textSecondary,
        )
    }
}

@Composable
private fun CreateHabitAddReminderCard(
    onClick: () -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val spacing = HabitTrackerDesignSystem.spacing

    RoundedCardSurface(
        onClick = onClick,
        modifier = Modifier.testTag(CreateHabitUiTestTags.ADD_REMINDER_BUTTON),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.xxs),
            ) {
                Text(
                    text = "Add Reminder",
                    style = typography.bodyStrong,
                    color = colors.textPrimary,
                )
                Text(
                    text = "Use the current habit schedule for notifications.",
                    style = typography.callout,
                    color = colors.textSecondary,
                )
            }
            Text(
                text = "+",
                style = typography.sectionTitle,
                color = colors.accentPrimary,
            )
        }
    }
}

@Composable
private fun CreateHabitReminderCard(
    reminder: CreateHabitReminderItem,
    selectedDays: Set<DayOfWeek>,
    onEnabledChanged: (Boolean) -> Unit,
    onRemove: () -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.xxs),
                ) {
                    Text(
                        text = reminder.time.format(reminderTimeFormatter),
                        style = typography.cardTitle,
                        color = colors.textPrimary,
                    )
                    Text(
                        text = selectedDays.scheduleSummary(),
                        style = typography.callout,
                        color = colors.textSecondary,
                    )
                }
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember(reminder.reminderId) { MutableInteractionSource() },
                        indication = null,
                        onClick = onRemove,
                    ),
                    text = "Remove",
                    style = typography.label,
                    color = colors.accentDanger,
                )
            }
            HabitTrackerSeparator()
            ToggleRow(
                title = "Enabled",
                checked = reminder.isEnabled,
                onCheckedChange = onEnabledChanged,
            )
        }
    }
}

@Composable
private fun CreateHabitIconPickerScreen(
    selectedIconToken: String?,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onIconSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = HabitTrackerDesignSystem.spacing
    val iconSections = iconCatalogSections(searchQuery)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag(CreateHabitUiTestTags.ICON_PICKER),
        contentPadding = PaddingValues(
            start = spacing.screenEdge,
            top = spacing.sm,
            end = spacing.screenEdge,
            bottom = spacing.xxl,
        ),
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
    ) {
        item {
            HabitTrackerSearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChanged,
                placeholder = "Search icons",
                modifier = Modifier.testTag(CreateHabitUiTestTags.ICON_SEARCH_FIELD),
            )
        }
        if (iconSections.isEmpty()) {
            item {
                CreateHabitPickerEmptyState(message = "No icons match your search.")
            }
        } else {
            iconSections.forEach { section ->
                item {
                    SectionHeader(title = section.title)
                }
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(spacing.md),
                        verticalArrangement = Arrangement.spacedBy(spacing.md),
                    ) {
                        section.items.forEach { option ->
                            SelectableIconTile(
                                title = option.label,
                                selected = option.token == selectedIconToken,
                                onClick = { onIconSelected(option.token) },
                                modifier = Modifier
                                    .width(108.dp)
                                    .testTag(CreateHabitUiTestTags.iconOption(option.token)),
                                icon = {
                                    Text(
                                        text = option.monogram,
                                        textAlign = TextAlign.Center,
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateHabitColorPickerScreen(
    selectedColorToken: String?,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onColorSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = HabitTrackerDesignSystem.spacing
    val colors = HabitTrackerDesignSystem.colors
    val colorSections = colorCatalogSections(searchQuery)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag(CreateHabitUiTestTags.COLOR_PICKER),
        contentPadding = PaddingValues(
            start = spacing.screenEdge,
            top = spacing.sm,
            end = spacing.screenEdge,
            bottom = spacing.xxl,
        ),
        verticalArrangement = Arrangement.spacedBy(spacing.lg),
    ) {
        item {
            HabitTrackerSearchBar(
                value = searchQuery,
                onValueChange = onSearchQueryChanged,
                placeholder = "Search colors",
                modifier = Modifier.testTag(CreateHabitUiTestTags.COLOR_SEARCH_FIELD),
            )
        }
        if (colorSections.isEmpty()) {
            item {
                CreateHabitPickerEmptyState(message = "No colors match your search.")
            }
        } else {
            colorSections.forEach { section ->
                item {
                    SectionHeader(title = section.title)
                }
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(spacing.lg),
                        verticalArrangement = Arrangement.spacedBy(spacing.lg),
                    ) {
                        section.items.forEach { option ->
                            Box(
                                modifier = Modifier
                                    .testTag(CreateHabitUiTestTags.colorOption(option.token))
                                    .clickable(
                                        interactionSource = remember(option.token) { MutableInteractionSource() },
                                        indication = null,
                                        onClick = { onColorSelected(option.token) },
                                    ),
                            ) {
                                SelectableColorTile(
                                    color = option.token.toAccentColor(colors),
                                    label = option.label,
                                    selected = option.token == selectedColorToken,
                                    onClick = { onColorSelected(option.token) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateHabitPickerEmptyState(
    message: String,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        tone = HabitTrackerSurfaceTone.Secondary,
    ) {
        Text(
            text = message,
            style = typography.body,
            color = colors.textSecondary,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateHabitGoalSheet(
    uiState: CreateHabitUiState,
    onDismissRequest: () -> Unit,
    onIntent: (CreateHabitIntent) -> Unit,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenEdge)
                .padding(bottom = spacing.xxl)
                .testTag(CreateHabitUiTestTags.GOAL_SHEET),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            ModalSheetHeader(
                title = "Goal",
            )
            SegmentedControl(
                options = createHabitTypeOptions,
                selectedValue = uiState.habitType,
                onValueSelected = { habitType ->
                    onIntent(CreateHabitIntent.HabitTypeSelected(habitType))
                },
            )
            if (uiState.habitType == HabitType.QUANTITY) {
                CreateHabitInputField(
                    label = "Target Value",
                    value = uiState.targetValueInput,
                    onValueChange = { onIntent(CreateHabitIntent.TargetValueChanged(it)) },
                    placeholder = "0",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next,
                    ),
                    errorText = uiState.validation.targetValueError?.message(),
                )
                CreateHabitInputField(
                    label = "Default Increment",
                    value = uiState.defaultIncrementInput,
                    onValueChange = { onIntent(CreateHabitIntent.DefaultIncrementChanged(it)) },
                    placeholder = "0",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next,
                    ),
                    errorText = uiState.validation.defaultIncrementError?.message(),
                )
                CreateHabitInputField(
                    label = "Unit Label",
                    value = uiState.unitLabelInput,
                    onValueChange = { onIntent(CreateHabitIntent.UnitLabelChanged(it)) },
                    placeholder = "Unit",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                )
            } else {
                RoundedCardSurface(
                    tone = HabitTrackerSurfaceTone.Secondary,
                ) {
                    Text(
                        text = "Boolean habits save a single completion target.",
                        style = HabitTrackerDesignSystem.typography.callout,
                        color = HabitTrackerDesignSystem.colors.textSecondary,
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateHabitInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    fieldTestTag: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
    ),
    errorText: String? = null,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        Text(
            text = label,
            style = typography.label,
            color = colors.textSecondary,
        )
        Surface(
            color = colors.surfacePrimary,
            shape = HabitTrackerDesignSystem.shapes.large,
            border = BorderStroke(spacing.hairline, if (errorText == null) colors.strokeSubtle else colors.accentDanger),
            shadowElevation = HabitTrackerDesignSystem.elevations.subtle,
        ) {
            androidx.compose.foundation.text.BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .then(
                        if (fieldTestTag != null) {
                            Modifier.testTag(fieldTestTag)
                        } else {
                            Modifier
                        },
                    )
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .padding(horizontal = spacing.lg, vertical = spacing.md),
                singleLine = true,
                textStyle = typography.body.copy(color = colors.textPrimary),
                keyboardOptions = keyboardOptions,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
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
                },
            )
        }
        CreateHabitInlineError(errorText = errorText)
    }
}

@Composable
private fun CreateHabitInlineError(
    errorText: String?,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography

    if (errorText != null) {
        Text(
            text = errorText,
            style = typography.caption,
            color = colors.accentDanger,
        )
    }
}

@Composable
private fun CreateHabitTokenCircle(
    token: String?,
    content: String,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography
    val accentColor = token.toAccentColor(colors)

    Surface(
        color = if (token == null) colors.fillMuted else accentColor.copy(alpha = 0.18f),
        contentColor = if (token == null) colors.textTertiary else accentColor,
        shape = HabitTrackerDesignSystem.shapes.large,
    ) {
        Box(
            modifier = Modifier
                .width(56.dp)
                .padding(vertical = HabitTrackerDesignSystem.spacing.md),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = content,
                style = typography.bodyStrong,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun CreateHabitColorCircle(
    token: String?,
) {
    val colors = HabitTrackerDesignSystem.colors

    Surface(
        color = token.toAccentColor(colors),
        shape = HabitTrackerDesignSystem.shapes.large,
    ) {
        Spacer(
            modifier = Modifier
                .size(56.dp),
        )
    }
}

private enum class CreateHabitPanel {
    FORM,
    ICON_PICKER,
    COLOR_PICKER,
}

private data class CreateHabitIconOption(
    val token: String,
    val label: String,
    val monogram: String,
    val searchTerms: List<String>,
)

private data class CreateHabitColorOption(
    val token: String,
    val label: String,
    val searchTerms: List<String>,
)

private data class CreateHabitCatalogSection<T>(
    val title: String,
    val items: List<T>,
)

private val createHabitTypeOptions = listOf(
    SegmentedControlOption(value = HabitType.BOOLEAN, label = "Boolean"),
    SegmentedControlOption(value = HabitType.QUANTITY, label = "Quantity"),
)

private val createHabitScheduleOptions = listOf(
    SegmentedControlOption(value = CreateHabitSchedulePreset.EVERY_DAY, label = "Every Day"),
    SegmentedControlOption(value = CreateHabitSchedulePreset.WEEKDAYS, label = "Weekdays"),
    SegmentedControlOption(value = CreateHabitSchedulePreset.WEEKENDS, label = "Weekends"),
    SegmentedControlOption(value = CreateHabitSchedulePreset.CUSTOM, label = "Custom"),
)

private val createHabitIconCatalog = listOf(
    CreateHabitIconOption(
        token = "shoe",
        label = "Walk",
        monogram = "W",
        searchTerms = listOf("walk", "shoe", "steps"),
    ),
    CreateHabitIconOption(
        token = "drop",
        label = "Water",
        monogram = "W",
        searchTerms = listOf("water", "drop", "drink"),
    ),
    CreateHabitIconOption(
        token = "book",
        label = "Read",
        monogram = "R",
        searchTerms = listOf("read", "book", "study"),
    ),
    CreateHabitIconOption(
        token = "lotus",
        label = "Meditate",
        monogram = "M",
        searchTerms = listOf("meditate", "lotus", "mindfulness"),
    ),
)

private val createHabitColorCatalog = listOf(
    CreateHabitColorOption(
        token = "blue",
        label = "Blue",
        searchTerms = listOf("blue"),
    ),
    CreateHabitColorOption(
        token = "purple",
        label = "Purple",
        searchTerms = listOf("purple"),
    ),
    CreateHabitColorOption(
        token = "green",
        label = "Green",
        searchTerms = listOf("green"),
    ),
    CreateHabitColorOption(
        token = "orange",
        label = "Orange",
        searchTerms = listOf("orange"),
    ),
    CreateHabitColorOption(
        token = "yellow",
        label = "Yellow",
        searchTerms = listOf("yellow"),
    ),
    CreateHabitColorOption(
        token = "red",
        label = "Red",
        searchTerms = listOf("red"),
    ),
)

private val reminderTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

private fun iconCatalogSections(
    query: String,
): List<CreateHabitCatalogSection<CreateHabitIconOption>> {
    val normalizedQuery = query.trim().lowercase(Locale.US)
    val filtered = if (normalizedQuery.isEmpty()) {
        createHabitIconCatalog
    } else {
        createHabitIconCatalog.filter { option ->
            option.label.lowercase(Locale.US).contains(normalizedQuery) ||
                option.token.lowercase(Locale.US).contains(normalizedQuery) ||
                option.searchTerms.any { searchTerm -> searchTerm.contains(normalizedQuery) }
        }
    }

    return filtered.takeIf(List<CreateHabitIconOption>::isNotEmpty)?.let { items ->
        listOf(CreateHabitCatalogSection(title = "Available", items = items))
    }.orEmpty()
}

private fun colorCatalogSections(
    query: String,
): List<CreateHabitCatalogSection<CreateHabitColorOption>> {
    val normalizedQuery = query.trim().lowercase(Locale.US)
    val filtered = if (normalizedQuery.isEmpty()) {
        createHabitColorCatalog
    } else {
        createHabitColorCatalog.filter { option ->
            option.label.lowercase(Locale.US).contains(normalizedQuery) ||
                option.token.lowercase(Locale.US).contains(normalizedQuery) ||
                option.searchTerms.any { searchTerm -> searchTerm.contains(normalizedQuery) }
        }
    }

    return filtered.takeIf(List<CreateHabitColorOption>::isNotEmpty)?.let { items ->
        listOf(CreateHabitCatalogSection(title = "Available", items = items))
    }.orEmpty()
}

private fun HabitType.summaryLabel(): String = when (this) {
    HabitType.BOOLEAN -> "Boolean"
    HabitType.QUANTITY -> "Quantity"
}

private fun CreateHabitUiState.targetValueSummary(): String = targetValueInput.asValueSummary(unitLabelInput)

private fun CreateHabitUiState.defaultIncrementSummary(): String = defaultIncrementInput.asValueSummary(unitLabelInput)

private fun String.asValueSummary(
    unitLabel: String,
): String {
    val trimmedUnit = unitLabel.trim()
    val trimmedValue = trim()

    return when {
        trimmedValue.isEmpty() -> "-"
        trimmedUnit.isEmpty() -> trimmedValue
        else -> "$trimmedValue $trimmedUnit"
    }
}

private fun Set<DayOfWeek>.scheduleSummary(): String = when {
    size == 7 -> "Every day"
    containsAll(
        setOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
        ),
    ) && !contains(DayOfWeek.SATURDAY) && !contains(DayOfWeek.SUNDAY) -> "Weekdays"

    containsAll(setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)) &&
        size == 2 -> "Weekends"

    else -> sortedBy { dayOfWeek -> dayOfWeek.value }.joinToString(", ") { dayOfWeek -> dayOfWeek.shortLabel() }
}

private fun DayOfWeek.shortLabel(): String = when (this) {
    DayOfWeek.MONDAY -> "Mon"
    DayOfWeek.TUESDAY -> "Tue"
    DayOfWeek.WEDNESDAY -> "Wed"
    DayOfWeek.THURSDAY -> "Thu"
    DayOfWeek.FRIDAY -> "Fri"
    DayOfWeek.SATURDAY -> "Sat"
    DayOfWeek.SUNDAY -> "Sun"
}

private fun CreateHabitNameValidationError.message(): String = when (this) {
    CreateHabitNameValidationError.REQUIRED -> "Enter a habit name."
}

private fun CreateHabitSelectionValidationError.message(): String = when (this) {
    CreateHabitSelectionValidationError.REQUIRED -> "Make a selection."
}

private fun CreateHabitNumericValidationError.message(): String = when (this) {
    CreateHabitNumericValidationError.REQUIRED -> "Enter a value."
    CreateHabitNumericValidationError.INVALID_NUMBER -> "Enter a valid number."
    CreateHabitNumericValidationError.NON_POSITIVE -> "Enter a value greater than zero."
}

private fun CreateHabitScheduleValidationError.message(): String = when (this) {
    CreateHabitScheduleValidationError.NO_DAYS_SELECTED -> "Select at least one day."
}

private fun CreateHabitSaveError.message(): String = when (this) {
    CreateHabitSaveError.GENERIC -> "Couldn't save habit."
}

private fun String?.iconLabel(): String? = createHabitIconCatalog
    .firstOrNull { option -> option.token == this }
    ?.label

private fun String?.colorLabel(): String? = createHabitColorCatalog
    .firstOrNull { option -> option.token == this }
    ?.label

private fun String?.iconMonogram(): String = createHabitIconCatalog
    .firstOrNull { option -> option.token == this }
    ?.monogram
    ?: "+"

private fun String?.toAccentColor(
    colors: com.threemdroid.habittracker.core.designsystem.HabitTrackerColors,
): Color = when (this?.trim()?.lowercase(Locale.US)) {
    "green" -> colors.accentPositive
    "orange", "yellow" -> colors.accentWarning
    "red" -> colors.accentDanger
    "purple" -> colors.accentSecondary
    "blue" -> colors.accentPrimary
    null -> colors.fillMuted
    else -> colors.accentPrimary
}
