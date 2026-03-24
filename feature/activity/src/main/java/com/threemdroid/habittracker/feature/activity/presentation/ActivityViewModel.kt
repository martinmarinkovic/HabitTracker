package com.threemdroid.habittracker.feature.activity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.feature.activity.contract.ActivityEffect
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState
import com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase
import com.threemdroid.habittracker.feature.activity.domain.nextAnchorDate
import com.threemdroid.habittracker.feature.activity.domain.previousAnchorDate
import com.threemdroid.habittracker.feature.activity.domain.windowFor
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Clock
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class ActivityViewModel @Inject constructor(
    private val observeActivityDataUseCase: ObserveActivityDataUseCase,
    private val clock: Clock,
) : ViewModel() {
    private val defaultPeriod: ActivityPeriod = ActivityPeriod.WEEKLY
    private val initialAnchorDate: LocalDate = LocalDate.now(clock)
    private val initialWindow = defaultPeriod.windowFor(initialAnchorDate)

    private val mutableUiState = MutableStateFlow(
        ActivityUiState.initial(
            selectedPeriod = defaultPeriod,
            periodAnchorDate = initialAnchorDate,
            periodStartDate = initialWindow.startDate,
            periodEndDate = initialWindow.endDate,
        ),
    )
    val uiState: StateFlow<ActivityUiState> = mutableUiState.asStateFlow()

    private val effectsChannel = Channel<ActivityEffect>(capacity = Channel.BUFFERED)
    val effects = effectsChannel.receiveAsFlow()

    private var activityJob: Job? = null

    init {
        observeCurrentSelection()
    }

    fun handleIntent(intent: ActivityIntent) {
        when (intent) {
            is ActivityIntent.PeriodSelected -> selectPeriod(intent.period)
            ActivityIntent.PreviousPeriodRequested -> shiftPeriod(isNext = false)
            ActivityIntent.NextPeriodRequested -> shiftPeriod(isNext = true)
            is ActivityIntent.HabitFilterSelected -> selectHabitFilter(intent.habitId)
            ActivityIntent.RetryLoad -> observeCurrentSelection()
        }
    }

    private fun selectPeriod(period: ActivityPeriod) {
        val currentState = mutableUiState.value
        if (period == currentState.selectedPeriod) {
            return
        }

        val window = period.windowFor(currentState.periodAnchorDate)
        reduce(
            ActivityResult.ConfigurationChanged(
                period = period,
                periodAnchorDate = currentState.periodAnchorDate,
                periodStartDate = window.startDate,
                periodEndDate = window.endDate,
                selectedHabitId = currentState.selectedHabitId,
            ),
        )
        observeCurrentSelection()
    }

    private fun shiftPeriod(isNext: Boolean) {
        val currentState = mutableUiState.value
        val updatedAnchorDate = if (isNext) {
            currentState.selectedPeriod.nextAnchorDate(currentState.periodAnchorDate)
        } else {
            currentState.selectedPeriod.previousAnchorDate(currentState.periodAnchorDate)
        }
        val window = currentState.selectedPeriod.windowFor(updatedAnchorDate)

        reduce(
            ActivityResult.ConfigurationChanged(
                period = currentState.selectedPeriod,
                periodAnchorDate = updatedAnchorDate,
                periodStartDate = window.startDate,
                periodEndDate = window.endDate,
                selectedHabitId = currentState.selectedHabitId,
            ),
        )
        observeCurrentSelection()
    }

    private fun selectHabitFilter(habitId: String?) {
        if (habitId == mutableUiState.value.selectedHabitId) {
            return
        }

        val currentState = mutableUiState.value
        reduce(
            ActivityResult.ConfigurationChanged(
                period = currentState.selectedPeriod,
                periodAnchorDate = currentState.periodAnchorDate,
                periodStartDate = currentState.periodStartDate,
                periodEndDate = currentState.periodEndDate,
                selectedHabitId = habitId,
            ),
        )
        observeCurrentSelection()
    }

    private fun observeCurrentSelection() {
        val currentState = mutableUiState.value
        activityJob?.cancel()
        reduce(ActivityResult.ActivityDataLoading)
        activityJob = viewModelScope.launch {
            observeActivityDataUseCase(
                period = currentState.selectedPeriod,
                anchorDate = currentState.periodAnchorDate,
                selectedHabitId = currentState.selectedHabitId,
            ).collect { result ->
                when (result) {
                    is AppResult.Success -> reduce(ActivityResult.ActivityDataLoaded(result.value))
                    is AppResult.Failure -> reduce(ActivityResult.ActivityDataLoadFailed)
                }
            }
        }
    }

    private fun reduce(result: ActivityResult) {
        mutableUiState.value = ActivityReducer.reduce(
            currentState = mutableUiState.value,
            result = result,
        )
    }
}
