package com.threemdroid.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.threemdroid.habittracker.feature.activity.ActivityDestination
import com.threemdroid.habittracker.feature.activity.activityScreen
import com.threemdroid.habittracker.feature.create_habit.CreateHabitDestination
import com.threemdroid.habittracker.feature.create_habit.createHabitScreen
import com.threemdroid.habittracker.feature.home.HomeDestination
import com.threemdroid.habittracker.feature.home.homeScreen
import com.threemdroid.habittracker.feature.learn.LearnDestination
import com.threemdroid.habittracker.feature.learn.learnScreen
import com.threemdroid.habittracker.feature.profile.ProfileDestination
import com.threemdroid.habittracker.feature.profile.profileScreen
import com.threemdroid.habittracker.feature.settings.SettingsDestination
import com.threemdroid.habittracker.feature.settings.settingsScreen

@Composable
fun HabitTrackerApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        homeScreen()
        createHabitScreen()
        activityScreen()
        learnScreen()
        profileScreen()
        settingsScreen()
    }
}
