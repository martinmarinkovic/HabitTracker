package com.threemdroid.habittracker.core.database.dao;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0003H\'\u00a8\u0006\u0011\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/core/database/dao/ReminderDao;", "", "observeRemindersForHabit", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/threemdroid/habittracker/core/database/model/ReminderEntity;", "habitId", "", "upsertReminder", "", "reminder", "(Lcom/threemdroid/habittracker/core/database/model/ReminderEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "reminderId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeEnabledReminderCount", "", "database_debug"})
@androidx.room.Dao()
public abstract interface ReminderDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM reminders\n        WHERE habit_id = :habitId\n        ORDER BY time_second_of_day ASC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.threemdroid.habittracker.core.database.model.ReminderEntity>> observeRemindersForHabit(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertReminder(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.database.model.ReminderEntity reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM reminders WHERE reminder_id = :reminderId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull()
    java.lang.String reminderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM reminders WHERE is_enabled = 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> observeEnabledReminderCount();
}