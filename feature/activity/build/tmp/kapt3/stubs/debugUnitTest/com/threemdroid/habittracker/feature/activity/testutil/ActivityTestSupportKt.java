package com.threemdroid.habittracker.feature.activity.testutil;

import com.threemdroid.habittracker.core.common.result.AppError;
import com.threemdroid.habittracker.core.common.result.AppResult;
import com.threemdroid.habittracker.core.model.habits.Habit;
import com.threemdroid.habittracker.core.model.habits.HabitEntry;
import com.threemdroid.habittracker.core.model.habits.HabitSchedule;
import com.threemdroid.habittracker.core.model.habits.HabitState;
import com.threemdroid.habittracker.core.model.habits.HabitType;
import com.threemdroid.habittracker.core.model.habits.Reminder;
import com.threemdroid.habittracker.domain.habits.HabitsRepository;
import com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\f\u001a\u00020\r\u001a~\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0005\u001a0\u0010\"\u001a\u00020#2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u00162\b\b\u0002\u0010\'\u001a\u00020\u0005\u001a\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0000\"\u0011\u0010\u0000\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006,"}, d2 = {"ACTIVITY_TEST_DATE", "Ljava/time/LocalDate;", "getACTIVITY_TEST_DATE", "()Ljava/time/LocalDate;", "ACTIVITY_TEST_INSTANT", "Ljava/time/Instant;", "getACTIVITY_TEST_INSTANT", "()Ljava/time/Instant;", "ACTIVITY_TEST_ZONE", "Ljava/time/ZoneId;", "getACTIVITY_TEST_ZONE", "()Ljava/time/ZoneId;", "activityTestClock", "Ljava/time/Clock;", "activityTestHabit", "Lcom/threemdroid/habittracker/core/model/habits/Habit;", "id", "", "name", "type", "Lcom/threemdroid/habittracker/core/model/habits/HabitType;", "targetValue", "", "defaultIncrement", "unitLabel", "allowsMultipleUpdatesPerDay", "", "selectedDays", "", "Ljava/time/DayOfWeek;", "state", "Lcom/threemdroid/habittracker/core/model/habits/HabitState;", "createdAt", "stoppedAt", "activityTestEntry", "Lcom/threemdroid/habittracker/core/model/habits/HabitEntry;", "habitId", "entryDate", "value", "loggedAt", "activityObserveUseCase", "Lcom/threemdroid/habittracker/feature/activity/domain/ObserveActivityDataUseCase;", "repository", "Lcom/threemdroid/habittracker/feature/activity/testutil/TestActivityHabitsRepository;", "activity_debugUnitTest"})
public final class ActivityTestSupportKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.time.LocalDate ACTIVITY_TEST_DATE = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.time.Instant ACTIVITY_TEST_INSTANT = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.time.ZoneId ACTIVITY_TEST_ZONE = null;
    
    @org.jetbrains.annotations.NotNull()
    public static final java.time.LocalDate getACTIVITY_TEST_DATE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.time.Instant getACTIVITY_TEST_INSTANT() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.time.ZoneId getACTIVITY_TEST_ZONE() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.time.Clock activityTestClock() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.threemdroid.habittracker.core.model.habits.Habit activityTestHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.HabitType type, double targetValue, double defaultIncrement, @org.jetbrains.annotations.Nullable()
    java.lang.String unitLabel, boolean allowsMultipleUpdatesPerDay, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends java.time.DayOfWeek> selectedDays, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.HabitState state, @org.jetbrains.annotations.NotNull()
    java.time.Instant createdAt, @org.jetbrains.annotations.Nullable()
    java.time.Instant stoppedAt) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.threemdroid.habittracker.core.model.habits.HabitEntry activityTestEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate entryDate, double value, @org.jetbrains.annotations.NotNull()
    java.time.Instant loggedAt) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase activityObserveUseCase(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.testutil.TestActivityHabitsRepository repository) {
        return null;
    }
}