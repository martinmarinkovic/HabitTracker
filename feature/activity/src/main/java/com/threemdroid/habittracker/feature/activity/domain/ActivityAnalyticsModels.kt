package com.threemdroid.habittracker.feature.activity.domain

import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

internal data class ActivityPeriodWindow(
    val startDate: LocalDate,
    val endDate: LocalDate,
)

internal data class ActivitySnapshot(
    val selectedPeriod: ActivityPeriod,
    val periodAnchorDate: LocalDate,
    val periodStartDate: LocalDate,
    val periodEndDate: LocalDate,
    val selectedHabitId: String?,
    val availableHabitFilters: List<ActivityHabitFilterOption>,
    val chartData: List<ActivityChartPoint>,
    val analyticsSummary: ActivityAnalyticsSummary,
)

internal fun ActivityPeriod.windowFor(anchorDate: LocalDate): ActivityPeriodWindow = when (this) {
    ActivityPeriod.DAILY -> ActivityPeriodWindow(
        startDate = anchorDate,
        endDate = anchorDate,
    )

    ActivityPeriod.WEEKLY -> ActivityPeriodWindow(
        startDate = anchorDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
        endDate = anchorDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)),
    )

    ActivityPeriod.MONTHLY -> ActivityPeriodWindow(
        startDate = anchorDate.withDayOfMonth(1),
        endDate = anchorDate.withDayOfMonth(anchorDate.lengthOfMonth()),
    )
}

internal fun ActivityPeriod.previousAnchorDate(anchorDate: LocalDate): LocalDate = when (this) {
    ActivityPeriod.DAILY -> anchorDate.minusDays(1)
    ActivityPeriod.WEEKLY -> anchorDate.minusWeeks(1)
    ActivityPeriod.MONTHLY -> anchorDate.minusMonths(1)
}

internal fun ActivityPeriod.nextAnchorDate(anchorDate: LocalDate): LocalDate = when (this) {
    ActivityPeriod.DAILY -> anchorDate.plusDays(1)
    ActivityPeriod.WEEKLY -> anchorDate.plusWeeks(1)
    ActivityPeriod.MONTHLY -> anchorDate.plusMonths(1)
}

internal fun datesBetween(
    startDate: LocalDate,
    endDate: LocalDate,
): List<LocalDate> {
    if (endDate.isBefore(startDate)) {
        return emptyList()
    }

    return generateSequence(startDate) { currentDate ->
        currentDate.plusDays(1).takeUnless { it.isAfter(endDate) }
    }.toList()
}

internal fun progressRatio(
    progressValue: Double,
    targetValue: Double,
): Double {
    if (targetValue <= 0.0) {
        return 0.0
    }

    return (progressValue / targetValue).coerceIn(0.0, 1.0)
}

internal fun habitWasScheduledOn(
    habit: com.threemdroid.habittracker.core.model.habits.Habit,
    date: LocalDate,
    zoneId: ZoneId,
): Boolean {
    val createdDate = habit.createdAt.atZone(zoneId).toLocalDate()
    if (date.isBefore(createdDate)) {
        return false
    }

    val stoppedDate = habit.stoppedAt?.atZone(zoneId)?.toLocalDate()
    if (stoppedDate != null && date.isAfter(stoppedDate)) {
        return false
    }

    return habit.schedule.selectedDays.isEmpty() || date.dayOfWeek in habit.schedule.selectedDays
}
