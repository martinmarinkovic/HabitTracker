package com.threemdroid.habittracker.core.database.model;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lcom/threemdroid/habittracker/core/database/model/HabitEntryEntity;", "", "entryId", "", "habitId", "entryDate", "Ljava/time/LocalDate;", "loggedAt", "Ljava/time/Instant;", "value", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/time/LocalDate;Ljava/time/Instant;D)V", "getEntryId", "()Ljava/lang/String;", "getHabitId", "getEntryDate", "()Ljava/time/LocalDate;", "getLoggedAt", "()Ljava/time/Instant;", "getValue", "()D", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "database_debug"})
@androidx.room.Entity(tableName = "habit_entries", foreignKeys = {@androidx.room.ForeignKey(entity = com.threemdroid.habittracker.core.database.model.HabitEntity.class, parentColumns = {"habit_id"}, childColumns = {"habit_id"}, onDelete = 5)}, indices = {@androidx.room.Index(value = {"habit_id"}), @androidx.room.Index(value = {"habit_id", "entry_date_epoch_day"}), @androidx.room.Index(value = {"logged_at_epoch_millis"})})
public final class HabitEntryEntity {
    @androidx.room.PrimaryKey()
    @androidx.room.ColumnInfo(name = "entry_id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String entryId = null;
    @androidx.room.ColumnInfo(name = "habit_id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String habitId = null;
    @androidx.room.ColumnInfo(name = "entry_date_epoch_day")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDate entryDate = null;
    @androidx.room.ColumnInfo(name = "logged_at_epoch_millis")
    @org.jetbrains.annotations.NotNull()
    private final java.time.Instant loggedAt = null;
    @androidx.room.ColumnInfo(name = "value")
    private final double value = 0.0;
    
    public HabitEntryEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String entryId, @org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate entryDate, @org.jetbrains.annotations.NotNull()
    java.time.Instant loggedAt, double value) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEntryId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHabitId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate getEntryDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.Instant getLoggedAt() {
        return null;
    }
    
    public final double getValue() {
        return 0.0;
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
    public final java.time.LocalDate component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.Instant component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.database.model.HabitEntryEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String entryId, @org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate entryDate, @org.jetbrains.annotations.NotNull()
    java.time.Instant loggedAt, double value) {
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