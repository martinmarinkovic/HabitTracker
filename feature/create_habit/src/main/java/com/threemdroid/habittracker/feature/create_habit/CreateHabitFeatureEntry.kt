package com.threemdroid.habittracker.feature.create_habit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.core.ui.PlaceholderScreen

object CreateHabitDestination : AppDestination {
    override val route: String = "create_habit"
}

fun NavGraphBuilder.createHabitScreen() {
    composable(route = CreateHabitDestination.route) {
        PlaceholderScreen(title = "Create Habit")
    }
}
