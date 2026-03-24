package com.threemdroid.habittracker.feature.activity.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.threemdroid.habittracker.core.designsystem.HabitTrackerDesignSystem
import com.threemdroid.habittracker.core.designsystem.HabitTrackerSurfaceTone
import com.threemdroid.habittracker.core.ui.ChartCardShell
import com.threemdroid.habittracker.core.ui.CircularIconButton
import com.threemdroid.habittracker.core.ui.GradientActionButton
import com.threemdroid.habittracker.core.ui.HabitTrackerSeparator
import com.threemdroid.habittracker.core.ui.ModalSheetHeader
import com.threemdroid.habittracker.core.ui.RoundedCardSurface
import com.threemdroid.habittracker.core.ui.SegmentedControl
import com.threemdroid.habittracker.core.ui.SegmentedControlOption
import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
internal fun ActivityRoute(
) {
    val viewModel: ActivityViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ActivityScreen(
        uiState = uiState,
        onIntent = viewModel::handleIntent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ActivityScreen(
    uiState: ActivityUiState,
    onIntent: (ActivityIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    var isFilterSheetVisible by remember { mutableStateOf(false) }
    val selectedFilterOption = uiState.availableHabitFilters.firstOrNull { option ->
        option.habitId == uiState.selectedHabitId
    }
    val selectedFilterLabel = selectedFilterOption?.name
        ?: uiState.availableHabitFilters.firstOrNull()?.name
        ?: "All Habits"

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surfaceCanvas)
            .testTag(ActivityUiTestTags.ROOT),
        containerColor = colors.surfaceCanvas,
        contentWindowInsets = WindowInsets(0.dp),
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(
                start = spacing.screenEdge,
                top = spacing.sm,
                end = spacing.screenEdge,
                bottom = 120.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            item {
                ActivityTitle()
            }
            item {
                ActivityPeriodTabs(
                    selectedPeriod = uiState.selectedPeriod,
                    onPeriodSelected = { period ->
                        onIntent(ActivityIntent.PeriodSelected(period))
                    },
                )
            }
            item {
                ActivityPeriodHeaderCard(
                    selectedPeriod = uiState.selectedPeriod,
                    periodStartDate = uiState.periodStartDate,
                    periodEndDate = uiState.periodEndDate,
                    selectedFilterLabel = selectedFilterLabel,
                    filterEnabled = uiState.availableHabitFilters.isNotEmpty(),
                    onPreviousPeriodRequested = {
                        onIntent(ActivityIntent.PreviousPeriodRequested)
                    },
                    onNextPeriodRequested = {
                        onIntent(ActivityIntent.NextPeriodRequested)
                    },
                    onFilterRequested = {
                        isFilterSheetVisible = true
                    },
                )
            }

            when (uiState.screenState) {
                ActivityScreenState.Loading -> item {
                    ActivityLoadingCard()
                }

                ActivityScreenState.Empty -> item {
                    ActivityEmptyCard()
                }

                is ActivityScreenState.Error -> item {
                    ActivityErrorCard(
                        onRetry = { onIntent(ActivityIntent.RetryLoad) },
                    )
                }

                ActivityScreenState.Content -> {
                    item {
                        ActivityAnalyticsCard(
                            analyticsSummary = uiState.analyticsSummary,
                        )
                    }
                    item {
                        ActivityChartCard(
                            selectedPeriod = uiState.selectedPeriod,
                            selectedFilterLabel = selectedFilterLabel,
                            chartData = uiState.chartData,
                        )
                    }
                }
            }
        }
    }

    if (isFilterSheetVisible) {
        ActivityHabitFilterSheet(
            options = uiState.availableHabitFilters,
            selectedHabitId = uiState.selectedHabitId,
            onDismissRequest = { isFilterSheetVisible = false },
            onOptionSelected = { habitId ->
                isFilterSheetVisible = false
                onIntent(ActivityIntent.HabitFilterSelected(habitId))
            },
        )
    }
}

@Composable
private fun ActivityTitle(
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val typography = HabitTrackerDesignSystem.typography

    Text(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        text = "Activity",
        style = typography.screenTitle,
        color = colors.textPrimary,
    )
}

@Composable
private fun ActivityPeriodTabs(
    selectedPeriod: ActivityPeriod,
    onPeriodSelected: (ActivityPeriod) -> Unit,
    modifier: Modifier = Modifier,
) {
    SegmentedControl(
        modifier = modifier.fillMaxWidth(),
        options = ActivityPeriod.entries.map { period ->
            SegmentedControlOption(
                value = period,
                label = period.label(),
            )
        },
        selectedValue = selectedPeriod,
        onValueSelected = onPeriodSelected,
    )
}

@Composable
private fun ActivityPeriodHeaderCard(
    selectedPeriod: ActivityPeriod,
    periodStartDate: LocalDate,
    periodEndDate: LocalDate,
    selectedFilterLabel: String,
    filterEnabled: Boolean,
    onPreviousPeriodRequested: () -> Unit,
    onNextPeriodRequested: () -> Unit,
    onFilterRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(ActivityUiTestTags.PERIOD_HEADER),
        tone = HabitTrackerSurfaceTone.Tinted,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularIconButton(
                    modifier = Modifier.testTag(ActivityUiTestTags.PREVIOUS_PERIOD_BUTTON),
                    onClick = onPreviousPeriodRequested,
                ) {
                    Text(
                        text = "<",
                        style = typography.bodyStrong,
                        color = colors.textPrimary,
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(spacing.xxs),
                ) {
                    Text(
                        text = selectedPeriod.label(),
                        style = typography.caption,
                        color = colors.textSecondary,
                    )
                    Text(
                        text = selectedPeriod.headerText(
                            periodStartDate = periodStartDate,
                            periodEndDate = periodEndDate,
                        ),
                        style = typography.cardTitle,
                        color = colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                CircularIconButton(
                    modifier = Modifier.testTag(ActivityUiTestTags.NEXT_PERIOD_BUTTON),
                    onClick = onNextPeriodRequested,
                ) {
                    Text(
                        text = ">",
                        style = typography.bodyStrong,
                        color = colors.textPrimary,
                    )
                }
            }

            HabitTrackerSeparator()

            RoundedCardSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(ActivityUiTestTags.FILTER_BUTTON),
                tone = if (filterEnabled) HabitTrackerSurfaceTone.Raised else HabitTrackerSurfaceTone.Base,
                onClick = onFilterRequested.takeIf { filterEnabled },
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.md),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(spacing.xxs),
                    ) {
                        Text(
                            text = "Habit Filter",
                            style = typography.caption,
                            color = colors.textSecondary,
                        )
                        Text(
                            text = selectedFilterLabel,
                            style = typography.bodyStrong,
                            color = if (filterEnabled) colors.textPrimary else colors.textTertiary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Text(
                        text = if (filterEnabled) "v" else "-",
                        style = typography.bodyStrong,
                        color = if (filterEnabled) colors.textSecondary else colors.textTertiary,
                    )
                }
            }
        }
    }
}

