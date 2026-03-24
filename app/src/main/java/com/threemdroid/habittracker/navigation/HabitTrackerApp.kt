package com.threemdroid.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
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
fun HabitTrackerApp(
    homeViewModelFactory: ViewModelProvider.Factory,
    createHabitViewModelFactory: ViewModelProvider.Factory,
) {
    val navController = rememberNavController()
    val uriHandler = LocalUriHandler.current

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        homeScreen(
            viewModelFactory = homeViewModelFactory,
            onCreateHabitRequested = {
                navController.navigate(CreateHabitDestination.route)
            },
            onNavigateToActivity = {
                navController.navigatePrimaryRoute(ActivityDestination.route)
            },
            onNavigateToLearn = {
                navController.navigatePrimaryRoute(LearnDestination.route)
            },
            onNavigateToProfile = {
                navController.navigatePrimaryRoute(ProfileDestination.route)
            },
        )
        createHabitScreen(
            viewModelFactory = createHabitViewModelFactory,
            onBackRequested = {
                navController.popBackStack()
            },
            onHabitCreated = {
                navController.popBackStack()
            },
        )
        activityScreen()
        learnScreen(
            onVideoRequested = uriHandler::openUri,
        )
        profileScreen()
        settingsScreen()
    }
}

private fun NavHostController.navigatePrimaryRoute(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
    }
}
