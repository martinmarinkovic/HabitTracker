package com.threemdroid.habittracker.feature.activity.domain;

import com.threemdroid.habittracker.core.common.result.AppError;
import com.threemdroid.habittracker.core.common.result.AppResult;
import com.threemdroid.habittracker.core.model.habits.Habit;
import com.threemdroid.habittracker.core.model.habits.HabitEntry;
import com.threemdroid.habittracker.domain.habits.HabitsRepository;
import com.threemdroid.habittracker.feature.activity.contract.ActivityAnalyticsSummary;
import com.threemdroid.habittracker.feature.activity.contract.ActivityChartPoint;
import com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import java.time.Clock;
import java.time.LocalDate;
import javax.inject.Inject;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0001H\u0002\u00a8\u0006\u0004"}, d2 = {"toHabitFilterOptions", "", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityHabitFilterOption;", "Lcom/threemdroid/habittracker/core/model/habits/Habit;", "activity_debug"})
public final class ObserveActivityDataUseCaseKt {
    
    private static final java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> toHabitFilterOptions(java.util.List<com.threemdroid.habittracker.core.model.habits.Habit> $this$toHabitFilterOptions) {
        return null;
    }
}