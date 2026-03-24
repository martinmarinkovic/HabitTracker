package com.threemdroid.habittracker.feature.activity

import com.threemdroid.habittracker.core.common.result.AppError
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.contract.ActivityScreenState
import com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase
import com.threemdroid.habittracker.feature.activity.presentation.ActivityViewModel
import com.threemdroid.habittracker.feature.activity.testutil.ACTIVITY_TEST_DATE
import com.threemdroid.habittracker.feature.activity.testutil.TestActivityHabitsRepository
import com.threemdroid.habittracker.feature.activity.testutil.activityObserveUseCase
import com.threemdroid.habittracker.feature.activity.testutil.activityTestEntry
import com.threemdroid.habittracker.feature.activity.testutil.activityTestHabit
import com.threemdroid.habittracker.feature.activity.testutil.activityTestClock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ActivityViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        kotlinx.coroutines.Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        kotlinx.coroutines.Dispatchers.resetMain()
    }

    @Test
    fun init_loadsWeeklyActivityContent() = runTest(dispatcher) {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(listOf(activityTestHabit(id = "habit-a", name = "Walk")))
            setEntries(
                listOf(
                    activityTestEntry(
                        id = "entry-1",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE,
                        value = 1.0,
                    ),
                ),
            )
        }

        val viewModel = activityViewModel(repository)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(ActivityPeriod.WEEKLY, state.selectedPeriod)
        assertEquals(ActivityScreenState.Content, state.screenState)
        assertEquals(1.0, state.analyticsSummary.totalProgress, 0.0)
        assertEquals(listOf("All Habits", "Walk"), state.availableHabitFilters.map { it.name })
    }

    @Test
    fun handleIntent_periodSelected_updatesWindowAndReloadsData() = runTest(dispatcher) {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(listOf(activityTestHabit(id = "habit-a", name = "Walk")))
            setEntries(emptyList())
        }

        val viewModel = activityViewModel(repository)
        advanceUntilIdle()

        viewModel.handleIntent(ActivityIntent.PeriodSelected(ActivityPeriod.MONTHLY))
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals(ActivityPeriod.MONTHLY, state.selectedPeriod)
        assertEquals(ACTIVITY_TEST_DATE.withDayOfMonth(1), state.periodStartDate)
        assertEquals(ACTIVITY_TEST_DATE.withDayOfMonth(ACTIVITY_TEST_DATE.lengthOfMonth()), state.periodEndDate)
        assertEquals(ActivityScreenState.Content, state.screenState)
    }

    @Test
    fun handleIntent_habitFilterSelected_updatesSelectedHabitAnalytics() = runTest(dispatcher) {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(
                listOf(
                    activityTestHabit(id = "habit-a", name = "Walk"),
                    activityTestHabit(id = "habit-b", name = "Read"),
                ),
            )
            setEntries(
                listOf(
                    activityTestEntry(
                        id = "entry-1",
                        habitId = "habit-a",
                        entryDate = ACTIVITY_TEST_DATE,
                        value = 2.0,
                    ),
                    activityTestEntry(
                        id = "entry-2",
                        habitId = "habit-b",
                        entryDate = ACTIVITY_TEST_DATE,
                        value = 1.0,
                    ),
                ),
            )
        }

        val viewModel = activityViewModel(repository)
        advanceUntilIdle()

        viewModel.handleIntent(ActivityIntent.HabitFilterSelected("habit-b"))
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("habit-b", state.selectedHabitId)
        assertEquals(1.0, state.analyticsSummary.totalProgress, 0.0)
    }

    @Test
    fun handleIntent_previousAndNextPeriod_shiftAnchorDate() = runTest(dispatcher) {
        val repository = TestActivityHabitsRepository().apply {
            setHabits(listOf(activityTestHabit(id = "habit-a", name = "Walk")))
            setEntries(emptyList())
        }

        val viewModel = activityViewModel(repository)
        advanceUntilIdle()

        viewModel.handleIntent(ActivityIntent.PreviousPeriodRequested)
        advanceUntilIdle()
        assertEquals(ACTIVITY_TEST_DATE.minusWeeks(1), viewModel.uiState.value.periodAnchorDate)

        viewModel.handleIntent(ActivityIntent.NextPeriodRequested)
        advanceUntilIdle()
        assertEquals(ACTIVITY_TEST_DATE, viewModel.uiState.value.periodAnchorDate)
    }

    @Test
    fun handleIntent_whenLoadFails_setsErrorState() = runTest(dispatcher) {
        val repository = TestActivityHabitsRepository().apply {
            setHabitsFailure(
                AppError.Storage(
                    source = "habits",
                    message = "Habit query failed.",
                ),
            )
        }

        val viewModel = activityViewModel(repository)
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value.screenState is ActivityScreenState.Error)
        assertNull(viewModel.uiState.value.selectedHabitId)
    }

    private fun activityViewModel(
        repository: TestActivityHabitsRepository,
        observeActivityDataUseCase: ObserveActivityDataUseCase = activityObserveUseCase(repository),
    ): ActivityViewModel = ActivityViewModel(
        observeActivityDataUseCase = observeActivityDataUseCase,
        clock = activityTestClock(),
    )
}
