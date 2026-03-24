package com.threemdroid.habittracker.feature.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.core.ui.PlaceholderScreen

object SettingsDestination : AppDestination {
    override val route: String = "settings"
}

fun NavGraphBuilder.settingsScreen() {
    composable(route = SettingsDestination.route) {
        PlaceholderScreen(title = "Settings")
    }
}
