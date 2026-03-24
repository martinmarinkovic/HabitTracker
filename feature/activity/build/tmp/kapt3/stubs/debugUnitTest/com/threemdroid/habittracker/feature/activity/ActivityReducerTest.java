package com.threemdroid.habittracker.feature.activity;

import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary;
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint;
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState;
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState;
import com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot;
import com.threemdroid.habittracker.feature.activity.presentation.ActivityReducer;
import com.threemdroid.habittracker.feature.activity.presentation.ActivityResult;
import org.junit.Test;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\b\u001a\u00020\u0007H\u0007J\b\u0010\t\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/ActivityReducerTest;", "", "<init>", "()V", "initialWindow", "Lcom/threemdroid/habittracker/feature/activity/domain/ActivityPeriodWindow;", "reduce_configurationChanged_setsLoadingAndClearsAnalytics", "", "reduce_activityDataLoaded_withoutHabits_setsEmptyState", "reduce_activityDataLoadFailed_setsErrorState", "activity_debugUnitTest"})
public final class ActivityReducerTest {
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.activity.domain.ActivityPeriodWindow initialWindow = null;
    
    public ActivityReducerTest() {
        super();
    }
    
    @org.junit.Test()
    public final void reduce_configurationChanged_setsLoadingAndClearsAnalytics() {
    }
    
    @org.junit.Test()
    public final void reduce_activityDataLoaded_withoutHabits_setsEmptyState() {
    }
    
    @org.junit.Test()
    public final void reduce_activityDataLoadFailed_setsErrorState() {
    }
}