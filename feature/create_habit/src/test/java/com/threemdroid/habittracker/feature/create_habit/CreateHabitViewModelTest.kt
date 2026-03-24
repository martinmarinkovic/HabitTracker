package com.threemdroid.habittracker.feature.create_habit

import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.core.model.habits.HabitType
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitEffect
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitIntent
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitSaveError
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.ValidateCreateHabitDraftUseCase
import com.threemdroid.habittracker.feature.create_habit.presentation.CreateHabitViewModel
import com.threemdroid.habittracker.feature.create_habit.testutil.TestCreateHabitHabitsRepository
import com.threemdroid.habittracker.feature.create_habit.testutil.createHabitUseCase
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
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CreateHabitViewModelTest {
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
    fun nameChanged_updatesUiState() = runTest(dispatcher) {
        val viewModel = createViewModel(TestCreateHabitHabitsRepository())

        viewModel.handleIntent(CreateHabitIntent.NameChanged("Read"))

        assertEquals("Read", viewModel.uiState.value.nameInput)
    }

    @Test
    fun schedulePresetSelected_andCustomWeekdayToggled_updatesSelectedDays() = runTest(dispatcher) {
        val viewModel = createViewModel(TestCreateHabitHabitsRepository())

        viewModel.handleIntent(CreateHabitIntent.SchedulePresetSelected(CreateHabitSchedulePreset.WEEKDAYS))
        assertEquals(CreateHabitSchedulePreset.WEEKDAYS, viewModel.uiState.value.schedulePreset)
        assertEquals(5, viewModel.uiState.value.selectedDays.size)

        viewModel.handleIntent(CreateHabitIntent.CustomWeekdayToggled(java.time.DayOfWeek.MONDAY))

        assertEquals(CreateHabitSchedulePreset.CUSTOM, viewModel.uiState.value.schedulePreset)
        assertEquals(
            setOf(
                java.time.DayOfWeek.TUESDAY,
                java.time.DayOfWeek.WEDNESDAY,
                java.time.DayOfWeek.THURSDAY,
                java.time.DayOfWeek.FRIDAY,
            ),
            viewModel.uiState.value.selectedDays,
        )
    }

    @Test
    fun reminderAdded_andRemoved_updatesReminderState() = runTest(dispatcher) {
        val viewModel = createViewModel(TestCreateHabitHabitsRepository())

        viewModel.handleIntent(CreateHabitIntent.ReminderAdded)
        assertEquals(1, viewModel.uiState.value.reminders.size)
        assertEquals("generated-reminder-id", viewModel.uiState.value.reminders.single().reminderId)

        viewModel.handleIntent(CreateHabitIntent.ReminderRemoved("generated-reminder-id"))

        assertTrue(viewModel.uiState.value.reminders.isEmpty())
    }

    @Test
    fun saveRequested_withMissingSelections_updatesValidationState() = runTest(dispatcher) {
        val viewModel = createViewModel(TestCreateHabitHabitsRepository())
        viewModel.handleIntent(CreateHabitIntent.NameChanged("  "))
        viewModel.handleIntent(CreateHabitIntent.IconSelected(""))
        viewModel.handleIntent(CreateHabitIntent.ColorSelected(""))

        viewModel.handleIntent(CreateHabitIntent.SaveRequested)

        val validation = viewModel.uiState.value.validation
        assertEquals(com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitNameValidationError.REQUIRED, validation.nameError)
        assertEquals(com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSelectionValidationError.REQUIRED, validation.iconError)
        assertEquals(com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSelectionValidationError.REQUIRED, validation.colorError)
        assertFalse(viewModel.uiState.value.isSaving)
    }

    @Test
    fun saveRequested_withValidQuantityHabit_persistsHabitAndEmitsSuccessEffect() = runTest(dispatcher) {
        val repository = TestCreateHabitHabitsRepository()
        val viewModel = createViewModel(repository)
        viewModel.handleIntent(CreateHabitIntent.NameChanged("Walk"))
        viewModel.handleIntent(CreateHabitIntent.IconSelected("shoe"))
        viewModel.handleIntent(CreateHabitIntent.ColorSelected("green"))
        viewModel.handleIntent(CreateHabitIntent.HabitTypeSelected(HabitType.QUANTITY))
        viewModel.handleIntent(CreateHabitIntent.TargetValueChanged("8"))
        viewModel.handleIntent(CreateHabitIntent.DefaultIncrementChanged("2"))
        viewModel.handleIntent(CreateHabitIntent.UnitLabelChanged("km"))
        viewModel.handleIntent(CreateHabitIntent.ReminderAdded)

        viewModel.handleIntent(CreateHabitIntent.SaveRequested)
        advanceUntilIdle()

        assertEquals(1, repository.savedHabits.size)
        assertEquals(1, repository.savedReminders.size)
        assertEquals(
            CreateHabitEffect.HabitCreated("habit-created-id"),
            viewModel.effects.first(),
        )
        assertFalse(viewModel.uiState.value.isSaving)
    }

    @Test
    fun saveRequested_whenRepositoryFails_emitsSaveErrorEffect() = runTest(dispatcher) {
        val repository = TestCreateHabitHabitsRepository().apply {
            upsertHabitResult = AppResult.Failure(
                com.threemdroid.habittracker.core.common.result.AppError.Storage(
                    source = "TestCreateHabitHabitsRepository",
                    message = "Failed to save habit.",
                ),
            )
        }
        val viewModel = createViewModel(repository)
        viewModel.handleIntent(CreateHabitIntent.NameChanged("Walk"))
        viewModel.handleIntent(CreateHabitIntent.IconSelected("shoe"))
        viewModel.handleIntent(CreateHabitIntent.ColorSelected("green"))

        viewModel.handleIntent(CreateHabitIntent.SaveRequested)
        advanceUntilIdle()

        assertEquals(
            CreateHabitEffect.ShowSaveError(CreateHabitSaveError.GENERIC),
            viewModel.effects.first(),
        )
        assertFalse(viewModel.uiState.value.isSaving)
    }

    private fun createViewModel(
        repository: TestCreateHabitHabitsRepository,
    ): CreateHabitViewModel = CreateHabitViewModel(
        validateCreateHabitDraftUseCase = ValidateCreateHabitDraftUseCase(),
        createHabitUseCase = createHabitUseCase(repository),
        reminderIdGenerator = { "generated-reminder-id" },
    )
}
