package com.threemdroid.habittracker.feature.create_habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.threemdroid.habittracker.core.navigation.AppDestination
import com.threemdroid.habittracker.domain.habits.HabitsRepository
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitUseCase
import com.threemdroid.habittracker.feature.create_habit.domain.ValidateCreateHabitDraftUseCase
import com.threemdroid.habittracker.feature.create_habit.presentation.CreateHabitRoute
import com.threemdroid.habittracker.feature.create_habit.presentation.CreateHabitViewModel
import java.time.Clock

object CreateHabitDestination : AppDestination {
    override val route: String = "create_habit"
}

fun createHabitViewModelFactory(
    habitsRepository: HabitsRepository,
    clock: Clock = Clock.systemDefaultZone(),
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CreateHabitViewModel(
        validateCreateHabitDraftUseCase = ValidateCreateHabitDraftUseCase(),
        createHabitUseCase = CreateHabitUseCase(
            habitsRepository = habitsRepository,
            clock = clock,
        ),
    ) as T
}

fun NavGraphBuilder.createHabitScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onBackRequested: () -> Unit,
    onHabitCreated: (String) -> Unit = {},
) {
    composable(route = CreateHabitDestination.route) {
        CreateHabitRoute(
            viewModelFactory = viewModelFactory,
            onBackRequested = onBackRequested,
            onHabitCreated = onHabitCreated,
        )
    }
}
