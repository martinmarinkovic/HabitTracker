package com.threemdroid.habittracker.feature.activity;

import com.threemdroid.habittracker.core.common.result.AppError;
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState;
import com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase;
import com.threemdroid.habittracker.feature.activity.presentation.ActivityViewModel;
import com.threemdroid.habittracker.feature.activity.testutil.TestActivityHabitsRepository;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\b\u001a\u00020\u0007H\u0007J\f\u0010\t\u001a\u00060\u0007j\u0002`\nH\u0007J\f\u0010\u000b\u001a\u00060\u0007j\u0002`\nH\u0007J\f\u0010\f\u001a\u00060\u0007j\u0002`\nH\u0007J\f\u0010\r\u001a\u00060\u0007j\u0002`\nH\u0007J\f\u0010\u000e\u001a\u00060\u0007j\u0002`\nH\u0007J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/ActivityViewModelTest;", "", "<init>", "()V", "dispatcher", "Lkotlinx/coroutines/test/TestDispatcher;", "setUp", "", "tearDown", "init_loadsWeeklyActivityContent", "Lkotlinx/coroutines/test/TestResult;", "handleIntent_periodSelected_updatesWindowAndReloadsData", "handleIntent_habitFilterSelected_updatesSelectedHabitAnalytics", "handleIntent_previousAndNextPeriod_shiftAnchorDate", "handleIntent_whenLoadFails_setsErrorState", "activityViewModel", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityViewModel;", "repository", "Lcom/threemdroid/habittracker/feature/activity/testutil/TestActivityHabitsRepository;", "observeActivityDataUseCase", "Lcom/threemdroid/habittracker/feature/activity/domain/ObserveActivityDataUseCase;", "activity_debugUnitTest"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class ActivityViewModelTest {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.test.TestDispatcher dispatcher = null;
    
    public ActivityViewModelTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setUp() {
    }
    
    @org.junit.After()
    public final void tearDown() {
    }
    
    @org.junit.Test()
    public final void init_loadsWeeklyActivityContent() {
    }
    
    @org.junit.Test()
    public final void handleIntent_periodSelected_updatesWindowAndReloadsData() {
    }
    
    @org.junit.Test()
    public final void handleIntent_habitFilterSelected_updatesSelectedHabitAnalytics() {
    }
    
    @org.junit.Test()
    public final void handleIntent_previousAndNextPeriod_shiftAnchorDate() {
    }
    
    @org.junit.Test()
    public final void handleIntent_whenLoadFails_setsErrorState() {
    }
    
    private final com.threemdroid.habittracker.feature.activity.presentation.ActivityViewModel activityViewModel(com.threemdroid.habittracker.feature.activity.testutil.TestActivityHabitsRepository repository, com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase observeActivityDataUseCase) {
        return null;
    }
}