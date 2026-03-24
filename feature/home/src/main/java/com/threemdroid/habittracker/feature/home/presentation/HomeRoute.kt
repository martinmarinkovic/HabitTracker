package com.threemdroid.habittracker.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone
import com.threemdroid.habittracker.core.ui.CircularIconButton
import com.threemdroid.habittracker.core.ui.DateCapsuleDaySelector
import com.threemdroid.habittracker.core.ui.FloatingBottomNavigationContainer
import com.threemdroid.habittracker.core.ui.FloatingBottomNavigationItem
import com.threemdroid.habittracker.core.ui.GradientActionButton
import com.threemdroid.habittracker.core.ui.HabitTrackerProgressRing
import com.threemdroid.habittracker.core.ui.HabitTrackerSeparator
import com.threemdroid.habittracker.core.ui.InsetSeparator
import com.threemdroid.habittracker.core.ui.ModalSheetHeader
import com.threemdroid.habittracker.core.ui.RoundedCardSurface
import com.threemdroid.habittracker.core.ui.SectionHeader
import com.threemdroid.habittracker.core.ui.SettingsRow
import com.threemdroid.habittracker.feature.home.contract.HomeActionError
import com.threemdroid.habittracker.feature.home.contract.HomeEffect
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingTimeOfDay
import com.threemdroid.habittracker.feature.home.contract.HomeHabitItem
import com.threemdroid.habittracker.feature.home.contract.HomeIntent
import com.threemdroid.habittracker.feature.home.contract.HomeLoadError
import com.threemdroid.habittracker.feature.home.contract.HomeQuickAddOverlayState
import com.threemdroid.habittracker.feature.home.contract.HomeScreenState
import com.threemdroid.habittracker.feature.home.contract.HomeUiState
import com.threemdroid.habittracker.feature.home.contract.QuickAddValidationError
import java.time.LocalDate
import java.util.Locale

