package com.threemdroid.habittracker.core.database.model;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u000bH\u00c6\u0003JA\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0015\u00a8\u0006!"}, d2 = {"Lcom/threemdroid/habittracker/core/database/model/ReminderEntity;", "", "reminderId", "", "habitId", "time", "Ljava/time/LocalTime;", "selectedDays", "", "Ljava/time/DayOfWeek;", "isEnabled", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/time/LocalTime;Ljava/util/Set;Z)V", "getReminderId", "()Ljava/lang/String;", "getHabitId", "getTime", "()Ljava/time/LocalTime;", "getSelectedDays", "()Ljava/util/Set;", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "database_debug"})
@androidx.room.Entity(tableName = "reminders", foreignKeys = {@androidx.room.ForeignKey(entity = com.threemdroid.habittracker.core.database.model.HabitEntity.class, parentColumns = {"habit_id"}, childColumns = {"habit_id"}, onDelete = 5)}, indices = {@androidx.room.Index(value = {"habit_id"}), @androidx.room.Index(value = {"habit_id", "is_enabled"})})
public final class ReminderEntity {
    @androidx.room.PrimaryKey()
    @androidx.room.ColumnInfo(name = "reminder_id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String reminderId = null;
    @androidx.room.ColumnInfo(name = "habit_id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String habitId = null;
    @androidx.room.ColumnInfo(name = "time_second_of_day")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalTime time = null;
    @androidx.room.ColumnInfo(name = "selected_days")
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.time.DayOfWeek> selectedDays = null;
    @androidx.room.ColumnInfo(name = "is_enabled")
    private final boolean isEnabled = false;
    
    public ReminderEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String reminderId, @org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.time.LocalTime time, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends java.time.DayOfWeek> selectedDays, boolean isEnabled) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReminderId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHabitId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalTime getTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.time.DayOfWeek> getSelectedDays() {
        return null;
    }
    
    public final boolean isEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalTime component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.time.DayOfWeek> component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.database.model.ReminderEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String reminderId, @org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.time.LocalTime time, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends java.time.DayOfWeek> selectedDays, boolean isEnabled) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}