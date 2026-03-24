package com.threemdroid.habittracker.core.database.model;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0006H\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003J\t\u00101\u001a\u00020\bH\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\fH\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003J\t\u00107\u001a\u00020\u0013H\u00c6\u0003J\t\u00108\u001a\u00020\u0015H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0015H\u00c6\u0003J\u0095\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u00c6\u0001J\u0013\u0010;\u001a\u00020\f2\b\u0010<\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010=\u001a\u00020>H\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0016\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0016\u0010\u0014\u001a\u00020\u00158\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010+\u00a8\u0006@"}, d2 = {"Lcom/threemdroid/habittracker/core/database/model/HabitEntity;", "", "habitId", "", "name", "habitType", "Lcom/threemdroid/habittracker/core/model/habits/HabitType;", "targetValue", "", "defaultIncrement", "unitLabel", "allowsMultipleUpdatesPerDay", "", "selectedIconToken", "selectedColorToken", "selectedDays", "", "Ljava/time/DayOfWeek;", "state", "Lcom/threemdroid/habittracker/core/model/habits/HabitState;", "createdAt", "Ljava/time/Instant;", "stoppedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/threemdroid/habittracker/core/model/habits/HabitType;DDLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/Set;Lcom/threemdroid/habittracker/core/model/habits/HabitState;Ljava/time/Instant;Ljava/time/Instant;)V", "getHabitId", "()Ljava/lang/String;", "getName", "getHabitType", "()Lcom/threemdroid/habittracker/core/model/habits/HabitType;", "getTargetValue", "()D", "getDefaultIncrement", "getUnitLabel", "getAllowsMultipleUpdatesPerDay", "()Z", "getSelectedIconToken", "getSelectedColorToken", "getSelectedDays", "()Ljava/util/Set;", "getState", "()Lcom/threemdroid/habittracker/core/model/habits/HabitState;", "getCreatedAt", "()Ljava/time/Instant;", "getStoppedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "", "toString", "database_debug"})
@androidx.room.Entity(tableName = "habits", indices = {@androidx.room.Index(value = {"state"}), @androidx.room.Index(value = {"created_at_epoch_millis"})})
public final class HabitEntity {
    @androidx.room.PrimaryKey()
    @androidx.room.ColumnInfo(name = "habit_id")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String habitId = null;
    @androidx.room.ColumnInfo(name = "name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @androidx.room.ColumnInfo(name = "habit_type")
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.core.model.habits.HabitType habitType = null;
    @androidx.room.ColumnInfo(name = "target_value")
    private final double targetValue = 0.0;
    @androidx.room.ColumnInfo(name = "default_increment")
    private final double defaultIncrement = 0.0;
    @androidx.room.ColumnInfo(name = "unit_label")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String unitLabel = null;
    @androidx.room.ColumnInfo(name = "allows_multiple_updates_per_day")
    private final boolean allowsMultipleUpdatesPerDay = false;
    @androidx.room.ColumnInfo(name = "selected_icon_token")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String selectedIconToken = null;
    @androidx.room.ColumnInfo(name = "selected_color_token")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String selectedColorToken = null;
    @androidx.room.ColumnInfo(name = "selected_days")
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.time.DayOfWeek> selectedDays = null;
    @androidx.room.ColumnInfo(name = "state")
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.core.model.habits.HabitState state = null;
    @androidx.room.ColumnInfo(name = "created_at_epoch_millis")
    @org.jetbrains.annotations.NotNull()
    private final java.time.Instant createdAt = null;
    @androidx.room.ColumnInfo(name = "stopped_at_epoch_millis")
    @org.jetbrains.annotations.Nullable()
    private final java.time.Instant stoppedAt = null;
    
    public HabitEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.HabitType habitType, double targetValue, double defaultIncrement, @org.jetbrains.annotations.Nullable()
    java.lang.String unitLabel, boolean allowsMultipleUpdatesPerDay, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedIconToken, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedColorToken, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends java.time.DayOfWeek> selectedDays, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.HabitState state, @org.jetbrains.annotations.NotNull()
    java.time.Instant createdAt, @org.jetbrains.annotations.Nullable()
    java.time.Instant stoppedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHabitId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.model.habits.HabitType getHabitType() {
        return null;
    }
    
    public final double getTargetValue() {
        return 0.0;
    }
    
    public final double getDefaultIncrement() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUnitLabel() {
        return null;
    }
    
    public final boolean getAllowsMultipleUpdatesPerDay() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedIconToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedColorToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.time.DayOfWeek> getSelectedDays() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.model.habits.HabitState getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.Instant getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.Instant getStoppedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.time.DayOfWeek> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.model.habits.HabitState component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.Instant component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.Instant component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.model.habits.HabitType component3() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.threemdroid.habittracker.core.database.model.HabitEntity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String habitId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.HabitType habitType, double targetValue, double defaultIncrement, @org.jetbrains.annotations.Nullable()
    java.lang.String unitLabel, boolean allowsMultipleUpdatesPerDay, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedIconToken, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedColorToken, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends java.time.DayOfWeek> selectedDays, @org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.core.model.habits.HabitState state, @org.jetbrains.annotations.NotNull()
    java.time.Instant createdAt, @org.jetbrains.annotations.Nullable()
    java.time.Instant stoppedAt) {
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