@Composable
internal fun HomeRoute(
    viewModelFactory: ViewModelProvider.Factory,
    onCreateHabitRequested: () -> Unit,
    onNavigateToActivity: () -> Unit,
    onNavigateToLearn: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onEditHabitRequested: ((String) -> Unit)?,
) {
    val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToEditHabit -> onEditHabitRequested?.invoke(effect.habitId)
                is HomeEffect.ShowActionError -> snackbarHostState.showSnackbar(effect.error.message())
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onIntent = viewModel::handleIntent,
        onCreateHabitRequested = onCreateHabitRequested,
        onNavigateToActivity = onNavigateToActivity,
        onNavigateToLearn = onNavigateToLearn,
        onNavigateToProfile = onNavigateToProfile,
        canEditHabit = onEditHabitRequested != null,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    snackbarHostState: SnackbarHostState,
    onIntent: (HomeIntent) -> Unit,
    onCreateHabitRequested: () -> Unit,
    onNavigateToActivity: () -> Unit,
    onNavigateToLearn: () -> Unit,
    onNavigateToProfile: () -> Unit,
    canEditHabit: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    var contextMenuHabitId by remember { mutableStateOf<String?>(null) }
    val contextMenuHabit = uiState.habitsToday.firstOrNull { habit -> habit.habitId == contextMenuHabitId }

    LaunchedEffect(contextMenuHabit, uiState.quickAddOverlayState.isVisible) {
        if (uiState.quickAddOverlayState.isVisible || contextMenuHabitId != null && contextMenuHabit == null) {
            contextMenuHabitId = null
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceCanvas)
            .testTag(HomeUiTestTags.ROOT),
        containerColor = colors.surfaceCanvas,
        contentWindowInsets = WindowInsets(0.dp),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.navigationBarsPadding(),
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = spacing.screenEdge,
                    top = spacing.lg,
                    end = spacing.screenEdge,
                    bottom = 196.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(spacing.lg),
            ) {
                item {
                    HomeHeader(
                        uiState = uiState,
                    )
                }
                item {
                    HomeDateSelector(
                        selectedDate = uiState.selectedDate,
                        onDateSelected = { date -> onIntent(HomeIntent.SelectDate(date)) },
                    )
                }
                item {
                    SectionHeader(
                        title = "Habits Today",
                        supportingText = uiState.habitsTodaySectionSubtitle(),
                    )
                }

                when (uiState.screenState) {
                    HomeScreenState.Loading -> item {
                        HomeLoadingCard()
                    }

                    HomeScreenState.Empty -> item {
                        HomeEmptyCard(
                            onCreateHabitRequested = onCreateHabitRequested,
                        )
                    }

                    is HomeScreenState.Error -> item {
                        HomeErrorCard(
                            error = uiState.screenState.error,
                            onRetry = { onIntent(HomeIntent.RetryLoad) },
                        )
                    }

                    HomeScreenState.Content -> items(
                        items = uiState.habitsToday,
                        key = HomeHabitItem::habitId,
                    ) { habit ->
                        HomeHabitCard(
                            habit = habit,
                            modifier = Modifier.testTag(HomeUiTestTags.habitCard(habit.habitId)),
                            onPrimaryAction = {
                                when (habit.type) {
                                    com.threemdroid.habittracker.core.model.habits.HabitType.BOOLEAN ->
                                        onIntent(HomeIntent.BooleanHabitTapped(habit.habitId))
                                    com.threemdroid.habittracker.core.model.habits.HabitType.QUANTITY ->
                                        onIntent(HomeIntent.QuantityHabitTapped(habit.habitId))
                                }
                            },
                            onManageClick = {
                                contextMenuHabitId = habit.habitId
                            },
                        )
                    }
                }
            }

            HomeFloatingNavigation(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .safeDrawingPadding()
                    .padding(horizontal = spacing.screenEdge, vertical = spacing.md),
                onCreateHabitRequested = onCreateHabitRequested,
                onNavigateToActivity = onNavigateToActivity,
                onNavigateToLearn = onNavigateToLearn,
                onNavigateToProfile = onNavigateToProfile,
            )
        }
    }

    if (uiState.quickAddOverlayState.isVisible) {
        HomeQuickAddSheet(
            overlayState = uiState.quickAddOverlayState,
            onValueChange = { value -> onIntent(HomeIntent.QuickAddValueChanged(value)) },
            onDismiss = { onIntent(HomeIntent.DismissQuickAdd) },
            onConfirm = { onIntent(HomeIntent.ConfirmQuickAdd) },
        )
    } else if (contextMenuHabit != null) {
        HomeHabitContextSheet(
            habit = contextMenuHabit,
            showEditHabitAction = canEditHabit,
            onDismiss = { contextMenuHabitId = null },
            onEditHabit = {
                contextMenuHabitId = null
                onIntent(HomeIntent.EditHabitRequested(contextMenuHabit.habitId))
            },
            onStopHabit = {
                contextMenuHabitId = null
                onIntent(HomeIntent.StopHabitRequested(contextMenuHabit.habitId))
            },
        )
    }
}

@Composable
private fun HomeHeader(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.xxs),
        ) {
            Text(
                text = uiState.greetingAreaState.timeOfDay.greetingText(),
                style = typography.screenTitle,
                color = colors.textPrimary,
            )
            Text(
                text = uiState.selectedDate.headerDateText(isToday = uiState.greetingAreaState.isSelectedDateToday),
                style = typography.callout,
                color = colors.textSecondary,
            )
        }

        HomeMoodButton(
            selectedMoodToken = uiState.moodSelectionState.selectedMoodToken,
            modifier = Modifier
                .width(120.dp)
                .testTag(HomeUiTestTags.MOOD_BUTTON),
        )
    }
}

@Composable
private fun HomeMoodButton(
    selectedMoodToken: String?,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier.alpha(0.92f),
        tone = HabitTrackerSurfaceTone.Tinted,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.md, vertical = spacing.sm),
            verticalArrangement = Arrangement.spacedBy(spacing.xxs),
        ) {
            Text(
                text = "Mood",
                style = typography.caption,
                color = colors.textSecondary,
            )
            Text(
                text = selectedMoodToken?.toDisplayToken() ?: "Not set",
                style = typography.bodyStrong,
                color = if (selectedMoodToken != null) colors.textPrimary else colors.textTertiary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun HomeDateSelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        items(dateSelectorDates(selectedDate)) { date ->
            DateCapsuleDaySelector(
                modifier = Modifier.testTag(HomeUiTestTags.dateSelector(date.toString())),
                primaryLabel = date.dayOfWeek.displayLabel(),
                secondaryLabel = date.dayOfMonth.toString(),
                selected = date == selectedDate,
                onClick = { onDateSelected(date) },
            )
        }
    }
}

