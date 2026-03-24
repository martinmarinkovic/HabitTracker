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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J/\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086\u0002JD\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002Jb\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/domain/ObserveActivityDataUseCase;", "", "habitsRepository", "Lcom/threemdroid/habittracker/domain/habits/HabitsRepository;", "clock", "Ljava/time/Clock;", "<init>", "(Lcom/threemdroid/habittracker/domain/habits/HabitsRepository;Ljava/time/Clock;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/threemdroid/habittracker/core/common/result/AppResult;", "Lcom/threemdroid/habittracker/feature/activity/domain/ActivitySnapshot;", "period", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "anchorDate", "Ljava/time/LocalDate;", "selectedHabitId", "", "observeEntriesForSelection", "habits", "", "Lcom/threemdroid/habittracker/core/model/habits/Habit;", "periodWindow", "Lcom/threemdroid/habittracker/feature/activity/domain/ActivityPeriodWindow;", "buildSnapshot", "filteredHabits", "filteredEntries", "Lcom/threemdroid/habittracker/core/model/habits/HabitEntry;", "availableHabitFilters", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityHabitFilterOption;", "activity_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class ObserveActivityDataUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.domain.habits.HabitsRepository habitsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.Clock clock = null;
    
    @javax.inject.Inject()
    public ObserveActivityDataUseCase(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.domain.habits.HabitsRepository habitsRepository, @org.jetbrains.annotations.NotNull()
    java.time.Clock clock) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot>> invoke(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate anchorDate, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedHabitId) {
        return null;
    }
    
    private final kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot>> observeEntriesForSelection(java.util.List<com.threemdroid.habittracker.core.model.habits.Habit> habits, com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period, java.time.LocalDate anchorDate, com.threemdroid.habittracker.feature.activity.domain.ActivityPeriodWindow periodWindow, java.lang.String selectedHabitId) {
        return null;
    }
    
    private final com.threemdroid.habittracker.feature.activity.domain.ActivitySnapshot buildSnapshot(com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period, java.time.LocalDate anchorDate, com.threemdroid.habittracker.feature.activity.domain.ActivityPeriodWindow periodWindow, java.util.List<com.threemdroid.habittracker.core.model.habits.Habit> habits, java.util.List<com.threemdroid.habittracker.core.model.habits.Habit> filteredHabits, java.util.List<com.threemdroid.habittracker.core.model.habits.HabitEntry> filteredEntries, java.lang.String selectedHabitId, java.util.List<com.threemdroid.habittracker.feature.activity.contract.ActivityHabitFilterOption> availableHabitFilters) {
        return null;
    }
}