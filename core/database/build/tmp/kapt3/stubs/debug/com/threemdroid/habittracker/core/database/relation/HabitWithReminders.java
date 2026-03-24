package com.threemdroid.habittracker.core.database.relation;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lcom/threemdroid/habittracker/core/database/relation/HabitWithReminders;", "", "habit", "Lcom/threemdroid/habittracker/core/database/model/HabitEntity;", "reminders", "", "Lcom/threemdroid/habittracker/core/database/model/ReminderEntity;", "<init>", "(Lcom/threemdroid/habittracker/core/database/model/HabitEntity;Ljava/util/List;)V", "getHabit", "()Lcom/threemdroid/habittracker/core/database/model/HabitEntity;", "getReminders", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "database_debug"})
public final class HabitWithReminders {
    @androidx.room.Embedded()
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.core.database.model.HabitEntity habit = null;
    @androidx.room.Relation(parentColumn = "habit_id", entityColumn = "habit_id")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.threemdroid.habittracker.core.database.model.ReminderEntity> reminders = null;
    
    public HabitWithReminders(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.database.model.HabitEntity habit, @org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.core.database.model.ReminderEntity> reminders) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.database.model.HabitEntity getHabit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.threemdroid.habittracker.core.database.model.ReminderEntity> getReminders() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.database.model.HabitEntity component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.threemdroid.habittracker.core.database.model.ReminderEntity> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.database.relation.HabitWithReminders copy(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.database.model.HabitEntity habit, @org.jetbrains.annotations.NotNull()
    java.util.List<com.threemdroid.habittracker.core.database.model.ReminderEntity> reminders) {
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