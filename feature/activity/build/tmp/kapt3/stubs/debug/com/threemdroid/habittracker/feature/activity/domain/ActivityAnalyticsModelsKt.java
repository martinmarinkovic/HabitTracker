package com.threemdroid.habittracker.feature.activity.domain;

import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary;
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint;
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0000\u001a\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0000\u001a \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u00a8\u0006\u0016"}, d2 = {"windowFor", "Lcom/threemdroid/habittracker/feature/activity/domain/ActivityPeriodWindow;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "anchorDate", "Ljava/time/LocalDate;", "previousAnchorDate", "nextAnchorDate", "datesBetween", "", "startDate", "endDate", "progressRatio", "", "progressValue", "targetValue", "habitWasScheduledOn", "", "habit", "Lcom/threemdroid/habittracker/core/model/habits/Habit;", "date", "zoneId", "Ljava/time/ZoneId;", "activity_debug"})
public final class ActivityAnalyticsModelsKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final com.threemdroid.habittracker.feature.activity.domain.ActivityPeriodWindow windowFor(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod $this$windowFor, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate anchorDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.time.LocalDate previousAnchorDate(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod $this$previousAnchorDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate anchorDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.time.LocalDate nextAnchorDate(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod $this$nextAnchorDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate anchorDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<java.time.LocalDate> datesBetween(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate endDate) {
        return null;
    }
    
    public static final double progressRatio(double progressValue, double targetValue) {
        return 0.0;
    }
    
    public static final boolean habitWasScheduledOn(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.Habit habit, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    java.time.ZoneId zoneId) {
        return false;
    }
}