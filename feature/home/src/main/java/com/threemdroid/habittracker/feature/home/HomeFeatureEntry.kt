package com.threemdroid.habittracker.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.core.ui.PlaceholderScreen

object HomeDestination : AppDestination {
    override val route: String = "home"
}

fun NavGraphBuilder.homeScreen() {
    composable(route = HomeDestination.route) {
        PlaceholderScreen(title = "Home")
    }
}
