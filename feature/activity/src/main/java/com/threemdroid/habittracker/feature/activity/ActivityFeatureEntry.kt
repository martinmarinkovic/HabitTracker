package com.threemdroid.habittracker.feature.activity

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.feature.activity.presentation.ActivityRoute

object ActivityDestination : AppDestination {
    override val route: String = "activity"
}

fun NavGraphBuilder.activityScreen() {
    composable(route = ActivityDestination.route) {
        ActivityRoute()
    }
}
