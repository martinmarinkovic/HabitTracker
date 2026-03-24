package com.threemdroid.habittracker.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.threemdroid.habittracker.core.database.dao.HabitDao
import com.threemdroid.habittracker.core.database.dao.HabitEntryDao
import com.threemdroid.habittracker.core.database.dao.MoodEntryDao
import com.threemdroid.habittracker.core.database.dao.ReminderDao
import com.threemdroid.habittracker.core.database.dao.SettingsDao
import com.threemdroid.habittracker.core.database.model.HabitEntity
import com.threemdroid.habittracker.core.database.model.HabitEntryEntity
import com.threemdroid.habittracker.core.database.model.MoodEntryEntity
import com.threemdroid.habittracker.core.database.model.ReminderEntity
import com.threemdroid.habittracker.core.database.model.SettingsEntity

@Database(
    entities = [
        HabitEntity::class,
        HabitEntryEntity::class,
        MoodEntryEntity::class,
        ReminderEntity::class,
        SettingsEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(DatabaseConverters::class)
abstract class HabitTrackerDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    abstract fun habitEntryDao(): HabitEntryDao

    abstract fun moodEntryDao(): MoodEntryDao

    abstract fun reminderDao(): ReminderDao

    abstract fun settingsDao(): SettingsDao
}
