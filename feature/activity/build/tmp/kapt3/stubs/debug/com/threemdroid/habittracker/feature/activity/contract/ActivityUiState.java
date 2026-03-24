package com.threemdroid.habittracker.feature.activity.contract;

import java.time.LocalDate;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 42\u00020\u0001:\u00014B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bH\u00c6\u0003J\t\u0010+\u001a\u00020\u0010H\u00c6\u0003J\t\u0010,\u001a\u00020\u0012H\u00c6\u0003Jq\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u00c6\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u000202H\u00d6\u0001J\t\u00103\u001a\u00020\tH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\u00a8\u00065"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityUiState;", "", "selectedPeriod", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "periodAnchorDate", "Ljava/time/LocalDate;", "periodStartDate", "periodEndDate", "selectedHabitId", "", "availableHabitFilters", "", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityHabitFilterOption;", "chartData", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityChartPoint;", "analyticsSummary", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityAnalyticsSummary;", "screenState", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;", "<init>", "(Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;Ljava/time/LocalDate;Ljava/time/LocalDate;Ljava/time/LocalDate;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/threemdroid/habittracker/feature/activity/contract/ActivityAnalyticsSummary;Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;)V", "getSelectedPeriod", "()Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "getPeriodAnchorDate", "()Ljava/time/LocalDate;", "getPeriodStartDate", "getPeriodEndDate", "getSelectedHabitId", "()Ljava/lang/String;", "getAvailableHabitFilters", "()Ljava/util/List;", "getChartData", "getAnalyticsSummary", "()Lcom/threemdroid/habittracker/feature/activity/contract/ActivityAnalyticsSummary;", "getScreenState", "()Lcom/threemdroid/habittracker/feature/activity/contract/ActivityScreenState;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "activity_debug"})
public final class ActivityUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod selectedPeriod = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDate periodAnchorDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDate periodStartDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDate periodEndDate = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String selectedHabitId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> availableHabitFilters = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint> chartData = null;
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary analyticsSummary = null;
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState screenState = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.threemdroid.habittracker.feature.activity.contract.ActivityUiState.Companion Companion = null;
    
    public ActivityUiState(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod selectedPeriod, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate periodAnchorDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate periodStartDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate periodEndDate, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedHabitId, @org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> availableHabitFilters, @org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint> chartData, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary analyticsSummary, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState screenState) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod getSelectedPeriod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate getPeriodAnchorDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate getPeriodStartDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate getPeriodEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSelectedHabitId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> getAvailableHabitFilters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint> getChartData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary getAnalyticsSummary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState getScreenState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.feature.activity.contract.ActivityUiState copy(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod selectedPeriod, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate periodAnchorDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate periodStartDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate periodEndDate, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedHabitId, @org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> availableHabitFilters, @org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint> chartData, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary analyticsSummary, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState screenState) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t\u00a8\u0006\f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/contract/ActivityUiState$Companion;", "", "<init>", "()V", "initial", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityUiState;", "selectedPeriod", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "periodAnchorDate", "Ljava/time/LocalDate;", "periodStartDate", "periodEndDate", "activity_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.threemdroid.habittracker.feature.activity.contract.ActivityUiState initial(@org.jetbrains.annotations.NotNull()
        com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod selectedPeriod, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodAnchorDate, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodStartDate, @org.jetbrains.annotations.NotNull()
        java.time.LocalDate periodEndDate) {
            return null;
        }
    }
}