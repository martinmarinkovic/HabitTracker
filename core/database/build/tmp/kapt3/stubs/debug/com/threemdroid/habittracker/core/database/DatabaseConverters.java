package com.threemdroid.habittracker.core.database;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\fH\u0007\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0006\u001a\u0004\u0018\u00010\u0012H\u0007\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0006\u001a\u0004\u0018\u00010\u0011H\u0007\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0006\u001a\u0004\u0018\u00010\u0018H\u0007J\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0006\u001a\u0004\u0018\u00010\u0017H\u0007J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u00172\b\u0010\u0006\u001a\u0004\u0018\u00010\u001bH\u0007J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0017H\u0007J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00172\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eH\u0007J\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0017H\u0007\u00a8\u0006!"}, d2 = {"Lcom/threemdroid/habittracker/core/database/DatabaseConverters;", "", "<init>", "()V", "instantToEpochMillis", "", "value", "Ljava/time/Instant;", "(Ljava/time/Instant;)Ljava/lang/Long;", "epochMillisToInstant", "(Ljava/lang/Long;)Ljava/time/Instant;", "localDateToEpochDay", "Ljava/time/LocalDate;", "(Ljava/time/LocalDate;)Ljava/lang/Long;", "epochDayToLocalDate", "(Ljava/lang/Long;)Ljava/time/LocalDate;", "localTimeToSecondOfDay", "", "Ljava/time/LocalTime;", "(Ljava/time/LocalTime;)Ljava/lang/Integer;", "secondOfDayToLocalTime", "(Ljava/lang/Integer;)Ljava/time/LocalTime;", "habitTypeToString", "", "Lcom/threemdroid/habittracker/core/model/habits/HabitType;", "stringToHabitType", "habitStateToString", "Lcom/threemdroid/habittracker/core/model/habits/HabitState;", "stringToHabitState", "dayOfWeekSetToString", "", "Ljava/time/DayOfWeek;", "stringToDayOfWeekSet", "database_debug"})
public final class DatabaseConverters {
    
    public DatabaseConverters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long instantToEpochMillis(@org.jetbrains.annotations.Nullable()
    java.time.Instant value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.time.Instant epochMillisToInstant(@org.jetbrains.annotations.Nullable()
    java.lang.Long value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long localDateToEpochDay(@org.jetbrains.annotations.Nullable()
    java.time.LocalDate value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate epochDayToLocalDate(@org.jetbrains.annotations.Nullable()
    java.lang.Long value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer localTimeToSecondOfDay(@org.jetbrains.annotations.Nullable()
    java.time.LocalTime value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalTime secondOfDayToLocalTime(@org.jetbrains.annotations.Nullable()
    java.lang.Integer value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String habitTypeToString(@org.jetbrains.annotations.Nullable()
    com.threemdroid.habittracker.core.model.habits.HabitType value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final com.threemdroid.habittracker.core.model.habits.HabitType stringToHabitType(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String habitStateToString(@org.jetbrains.annotations.Nullable()
    com.threemdroid.habittracker.core.model.habits.HabitState value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final com.threemdroid.habittracker.core.model.habits.HabitState stringToHabitState(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String dayOfWeekSetToString(@org.jetbrains.annotations.Nullable()
    java.util.Set<? extends java.time.DayOfWeek> value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.time.DayOfWeek> stringToDayOfWeekSet(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
}