@Composable
private fun ActivityAnalyticsCard(
    analyticsSummary: ActivityAnalyticsSummary,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(ActivityUiTestTags.ANALYTICS_CARD),
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            Text(
                text = "Analytics",
                style = typography.cardTitle,
                color = colors.textPrimary,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ActivityMetricColumn(
                    label = "Avg completion",
                    value = analyticsSummary.averageCompletionRatio.toPercentText(),
                    modifier = Modifier.weight(1f),
                )
                ActivityMetricColumn(
                    label = "Done days",
                    value = analyticsSummary.doneDays.toString(),
                    modifier = Modifier.weight(1f),
                )
                ActivityMetricColumn(
                    label = "Total progress",
                    value = analyticsSummary.totalProgress.toProgressText(),
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun ActivityMetricColumn(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.xxs),
    ) {
        Text(
            text = value,
            style = typography.sectionTitle.copy(fontWeight = FontWeight.Bold),
            color = colors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = label,
            style = typography.caption,
            color = colors.textSecondary,
        )
    }
}

@Composable
private fun ActivityChartCard(
    selectedPeriod: ActivityPeriod,
    selectedFilterLabel: String,
    chartData: List<ActivityChartPoint>,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    ChartCardShell(
        modifier = modifier
            .fillMaxWidth()
            .testTag(ActivityUiTestTags.CHART_CARD),
        title = "Completion",
        subtitle = selectedFilterLabel,
        chartContent = {
            if (chartData.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "No chart data",
                        style = typography.callout,
                        color = colors.textTertiary,
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.xxs),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    chartData.forEachIndexed { index, point ->
                        ActivityChartBar(
                            point = point,
                            label = point.axisLabel(
                                period = selectedPeriod,
                                index = index,
                                totalCount = chartData.size,
                            ),
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        },
    )
}

@Composable
private fun ActivityChartBar(
    point: ActivityChartPoint,
    label: String,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val shapes = HabitTrackerDesignSystem.shapes
    val typography = HabitTrackerDesignSystem.typography
    val barHeight = (96.dp * point.completionRatio.toFloat()).coerceAtLeast(
        if (point.totalProgress > 0.0 || point.completionRatio > 0.0) 14.dp else 8.dp,
    )

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(if (label.length > 3) 0.88f else 0.72f),
                color = colors.fillMuted,
                shape = shapes.pill,
            ) {}
            Surface(
                modifier = Modifier
                    .fillMaxWidth(if (label.length > 3) 0.88f else 0.72f)
                    .height(barHeight),
                color = if (point.isDoneDay) colors.accentPositive else colors.accentPrimary,
                shape = shapes.pill,
            ) {}
        }
        Text(
            text = label,
            style = typography.caption,
            color = colors.textSecondary,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun ActivityLoadingCard(
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(ActivityUiTestTags.LOADING_CARD),
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 220.dp)
                .padding(vertical = spacing.xl)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(28.dp),
                color = colors.accentPrimary,
                strokeWidth = 2.5.dp,
            )
            Text(
                text = "Loading activity...",
                style = typography.bodyStrong,
                color = colors.textPrimary,
            )
        }
    }
}

