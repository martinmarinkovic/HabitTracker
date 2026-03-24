package com.threemdroid.habittracker.core.database;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&\u00a8\u0006\u000e"}, d2 = {"Lcom/threemdroid/habittracker/core/database/HabitTrackerDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "habitDao", "Lcom/threemdroid/habittracker/core/database/dao/HabitDao;", "habitEntryDao", "Lcom/threemdroid/habittracker/core/database/dao/HabitEntryDao;", "moodEntryDao", "Lcom/threemdroid/habittracker/core/database/dao/MoodEntryDao;", "reminderDao", "Lcom/threemdroid/habittracker/core/database/dao/ReminderDao;", "settingsDao", "Lcom/threemdroid/habittracker/core/database/dao/SettingsDao;", "database_debug"})
@androidx.room.Database(entities = {com.threemdroid.habittracker.core.database.model.HabitEntity.class, com.threemdroid.habittracker.core.database.model.HabitEntryEntity.class, com.threemdroid.habittracker.core.database.model.MoodEntryEntity.class, com.threemdroid.habittracker.core.database.model.ReminderEntity.class, com.threemdroid.habittracker.core.database.model.SettingsEntity.class}, version = 1, exportSchema = true)
@androidx.room.TypeConverters(value = {com.threemdroid.habittracker.core.database.DatabaseConverters.class})
public abstract class HabitTrackerDatabase extends androidx.room.RoomDatabase {
    
    public HabitTrackerDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.threemdroid.habittracker.core.database.dao.HabitDao habitDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.threemdroid.habittracker.core.database.dao.HabitEntryDao habitEntryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.threemdroid.habittracker.core.database.dao.MoodEntryDao moodEntryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.threemdroid.habittracker.core.database.dao.ReminderDao reminderDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.threemdroid.habittracker.core.database.dao.SettingsDao settingsDao();
}