package com.threemdroid.habittracker.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.domain.activity.ActivityRepository
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import com.threemdroid.habittracker.feature.home.domain.ObserveHomeDataUseCase
import com.threemdroid.habittracker.feature.home.domain.RecordHomeHabitProgressUseCase
import com.threemdroid.habittracker.feature.home.domain.SaveHomeMoodSelectionUseCase
import com.threemdroid.habittracker.feature.home.domain.StopHabitFromHomeUseCase
import com.threemdroid.habittracker.feature.home.presentation.HomeRoute
import com.threemdroid.habittracker.feature.home.presentation.HomeViewModel
import java.time.Clock

object HomeDestination : AppDestination {
    override val route: String = "home"
}

fun homeViewModelFactory(
    habitsRepository: HabitsRepository,
    activityRepository: ActivityRepository,
    clock: Clock = Clock.systemDefaultZone(),
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(
        observeHomeDataUseCase = ObserveHomeDataUseCase(
            habitsRepository = habitsRepository,
            activityRepository = activityRepository,
        ),
        recordHomeHabitProgressUseCase = RecordHomeHabitProgressUseCase(
            habitsRepository = habitsRepository,
            clock = clock,
        ),
        saveHomeMoodSelectionUseCase = SaveHomeMoodSelectionUseCase(
            activityRepository = activityRepository,
            clock = clock,
        ),
        stopHabitFromHomeUseCase = StopHabitFromHomeUseCase(
            habitsRepository = habitsRepository,
            clock = clock,
        ),
        clock = clock,
    ) as T
}

fun NavGraphBuilder.homeScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onCreateHabitRequested: () -> Unit,
    onNavigateToActivity: () -> Unit,
    onNavigateToLearn: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onEditHabitRequested: ((String) -> Unit)? = null,
) {
    composable(route = HomeDestination.route) {
        HomeRoute(
            viewModelFactory = viewModelFactory,
            onCreateHabitRequested = onCreateHabitRequested,
            onNavigateToActivity = onNavigateToActivity,
            onNavigateToLearn = onNavigateToLearn,
            onNavigateToProfile = onNavigateToProfile,
            onEditHabitRequested = onEditHabitRequested,
        )
    }
}