@Composable
private fun HomeHabitCard(
    habit: HomeHabitItem,
    onPrimaryAction: () -> Unit,
    onManageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography
    val accentColor = habit.selectedColorToken.toAccentColor()
    val progress = habit.progressFraction()

    RoundedCardSurface(
        modifier = modifier,
        tone = if (habit.isCompleted) HabitTrackerSurfaceTone.Tinted else HabitTrackerSurfaceTone.Raised,
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
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = accentColor.copy(alpha = 0.14f),
                    contentColor = accentColor,
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = habit.selectedIconToken.toIconMonogram(habit.name),
                            style = typography.bodyStrong.copy(fontWeight = FontWeight.SemiBold),
                            textAlign = TextAlign.Center,
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(spacing.xxs),
                ) {
                    Text(
                        text = habit.name,
                        style = typography.cardTitle,
                        color = colors.textPrimary,
                    )
                    Text(
                        text = habit.progressSummary(),
                        style = typography.callout,
                        color = colors.textSecondary,
                    )
                    if (habit.isCompleted) {
                        Text(
                            text = "Completed",
                            style = typography.caption,
                            color = colors.accentPositive,
                        )
                    }
                }

                CircularIconButton(
                    modifier = Modifier
                        .testTag(HomeUiTestTags.habitManageAction(habit.habitId))
                        .semantics { contentDescription = "Manage ${habit.name}" },
                    onClick = onManageClick,
                    size = 40.dp,
                ) {
                    Text(
                        text = "···",
                        style = typography.label,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                ) {
                    Text(
                        text = habit.secondaryProgressText(),
                        style = typography.caption,
                        color = colors.textSecondary,
                    )
                    RoundedCardSurface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(HomeUiTestTags.habitPrimaryAction(habit.habitId)),
                        tone = if (habit.isCompleted) HabitTrackerSurfaceTone.Secondary else HabitTrackerSurfaceTone.Tinted,
                        onClick = onPrimaryAction,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = spacing.md, vertical = spacing.sm),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = habit.primaryActionLabel(),
                                style = typography.bodyStrong,
                                color = colors.textPrimary,
                            )
                            Surface(
                                modifier = Modifier.size(28.dp),
                                shape = CircleShape,
                                color = if (habit.isCompleted) colors.fillStrong else accentColor.copy(alpha = 0.16f),
                                contentColor = if (habit.isCompleted) colors.textSecondary else accentColor,
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = if (habit.type == com.threemdroid.habittracker.core.model.habits.HabitType.BOOLEAN) "\u2713" else "+",
                                        style = typography.label,
                                    )
                                }
                            }
                        }
                    }
                }

                HabitTrackerProgressRing(
                    progress = progress,
                    ringSize = 62.dp,
                    strokeWidth = 6.dp,
                    modifier = Modifier.semantics {
                        contentDescription = "${habit.name} progress ${((progress) * 100).toInt()} percent"
                    },
                ) {
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = typography.caption,
                        color = colors.textPrimary,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeLoadingCard(
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(HomeUiTestTags.LOADING_STATE),
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing.xxl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            CircularProgressIndicator(color = colors.accentPrimary)
            Text(
                text = "Loading habits…",
                style = typography.body,
                color = colors.textPrimary,
            )
        }
    }
}

@Composable
private fun HomeEmptyCard(
    onCreateHabitRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(HomeUiTestTags.EMPTY_STATE),
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "No habits scheduled.",
                style = typography.cardTitle,
                color = colors.textPrimary,
            )
            Text(
                text = "Create your first habit to start filling this day.",
                style = typography.callout,
                color = colors.textSecondary,
            )
            GradientActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(HomeUiTestTags.EMPTY_CREATE_HABIT_BUTTON),
                text = "Create Habit",
                onClick = onCreateHabitRequested,
                leadingContent = {
                    Text(text = "+")
                },
            )
        }
    }
}

