package com.threemdroid.habittracker.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.feature.home.contract.HomeActionError
import com.threemdroid.habittracker.feature.home.contract.HomeEffect
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingState
import com.threemdroid.habittracker.feature.home.contract.HomeGreetingTimeOfDay
import com.threemdroid.habittracker.feature.home.contract.HomeIntent
import com.threemdroid.habittracker.feature.home.contract.HomeUiState
import com.threemdroid.habittracker.feature.home.contract.QuickAddValidationError
import com.threemdroid.habittracker.feature.home.domain.ObserveHomeDataUseCase
import com.threemdroid.habittracker.feature.home.domain.RecordHomeHabitProgressUseCase
import com.threemdroid.habittracker.feature.home.domain.SaveHomeMoodSelectionUseCase
import com.threemdroid.habittracker.feature.home.domain.StopHabitFromHomeUseCase
import java.time.Clock
import java.time.LocalDate
import java.time.LocalTime
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val observeHomeDataUseCase: ObserveHomeDataUseCase,
    private val recordHomeHabitProgressUseCase: RecordHomeHabitProgressUseCase,
    private val saveHomeMoodSelectionUseCase: SaveHomeMoodSelectionUseCase,
    private val stopHabitFromHomeUseCase: StopHabitFromHomeUseCase,
    private val clock: Clock = Clock.systemDefaultZone(),
) : ViewModel() {
    private val initialDate: LocalDate = LocalDate.now(clock)

    private val mutableUiState = MutableStateFlow(
        HomeUiState.initial(
            selectedDate = initialDate,
            greetingAreaState = greetingAreaStateFor(initialDate),
        ),
    )
    val uiState: StateFlow<HomeUiState> = mutableUiState.asStateFlow()

    private val effectsChannel = Channel<HomeEffect>(capacity = Channel.BUFFERED)
    val effects = effectsChannel.receiveAsFlow()

    private var homeDataJob: Job? = null

    init {
        observeSelectedDate(initialDate)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.SelectDate -> handleDateSelected(intent.date)
            is HomeIntent.BooleanHabitTapped -> recordBooleanHabit(intent.habitId)
            is HomeIntent.QuantityHabitTapped -> openQuickAddOverlay(intent.habitId)
            is HomeIntent.QuickAddValueChanged -> reduce(HomeResult.QuickAddValueChanged(intent.value))
            HomeIntent.ConfirmQuickAdd -> confirmQuickAdd()
            HomeIntent.DismissQuickAdd -> reduce(HomeResult.QuickAddDismissed)
            is HomeIntent.MoodSelected -> saveMoodSelection(intent.moodToken)
            is HomeIntent.EditHabitRequested -> emitEffect(HomeEffect.NavigateToEditHabit(intent.habitId))
            is HomeIntent.StopHabitRequested -> stopHabit(intent.habitId)
            HomeIntent.RetryLoad -> observeSelectedDate(mutableUiState.value.selectedDate)
        }
    }

    private fun handleDateSelected(date: LocalDate) {
        if (date == mutableUiState.value.selectedDate) {
            return
        }

        reduce(
            HomeResult.SelectedDateChanged(
                date = date,
                greetingAreaState = greetingAreaStateFor(date),
            ),
        )
        observeSelectedDate(date)
    }

    private fun observeSelectedDate(date: LocalDate) {
        homeDataJob?.cancel()
        reduce(HomeResult.HomeDataLoading)
        homeDataJob = viewModelScope.launch {
            observeHomeDataUseCase(date).collect { result ->
                when (result) {
                    is AppResult.Success -> reduce(HomeResult.HomeDataLoaded(result.value))
                    is AppResult.Failure -> reduce(HomeResult.HomeDataLoadFailed)
                }
            }
        }
    }

    private fun recordBooleanHabit(habitId: String) {
        viewModelScope.launch {
            when (
                recordHomeHabitProgressUseCase(
                    habitId = habitId,
                    entryDate = mutableUiState.value.selectedDate,
                )
            ) {
                is AppResult.Success -> Unit
                is AppResult.Failure -> emitEffect(
                    HomeEffect.ShowActionError(HomeActionError.HABIT_PROGRESS_SAVE_FAILED),
                )
            }
        }
    }

    private fun openQuickAddOverlay(habitId: String) {
        val habit = mutableUiState.value.habitsToday.firstOrNull { item -> item.habitId == habitId } ?: run {
            emitEffect(HomeEffect.ShowActionError(HomeActionError.HABIT_PROGRESS_SAVE_FAILED))
            return
        }

        reduce(
            HomeResult.QuickAddOpened(
                habitId = habit.habitId,
                habitName = habit.name,
                draftValue = formatQuantity(habit.defaultIncrement),
            ),
        )
    }

    private fun confirmQuickAdd() {
        val quickAddOverlayState = mutableUiState.value.quickAddOverlayState
        val habitId = quickAddOverlayState.habitId ?: return
        val parsedValue = quickAddOverlayState.draftValue.toDoubleOrNull()
        if (parsedValue == null) {
            reduce(HomeResult.QuickAddValidationFailed(QuickAddValidationError.INVALID_NUMBER))
            return
        }
        if (parsedValue <= 0) {
            reduce(HomeResult.QuickAddValidationFailed(QuickAddValidationError.NON_POSITIVE))
            return
        }

        viewModelScope.launch {
            when (
                recordHomeHabitProgressUseCase(
                    habitId = habitId,
                    entryDate = mutableUiState.value.selectedDate,
                    requestedValue = parsedValue,
                )
            ) {
                is AppResult.Success -> reduce(HomeResult.QuickAddDismissed)
                is AppResult.Failure -> emitEffect(
                    HomeEffect.ShowActionError(HomeActionError.HABIT_PROGRESS_SAVE_FAILED),
                )
            }
        }
    }

    private fun saveMoodSelection(moodToken: String) {
        viewModelScope.launch {
            when (
                saveHomeMoodSelectionUseCase(
                    entryDate = mutableUiState.value.selectedDate,
                    moodToken = moodToken,
                )
            ) {
                is AppResult.Success -> reduce(HomeResult.MoodSelectionUpdated(moodToken))
                is AppResult.Failure -> emitEffect(
                    HomeEffect.ShowActionError(HomeActionError.MOOD_SAVE_FAILED),
                )
            }
        }
    }

    private fun stopHabit(habitId: String) {
        viewModelScope.launch {
            when (stopHabitFromHomeUseCase(habitId)) {
                is AppResult.Success -> Unit
                is AppResult.Failure -> emitEffect(
                    HomeEffect.ShowActionError(HomeActionError.STOP_HABIT_FAILED),
                )
            }
        }
    }

    private fun reduce(result: HomeResult) {
        mutableUiState.value = HomeReducer.reduce(
            currentState = mutableUiState.value,
            result = result,
        )
    }

    private fun emitEffect(effect: HomeEffect) {
        viewModelScope.launch {
            effectsChannel.send(effect)
        }
    }

    private fun greetingAreaStateFor(selectedDate: LocalDate): HomeGreetingState {
        val localTime = LocalTime.now(clock)
        val timeOfDay = when (localTime.hour) {
            in 5..11 -> HomeGreetingTimeOfDay.MORNING
            in 12..16 -> HomeGreetingTimeOfDay.AFTERNOON
            else -> HomeGreetingTimeOfDay.EVENING
        }

        return HomeGreetingState(
            timeOfDay = timeOfDay,
            isSelectedDateToday = selectedDate == LocalDate.now(clock),
        )
    }

    private fun formatQuantity(value: Double): String =
        if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
}
