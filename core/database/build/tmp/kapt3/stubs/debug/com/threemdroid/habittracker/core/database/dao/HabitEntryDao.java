package com.threemdroid.habittracker.core.database.dao;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J,\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\'J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\f\u001a\u00020\tH\'J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\'\u00a8\u0006\u0016\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/core/database/dao/HabitEntryDao;", "", "observeEntriesForHabit", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/threemdroid/habittracker/core/database/model/HabitEntryEntity;", "habitId", "", "startDate", "Ljava/time/LocalDate;", "endDate", "observeEntriesForDate", "date", "upsertEntry", "", "habitEntry", "(Lcom/threemdroid/habittracker/core/database/model/HabitEntryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteEntry", "habitEntryId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeTotalEntryCount", "", "database_debug"})
@androidx.room.Dao()
public abstract interface HabitEntryDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM habit_entries\n        WHERE habit_id = :habitId\n        ORDER BY logged_at_epoch_millis DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.threemdroid.habittracker.core.database.model.HabitEntryEntity>> observeEntriesForHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId);
    
    @androidx.room.Query(value = "\n        SELECT * FROM habit_entries\n        WHERE habit_id = :habitId\n        AND entry_date_epoch_day BETWEEN :startDate AND :endDate\n        ORDER BY logged_at_epoch_millis DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.threemdroid.habittracker.core.database.model.HabitEntryEntity>> observeEntriesForHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate endDate);
    
    @androidx.room.Query(value = "\n        SELECT * FROM habit_entries\n        WHERE entry_date_epoch_day = :date\n        ORDER BY logged_at_epoch_millis DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.threemdroid.habittracker.core.database.model.HabitEntryEntity>> observeEntriesForDate(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate date);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertEntry(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.database.model.HabitEntryEntity habitEntry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM habit_entries WHERE entry_id = :habitEntryId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String habitEntryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM habit_entries")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> observeTotalEntryCount();
}