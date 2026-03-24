package com.threemdroid.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.threemdroid.habittracker.core.designsystem.HabitTrackerTheme
import com.threemdroid.habittracker.domain.activity.ActivityRepository
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import com.threemdroid.habittracker.feature.create_habit.createHabitViewModelFactory
import com.threemdroid.habittracker.feature.home.homeViewModelFactory
import com.threemdroid.habittracker.navigation.HabitTrackerApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var habitsRepository: HabitsRepository

    @Inject
    lateinit var activityRepository: ActivityRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val homeFactory = homeViewModelFactory(
            habitsRepository = habitsRepository,
            activityRepository = activityRepository,
        )
        val createHabitFactory = createHabitViewModelFactory(
            habitsRepository = habitsRepository,
        )
        setContent {
            HabitTrackerTheme {
                HabitTrackerApp(
                    homeViewModelFactory = homeFactory,
                    createHabitViewModelFactory = createHabitFactory,
                )
            }
        }
    }
}
