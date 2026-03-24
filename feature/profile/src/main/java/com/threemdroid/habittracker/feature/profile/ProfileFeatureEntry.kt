package com.threemdroid.habittracker.feature.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.core.ui.PlaceholderScreen

object ProfileDestination : AppDestination {
    override val route: String = "profile"
}

fun NavGraphBuilder.profileScreen() {
    composable(route = ProfileDestination.route) {
        PlaceholderScreen(title = "Profile")
    }
}
