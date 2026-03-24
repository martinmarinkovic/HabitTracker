package com.threemdroid.habittracker.feature.learn

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.feature.learn.presentation.LearnRoute

object LearnDestination : AppDestination {
    override val route: String = "learn"
}

fun NavGraphBuilder.learnScreen(
    onVideoRequested: (String) -> Unit = {},
) {
    composable(route = LearnDestination.route) {
        LearnRoute(onVideoRequested = onVideoRequested)
    }
}