@Composable
private fun HomeErrorCard(
    error: HomeLoadError,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(HomeUiTestTags.ERROR_STATE),
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            Text(
                text = when (error) {
                    HomeLoadError.GENERIC -> "We couldn’t load this day."
                },
                style = typography.cardTitle,
                color = colors.textPrimary,
            )
            Text(
                text = "Try again to refresh your Home data.",
                style = typography.callout,
                color = colors.textSecondary,
            )
            GradientActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(HomeUiTestTags.RETRY_BUTTON),
                text = "Retry",
                onClick = onRetry,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeHabitContextSheet(
    habit: HomeHabitItem,
    showEditHabitAction: Boolean,
    onDismiss: () -> Unit,
    onEditHabit: () -> Unit,
    onStopHabit: () -> Unit,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    ModalBottomSheet(
        modifier = Modifier.testTag(HomeUiTestTags.CONTEXT_SHEET),
        onDismissRequest = onDismiss,
        containerColor = HabitTrackerDesignSystem.colors.surfacePrimary,
        contentColor = HabitTrackerDesignSystem.colors.textPrimary,
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenEdge)
                .padding(bottom = spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            ModalSheetHeader(
                title = habit.name,
                subtitle = "Habit actions",
            )
            HabitTrackerSeparator()
            if (showEditHabitAction) {
                SettingsRow(
                    title = "Edit Habit",
                    onClick = onEditHabit,
                )
                InsetSeparator(insetStart = 0.dp, insetEnd = 0.dp)
            }
            SettingsRow(
                title = "Stop Habit",
                destructive = true,
                onClick = onStopHabit,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeQuickAddSheet(
    overlayState: HomeQuickAddOverlayState,
    onValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    ModalBottomSheet(
        modifier = Modifier.testTag(HomeUiTestTags.QUICK_ADD_SHEET),
        onDismissRequest = onDismiss,
        containerColor = colors.surfacePrimary,
        contentColor = colors.textPrimary,
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenEdge)
                .padding(bottom = spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            ModalSheetHeader(
                title = overlayState.habitName ?: "Quick Add",
                subtitle = "Enter progress",
            )

            RoundedCardSurface(
                modifier = Modifier.fillMaxWidth(),
                tone = HabitTrackerSurfaceTone.Secondary,
            ) {
                BasicTextField(
                    value = overlayState.draftValue,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.lg, vertical = spacing.lg),
                    singleLine = true,
                    textStyle = typography.screenTitle.copy(
                        color = colors.textPrimary,
                        textAlign = TextAlign.Center,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(onDone = { onConfirm() }),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (overlayState.draftValue.isBlank()) {
                                Text(
                                    text = "0",
                                    style = typography.screenTitle,
                                    color = colors.textTertiary,
                                )
                            }
                            innerTextField()
                        }
                    },
                )
            }

            if (overlayState.validationError != null) {
                Text(
                    text = overlayState.validationError.message(),
                    style = typography.callout,
                    color = colors.accentDanger,
                )
            }

            GradientActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Save Progress",
                onClick = onConfirm,
            )
        }
    }
}

@Composable
private fun HomeFloatingNavigation(
    onCreateHabitRequested: () -> Unit,
    onNavigateToActivity: () -> Unit,
    onNavigateToLearn: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        GradientActionButton(
            modifier = Modifier
                .width(208.dp)
                .testTag(HomeUiTestTags.FLOATING_CREATE_HABIT_BUTTON),
            text = "New Habit",
            onClick = onCreateHabitRequested,
            leadingContent = {
                Text(
                    text = "+",
                    style = typography.bodyStrong,
                )
            },
        )

        FloatingBottomNavigationContainer(
            modifier = Modifier.fillMaxWidth(),
        ) {
            FloatingBottomNavigationItem(
                selected = true,
                label = "Home",
                onClick = {},
                modifier = Modifier.weight(1f),
                icon = {
                    Text(text = "H", style = typography.label)
                },
            )
            FloatingBottomNavigationItem(
                selected = false,
                label = "Activity",
                onClick = onNavigateToActivity,
                modifier = Modifier.weight(1f),
                icon = {
                    Text(text = "A", style = typography.label)
                },
            )
            FloatingBottomNavigationItem(
                selected = false,
                label = "Learn",
                onClick = onNavigateToLearn,
                modifier = Modifier.weight(1f),
                icon = {
                    Text(text = "L", style = typography.label)
                },
            )
            FloatingBottomNavigationItem(
                selected = false,
                label = "Profile",
                onClick = onNavigateToProfile,
                modifier = Modifier.weight(1f),
                icon = {
                    Text(text = "P", style = typography.label)
                },
            )
        }
    }
}

