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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00060\u0010H\u0016J\u001e\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00060\u00102\u0006\u0010\u0012\u001a\u00020\rH\u0016J\"\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u00060\u00102\u0006\u0010\u0012\u001a\u00020\rH\u0016J\"\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u00060\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J2\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u00060\u00102\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00062\u0006\u0010\u001a\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u00062\u0006\u0010\u0012\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u00062\u0006\u0010\u001f\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010 J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00190\u00062\u0006\u0010\"\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190\u00062\u0006\u0010$\u001a\u00020%H\u0096@\u00a2\u0006\u0002\u0010&J\u001c\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00190\u00062\u0006\u0010(\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u0014\u0010)\u001a\u00020\u00192\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u000e\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u000eJ\u0014\u0010-\u001a\u00020\u00192\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\n0\u0007J\u000e\u0010/\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u000eJ\u0016\u00100\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010,\u001a\u00020\u000eJ\f\u00101\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R \u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/testutil/TestActivityHabitsRepository;", "Lcom/threemdroid/habittracker/domain/habits/HabitsRepository;", "<init>", "()V", "habitsState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/threemdroid/habittracker/core/common/result/AppResult;", "", "Lcom/threemdroid/habittracker/core/model/habits/Habit;", "entriesState", "Lcom/threemdroid/habittracker/core/model/habits/HabitEntry;", "rangeEntryErrors", "", "", "Lcom/threemdroid/habittracker/core/common/result/AppError;", "observeHabits", "Lkotlinx/coroutines/flow/Flow;", "observeHabit", "habitId", "observeHabitEntries", "date", "Ljava/time/LocalDate;", "startDate", "endDate", "upsertHabit", "", "habit", "(Lcom/threemdroid/habittracker/core/model/habits/Habit;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteHabit", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertHabitEntry", "habitEntry", "(Lcom/threemdroid/habittracker/core/model/habits/HabitEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteHabitEntry", "habitEntryId", "upsertReminder", "reminder", "Lcom/threemdroid/habittracker/core/model/habits/Reminder;", "(Lcom/threemdroid/habittracker/core/model/habits/Reminder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "reminderId", "setHabits", "habits", "setHabitsFailure", "error", "setEntries", "entries", "setEntriesFailure", "setRangeEntryError", "currentHabits", "activity_debugUnitTest"})
public final class TestActivityHabitsRepository implements com.threemdroid.habittracker.domain.habits.HabitsRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.core.model.habits.Habit>>> habitsState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.core.model.habits.HabitEntry>>> entriesState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.threemdroid.habittracker.core.common.result.AppError> rangeEntryErrors = null;
    
    public TestActivityHabitsRepository() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.core.model.habits.Habit>>> observeHabits() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<com.threemdroid.habittracker.core.model.habits.Habit>> observeHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.core.model.habits.HabitEntry>>> observeHabitEntries(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.core.model.habits.HabitEntry>>> observeHabitEntries(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.core.model.habits.HabitEntry>>> observeHabitEntries(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate endDate) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsertHabit(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.Habit habit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.threemdroid.habittracker.core.common.result.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.threemdroid.habittracker.core.common.result.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsertHabitEntry(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.HabitEntry habitEntry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.threemdroid.habittracker.core.common.result.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteHabitEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String habitEntryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.threemdroid.habittracker.core.common.result.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object upsertReminder(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.threemdroid.habittracker.core.common.result.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull()
    java.lang.String reminderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.threemdroid.habittracker.core.common.result.AppResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    public final void setHabits(@org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.core.model.habits.Habit> habits) {
    }
    
    public final void setHabitsFailure(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.common.result.AppError error) {
    }
    
    public final void setEntries(@org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.core.model.habits.HabitEntry> entries) {
    }
    
    public final void setEntriesFailure(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.common.result.AppError error) {
    }
    
    public final void setRangeEntryError(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.common.result.AppError error) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.threemdroid.habittracker.core.model.habits.Habit> currentHabits() {
        return null;
    }
}