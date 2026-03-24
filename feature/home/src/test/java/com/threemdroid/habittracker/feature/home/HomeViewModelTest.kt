package com.threemdroid.habittracker.feature.home

import com.threemdroid.habittracker.feature.home.contract.HomeActionError
import com.threemdroid.habittracker.feature.home.contract.HomeEffect
import com.threemdroid.habittracker.feature.home.contract.HomeIntent
import com.threemdroid.habittracker.feature.home.contract.HomeScreenState
import com.threemdroid.habittracker.feature.home.domain.ObserveHomeDataUseCase
import com.threemdroid.habittracker.feature.home.domain.RecordHomeHabitProgressUseCase
import com.threemdroid.habittracker.feature.home.domain.SaveHomeMoodSelectionUseCase
import com.threemdroid.habittracker.feature.home.domain.StopHabitFromHomeUseCase
import com.threemdroid.habittracker.feature.home.presentation.HomeViewModel
import com.threemdroid.habittracker.feature.home.testutil.TestHomeActivityRepository
import com.threemdroid.habittracker.feature.home.testutil.TestHomeHabitsRepository
import com.threemdroid.habittracker.feature.home.testutil.homeTestClock
import com.threemdroid.habittracker.feature.home.testutil.homeTestHabit
import com.threemdroid.habittracker.feature.home.testutil.homeTestMoodEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val dispatcher: TestDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialLoad_withScheduledHabit_updatesContentState() = runTest(dispatcher) {
        val habitsRepository = TestHomeHabitsRepository().apply {
            setHabits(listOf(homeTestHabit()))
        }
        val activityRepository = TestHomeActivityRepository().apply {
            setMoodEntries(listOf(homeTestMoodEntry(moodToken = "focused")))
        }

        val viewModel = createViewModel(
            habitsRepository = habitsRepository,
            activityRepository = activityRepository,
        )

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(HomeScreenState.Content, state.screenState)
        assertEquals(com.threemdroid.habittracker.feature.home.testutil.HOME_TEST_DATE, state.selectedDate)
        assertEquals("focused", state.moodSelectionState.selectedMoodToken)
        assertEquals(1, state.habitsToday.size)
    }

    @Test
    fun quantityHabitTapped_andConfirmQuickAdd_savesEntryAndClosesOverlay() = runTest(dispatcher) {
        val habitsRepository = TestHomeHabitsRepository().apply {
            setHabits(
                listOf(
                    homeTestHabit(
                        type = com.threemdroid.habittracker.core.model.habits.HabitType.QUANTITY,
                    ),
                ),
            )
        }

        val viewModel = createViewModel(
            habitsRepository = habitsRepository,
            activityRepository = TestHomeActivityRepository(),
        )
        advanceUntilIdle()

        viewModel.handleIntent(HomeIntent.QuantityHabitTapped("habit-1"))
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value.quickAddOverlayState.isVisible)

        viewModel.handleIntent(HomeIntent.QuickAddValueChanged("3.5"))
        viewModel.handleIntent(HomeIntent.ConfirmQuickAdd)
        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.quickAddOverlayState.isVisible)
        assertEquals(1, habitsRepository.currentEntries().size)
        assertEquals(3.5, habitsRepository.currentEntries().first().value, 0.0)
    }

    @Test
    fun editHabitRequested_emitsNavigateEffect() = runTest(dispatcher) {
        val viewModel = createViewModel(
            habitsRepository = TestHomeHabitsRepository().apply {
                setHabits(listOf(homeTestHabit()))
            },
            activityRepository = TestHomeActivityRepository(),
        )
        advanceUntilIdle()

        viewModel.handleIntent(HomeIntent.EditHabitRequested("habit-1"))

        assertEquals(
            HomeEffect.NavigateToEditHabit("habit-1"),
            viewModel.effects.first(),
        )
    }

    @Test
    fun moodSelected_persistsMoodAndUpdatesState() = runTest(dispatcher) {
        val habitsRepository = TestHomeHabitsRepository().apply {
            setHabits(listOf(homeTestHabit()))
        }
        val activityRepository = TestHomeActivityRepository()
        val viewModel = createViewModel(
            habitsRepository = habitsRepository,
            activityRepository = activityRepository,
        )
        advanceUntilIdle()

        viewModel.handleIntent(HomeIntent.MoodSelected("energized"))
        advanceUntilIdle()

        assertEquals("energized", viewModel.uiState.value.moodSelectionState.selectedMoodToken)
        assertEquals(1, activityRepository.currentMoodEntries().size)
        assertEquals("energized", activityRepository.currentMoodEntries().first().moodToken)
    }

    @Test
    fun stopHabitRequested_failure_emitsActionError() = runTest(dispatcher) {
        val habitsRepository = TestHomeHabitsRepository().apply {
            setHabits(listOf(homeTestHabit()))
            upsertHabitResult = com.threemdroid.habittracker.core.common.result.AppResult.Failure(
                com.threemdroid.habittracker.core.common.result.AppError.Unknown(
                    message = "Failed to update habit.",
                ),
            )
        }
        val viewModel = createViewModel(
            habitsRepository = habitsRepository,
            activityRepository = TestHomeActivityRepository(),
        )
        advanceUntilIdle()

        viewModel.handleIntent(HomeIntent.StopHabitRequested("habit-1"))

        assertEquals(
            HomeEffect.ShowActionError(HomeActionError.STOP_HABIT_FAILED),
            viewModel.effects.first(),
        )
    }

    private fun createViewModel(
        habitsRepository: TestHomeHabitsRepository,
        activityRepository: TestHomeActivityRepository,
    ): HomeViewModel {
        val clock = homeTestClock()
        return HomeViewModel(
            observeHomeDataUseCase = ObserveHomeDataUseCase(
                habitsRepository = habitsRepository,
                activityRepository = activityRepository,
            ),
            recordHomeHabitProgressUseCase = RecordHomeHabitProgressUseCase(
                habitsRepository = habitsRepository,
                clock = clock,
                idGenerator = { "generated-entry-id" },
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
        )
    }
}