private fun HomeUiState.habitsTodaySectionSubtitle(): String = when (screenState) {
    HomeScreenState.Loading -> "Preparing your day"
    HomeScreenState.Empty -> "Nothing is scheduled for this date"
    is HomeScreenState.Error -> "Unable to show your habits"
    HomeScreenState.Content -> "${habitsToday.size} habits"
}

private fun HomeGreetingTimeOfDay.greetingText(): String = when (this) {
    HomeGreetingTimeOfDay.MORNING -> "Good morning"
    HomeGreetingTimeOfDay.AFTERNOON -> "Good afternoon"
    HomeGreetingTimeOfDay.EVENING -> "Good evening"
}

private fun LocalDate.headerDateText(isToday: Boolean): String =
    if (isToday) {
        "Today"
    } else {
        "${dayOfWeek.displayLabel()}, ${month.displayLabel()} $dayOfMonth"
    }

private fun java.time.DayOfWeek.displayLabel(): String =
    name.lowercase(Locale.US).replaceFirstChar(Char::titlecase).take(3)

private fun java.time.Month.displayLabel(): String =
    name.lowercase(Locale.US).replaceFirstChar(Char::titlecase).take(3)

private fun HomeActionError.message(): String = when (this) {
    HomeActionError.HABIT_PROGRESS_SAVE_FAILED -> "We couldn’t save your habit progress."
    HomeActionError.MOOD_SAVE_FAILED -> "We couldn’t save your mood."
    HomeActionError.STOP_HABIT_FAILED -> "We couldn’t update this habit."
}

private fun QuickAddValidationError.message(): String = when (this) {
    QuickAddValidationError.INVALID_NUMBER -> "Enter a valid number."
    QuickAddValidationError.NON_POSITIVE -> "Enter a value greater than zero."
}

private fun dateSelectorDates(selectedDate: LocalDate): List<LocalDate> =
    (-3L..3L).map { offset -> selectedDate.plusDays(offset) }

private fun HomeHabitItem.progressSummary(): String {
    val targetLabel = targetValue.asDisplayNumber()
    val progressLabel = progressValue.asDisplayNumber()
    val unitSuffix = unitLabel?.let { " $it" }.orEmpty()
    return "$progressLabel / $targetLabel$unitSuffix"
}

private fun HomeHabitItem.secondaryProgressText(): String = when (type) {
    com.threemdroid.habittracker.core.model.habits.HabitType.BOOLEAN ->
        if (isCompleted) "Logged for this day" else "Tap when you complete it"
    com.threemdroid.habittracker.core.model.habits.HabitType.QUANTITY ->
        "Quick add ${defaultIncrement.asDisplayNumber()}${unitLabel?.let { " $it" }.orEmpty()}"
}

private fun HomeHabitItem.primaryActionLabel(): String = when (type) {
    com.threemdroid.habittracker.core.model.habits.HabitType.BOOLEAN ->
        if (isCompleted) "Logged" else "Mark Done"
    com.threemdroid.habittracker.core.model.habits.HabitType.QUANTITY ->
        "Quick Add"
}

private fun HomeHabitItem.progressFraction(): Float =
    if (targetValue <= 0.0) {
        0f
    } else {
        (progressValue / targetValue).toFloat().coerceIn(0f, 1f)
    }

private fun Double.asDisplayNumber(): String =
    if (this % 1.0 == 0.0) {
        toInt().toString()
    } else {
        toString()
    }

private fun String.toDisplayToken(): String =
    split('_', '-', ' ')
        .filter(String::isNotBlank)
        .joinToString(" ") { segment ->
            segment.lowercase(Locale.US).replaceFirstChar(Char::titlecase)
        }
        .ifBlank { "Not set" }

private fun String.toIconMonogram(fallbackText: String): String =
    trim().firstOrNull()?.uppercaseChar()?.toString()
        ?: fallbackText.firstOrNull()?.uppercaseChar()?.toString()
        ?: "H"

@Composable
private fun String.toAccentColor() = when (lowercase(Locale.US)) {
    "green" -> HabitTrackerDesignSystem.colors.accentPositive
    "red" -> HabitTrackerDesignSystem.colors.accentDanger
    "orange", "yellow" -> HabitTrackerDesignSystem.colors.accentWarning
    "purple" -> HabitTrackerDesignSystem.colors.accentSecondary
    else -> HabitTrackerDesignSystem.colors.accentPrimary
}