@Composable
private fun ActivityEmptyCard(
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(ActivityUiTestTags.EMPTY_CARD),
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 220.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.sm, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "No activity yet",
                style = typography.sectionTitle,
                color = colors.textPrimary,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Add a habit or log progress to see analytics here.",
                style = typography.callout,
                color = colors.textSecondary,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun ActivityErrorCard(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(ActivityUiTestTags.ERROR_CARD),
        tone = HabitTrackerSurfaceTone.Raised,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 220.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.md, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Couldn't load activity",
                style = typography.sectionTitle,
                color = colors.textPrimary,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Try again to refresh the current analytics view.",
                style = typography.callout,
                color = colors.textSecondary,
                textAlign = TextAlign.Center,
            )
            GradientActionButton(
                text = "Retry",
                onClick = onRetry,
                modifier = Modifier.testTag(ActivityUiTestTags.RETRY_BUTTON),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActivityHabitFilterSheet(
    options: List<ActivityHabitFilterOption>,
    selectedHabitId: String?,
    onDismissRequest: () -> Unit,
    onOptionSelected: (String?) -> Unit,
) {
    val spacing = HabitTrackerDesignSystem.spacing

    ModalBottomSheet(
        modifier = Modifier.testTag(ActivityUiTestTags.FILTER_SHEET),
        onDismissRequest = onDismissRequest,
        containerColor = HabitTrackerDesignSystem.colors.surfacePrimary,
        scrimColor = HabitTrackerDesignSystem.colors.overlayScrim,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = spacing.screenEdge,
                top = spacing.sm,
                end = spacing.screenEdge,
                bottom = spacing.xl,
            ),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            item {
                ModalSheetHeader(
                    title = "Select Habit",
                    trailingContent = {
                        CircularIconButton(
                            onClick = onDismissRequest,
                        ) {
                            Text(
                                text = "X",
                                style = HabitTrackerDesignSystem.typography.label,
                                color = HabitTrackerDesignSystem.colors.textPrimary,
                            )
                        }
                    },
                )
            }
            items(
                items = options,
                key = { option -> option.habitId ?: "all" },
            ) { option ->
                ActivityFilterOptionCard(
                    option = option,
                    selected = option.habitId == selectedHabitId,
                    onClick = { onOptionSelected(option.habitId) },
                )
            }
        }
    }
}

