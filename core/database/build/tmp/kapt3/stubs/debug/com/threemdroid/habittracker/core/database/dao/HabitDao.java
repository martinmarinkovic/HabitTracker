package com.threemdroid.habittracker.core.database.dao;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00032\u0006\u0010\u0007\u001a\u00020\bH\'J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\'J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\'J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\'\u00a8\u0006\u0014\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/core/database/dao/HabitDao;", "", "observeHabitsWithReminders", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/threemdroid/habittracker/core/database/relation/HabitWithReminders;", "observeHabitWithReminders", "habitId", "", "upsertHabit", "", "habit", "Lcom/threemdroid/habittracker/core/database/model/HabitEntity;", "(Lcom/threemdroid/habittracker/core/database/model/HabitEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteHabit", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeTotalHabitCount", "", "observeActiveHabitCount", "observeStoppedHabitCount", "database_debug"})
@androidx.room.Dao()
public abstract interface HabitDao {
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM habits ORDER BY created_at_epoch_millis DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.threemdroid.habittracker.core.database.relation.HabitWithReminders>> observeHabitsWithReminders();
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM habits WHERE habit_id = :habitId LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.database.relation.HabitWithReminders> observeHabitWithReminders(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertHabit(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.database.model.HabitEntity habit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM habits WHERE habit_id = :habitId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM habits")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> observeTotalHabitCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM habits WHERE state = \'ACTIVE\'")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> observeActiveHabitCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM habits WHERE state = \'STOPPED\'")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> observeStoppedHabitCount();
}