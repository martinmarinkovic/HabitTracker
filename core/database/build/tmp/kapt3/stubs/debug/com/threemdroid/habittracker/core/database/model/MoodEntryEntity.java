package com.threemdroid.habittracker.core.database.model;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lcom/threemdroid/habittracker/core/database/model/MoodEntryEntity;", "", "moodEntryId", "", "entryDate", "Ljava/time/LocalDate;", "loggedAt", "Ljava/time/Instant;", "moodToken", "<init>", "(Ljava/lang/String;Ljava/time/LocalDate;Ljava/time/Instant;Ljava/lang/String;)V", "getMoodEntryId", "()Ljava/lang/String;", "getEntryDate", "()Ljava/time/LocalDate;", "getLoggedAt", "()Ljava/time/Instant;", "getMoodToken", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "database_debug"})
@androidx.room.Entity(tableName = "mood_entries", indices = {@androidx.room.Index(value = {"entry_date_epoch_day"}), @androidx.room.Index(value = {"logged_at_epoch_millis"})})
public final class MoodEntryEntity {
    @androidx.room.PrimaryKey()
    @androidx.room.ColumnInfo(name = "mood_entry_id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String moodEntryId = null;
    @androidx.room.ColumnInfo(name = "entry_date_epoch_day")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDate entryDate = null;
    @androidx.room.ColumnInfo(name = "logged_at_epoch_millis")
    @org.jetbrains.annotations.NotNull()
    private final java.time.Instant loggedAt = null;
    @androidx.room.ColumnInfo(name = "mood_token")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String moodToken = null;
    
    public MoodEntryEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String moodEntryId, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate entryDate, @org.jetbrains.annotations.NotNull()
    java.time.Instant loggedAt, @org.jetbrains.annotations.NotNull()
    java.lang.String moodToken) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMoodEntryId() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMoodToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.Instant component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.database.model.MoodEntryEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String moodEntryId, @org.jetbrains.annotations.NotNull()
    java.time.LocalDate entryDate, @org.jetbrains.annotations.NotNull()
    java.time.Instant loggedAt, @org.jetbrains.annotations.NotNull()
    java.lang.String moodToken) {
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