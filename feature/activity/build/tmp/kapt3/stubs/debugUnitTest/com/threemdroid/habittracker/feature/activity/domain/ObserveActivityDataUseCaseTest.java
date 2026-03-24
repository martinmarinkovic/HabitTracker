package com.threemdroid.habittracker.feature.activity.domain;

import com.threemdroid.habittracker.core.common.result.AppError;
import com.threemdroid.habittracker.core.common.result.AppResult;
import com.threemdroid.habittracker.core.model.habits.HabitType;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import com.threemdroid.habittracker.feature.activity.testutil.TestActivityHabitsRepository;
import java.time.DayOfWeek;
import org.junit.Test;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0007J\f\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0007J\f\u0010\b\u001a\u00060\u0005j\u0002`\u0006H\u0007J\f\u0010\t\u001a\u00060\u0005j\u0002`\u0006H\u0007J\f\u0010\n\u001a\u00060\u0005j\u0002`\u0006H\u0007J\f\u0010\u000b\u001a\u00060\u0005j\u0002`\u0006H\u0007J\u0012\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\r0\u000eH\u0002\u00a8\u0006\u000f"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/domain/ObserveActivityDataUseCaseTest;", "", "<init>", "()V", "invoke_forAllHabits_buildsWeeklyChartReadyAnalytics", "", "Lkotlinx/coroutines/test/TestResult;", "invoke_emptySelectedDays_treatsHabitAsScheduledEveryDay", "invoke_createdAtHistoricalGating_limitsScheduledDaysBeforeHabitExists", "invoke_stoppedAtHistoricalGating_limitsScheduledDaysAfterHabitStops", "invoke_forSelectedHabit_filtersAnalyticsToThatHabit", "invoke_whenHabitEntriesFail_returnsFailure", "assertSuccess", "Lcom/threemdroid/habittracker/feature/activity/domain/ActivitySnapshot;", "Lcom/threemdroid/habittracker/core/common/result/AppResult;", "activity_debugUnitTest"})
public final class ObserveActivityDataUseCaseTest {
    
    public ObserveActivityDataUseCaseTest() {
        super();
    }
    
    @org.junit.Test()
    public final void invoke_forAllHabits_buildsWeeklyChartReadyAnalytics() {
    }
    
    @org.junit.Test()
    public final void invoke_emptySelectedDays_treatsHabitAsScheduledEveryDay() {
    }
    
    @org.junit.Test()
    public final void invoke_createdAtHistoricalGating_limitsScheduledDaysBeforeHabitExists() {
    }
    
    @org.junit.Test()
    public final void invoke_stoppedAtHistoricalGating_limitsScheduledDaysAfterHabitStops() {
    }
    
    @org.junit.Test()
    public final void invoke_forSelectedHabit_filtersAnalyticsToThatHabit() {
    }
    
    @org.junit.Test()
    public final void invoke_whenHabitEntriesFail_returnsFailure() {
    }
    
    private final com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot assertSuccess(com.threemdroid.habittracker.core.common.result.AppResult<com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot> $this$assertSuccess) {
        return null;
    }
}