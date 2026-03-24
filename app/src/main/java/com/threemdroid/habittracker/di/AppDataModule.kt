package com.threemdroid.habittracker.di

import android.content.Context
import androidx.room.Room
import com.threemdroid.habittracker.core.database.HabitTrackerDatabase
import com.threemdroid.habittracker.data.activity.repository.ActivityRepositoryImpl
import com.threemdroid.habittracker.data.habits.repository.HabitsRepositoryImpl
import com.threemdroid.habittracker.domain.activity.ActivityRepository
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val HABIT_TRACKER_DATABASE_NAME = "habit_tracker.db"

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {
    @Provides
    @Singleton
    fun provideHabitTrackerDatabase(
        @ApplicationContext context: Context,
    ): HabitTrackerDatabase = Room.databaseBuilder(
        context,
        HabitTrackerDatabase::class.java,
        HABIT_TRACKER_DATABASE_NAME,
    ).build()

    @Provides
    @Singleton
    fun provideHabitsRepository(
        database: HabitTrackerDatabase,
    ): HabitsRepository = HabitsRepositoryImpl(
        habitDao = database.habitDao(),
        habitEntryDao = database.habitEntryDao(),
        reminderDao = database.reminderDao(),
    )

    @Provides
    @Singleton
    fun provideActivityRepository(
        database: HabitTrackerDatabase,
    ): ActivityRepository = ActivityRepositoryImpl(
        moodEntryDao = database.moodEntryDao(),
    )
}