@Composable
private fun ActivityFilterOptionCard(
    option: ActivityHabitFilterOption,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = HabitTrackerDesignSystem.colors
    val spacing = HabitTrackerDesignSystem.spacing
    val typography = HabitTrackerDesignSystem.typography

    RoundedCardSurface(
        modifier = modifier
            .fillMaxWidth()
            .testTag(ActivityUiTestTags.filterOption(option.habitId)),
        tone = if (selected) HabitTrackerSurfaceTone.Tinted else HabitTrackerSurfaceTone.Raised,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(spacing.xxs),
            ) {
                Text(
                    text = option.name,
                    style = typography.bodyStrong,
                    color = colors.textPrimary,
                )
                if (selected) {
                    Text(
                        text = "Selected",
                        style = typography.caption,
                        color = colors.accentPrimary,
                    )
                }
            }
            Text(
                text = if (selected) "OK" else ">",
                style = typography.bodyStrong,
                color = if (selected) colors.accentPrimary else colors.textSecondary,
            )
        }
    }
}

private fun ActivityPeriod.label(): String = when (this) {
    ActivityPeriod.DAILY -> "Daily"
    ActivityPeriod.WEEKLY -> "Weekly"
    ActivityPeriod.MONTHLY -> "Monthly"
}

private fun ActivityPeriod.headerText(
    periodStartDate: LocalDate,
    periodEndDate: LocalDate,
): String = when (this) {
    ActivityPeriod.DAILY -> periodStartDate.format(longDateFormatter)
    ActivityPeriod.WEEKLY -> when {
        periodStartDate.year != periodEndDate.year ->
            "${periodStartDate.format(shortDateFormatter)} - ${periodEndDate.format(shortDateFormatterWithYear)}"

        periodStartDate.month != periodEndDate.month ->
            "${periodStartDate.format(shortDateFormatter)} - ${periodEndDate.format(shortDateFormatterWithYear)}"

        else -> "${periodStartDate.format(monthDayFormatter)} - ${periodEndDate.format(shortDateFormatterWithYear)}"
    }

    ActivityPeriod.MONTHLY -> periodStartDate.format(monthYearFormatter)
}

private fun ActivityChartPoint.axisLabel(
    period: ActivityPeriod,
    index: Int,
    totalCount: Int,
): String = when (period) {
    ActivityPeriod.DAILY -> date.format(monthDayFormatter)
    ActivityPeriod.WEEKLY -> date.format(weekdayFormatter)
    ActivityPeriod.MONTHLY -> when {
        totalCount <= 8 -> date.dayOfMonth.toString()
        index == 0 || index == totalCount - 1 || date.dayOfMonth % 7 == 1 -> date.dayOfMonth.toString()
        else -> ""
    }
}

private fun Double.toPercentText(): String = "${(this * 100).toInt()}%"

private fun Double.toProgressText(): String =
    if (this % 1.0 == 0.0) {
        this.toInt().toString()
    } else {
        String.format(Locale.US, "%.1f", this)
    }

private val weekdayFormatter = DateTimeFormatter.ofPattern("EEE", Locale.US)
private val monthDayFormatter = DateTimeFormatter.ofPattern("MMM d", Locale.US)
private val shortDateFormatter = DateTimeFormatter.ofPattern("MMM d", Locale.US)
private val shortDateFormatterWithYear = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.US)
private val longDateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d", Locale.US)
private val monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.US)
