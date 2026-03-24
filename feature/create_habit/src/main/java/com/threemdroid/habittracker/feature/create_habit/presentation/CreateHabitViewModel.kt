package com.threemdroid.habittracker.feature.create_habit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.threemdroid.habittracker.core.common.result.AppResult
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitEffect
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitIntent
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitReminderItem
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitSaveError
import com.threemdroid.habittracker.feature.create_habit.contract.CreateHabitUiState
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitDraft
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitReminderDraft
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitSchedulePreset
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitUseCase
import com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitValidationResult
import com.threemdroid.habittracker.feature.create_habit.domain.ValidateCreateHabitDraftUseCase
import com.threemdroid.habittracker.feature.create_habit.domain.resolveSelectedDays
import java.time.DayOfWeek
import java.time.LocalTime
import java.util.UUID
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class CreateHabitViewModel(
    private val validateCreateHabitDraftUseCase: ValidateCreateHabitDraftUseCase,
    private val createHabitUseCase: CreateHabitUseCase,
    private val reminderIdGenerator: () -> String = { UUID.randomUUID().toString() },
    private val defaultReminderTime: LocalTime = LocalTime.of(9, 0),
) : ViewModel() {
    private val mutableUiState = MutableStateFlow(
        CreateHabitUiState.initial(
            selectedDays = resolveSelectedDays(
                preset = CreateHabitSchedulePreset.EVERY_DAY,
                customSelectedDays = emptySet(),
            ),
        ),
    )
    val uiState: StateFlow<CreateHabitUiState> = mutableUiState.asStateFlow()

    private val effectsChannel = Channel<CreateHabitEffect>(capacity = Channel.BUFFERED)
    val effects = effectsChannel.receiveAsFlow()

    fun handleIntent(intent: CreateHabitIntent) {
        when (intent) {
            is CreateHabitIntent.NameChanged -> reduce(CreateHabitResult.NameChanged(intent.value))
            is CreateHabitIntent.IconSelected -> reduce(CreateHabitResult.IconSelected(intent.iconToken))
            is CreateHabitIntent.ColorSelected -> reduce(CreateHabitResult.ColorSelected(intent.colorToken))
            is CreateHabitIntent.HabitTypeSelected -> reduce(CreateHabitResult.HabitTypeSelected(intent.habitType))
            is CreateHabitIntent.TargetValueChanged -> reduce(CreateHabitResult.TargetValueChanged(intent.value))
            is CreateHabitIntent.DefaultIncrementChanged -> reduce(CreateHabitResult.DefaultIncrementChanged(intent.value))
            is CreateHabitIntent.UnitLabelChanged -> reduce(CreateHabitResult.UnitLabelChanged(intent.value))
            is CreateHabitIntent.MultipleUpdatesPerDayChanged -> reduce(
                CreateHabitResult.MultipleUpdatesPerDayChanged(intent.isEnabled),
            )
            is CreateHabitIntent.SchedulePresetSelected -> selectSchedulePreset(intent.preset)
            is CreateHabitIntent.CustomWeekdayToggled -> toggleCustomWeekday(intent.dayOfWeek)
            CreateHabitIntent.ReminderAdded -> addReminder()
            is CreateHabitIntent.ReminderRemoved -> reduce(CreateHabitResult.ReminderRemoved(intent.reminderId))
            is CreateHabitIntent.ReminderTimeChanged -> reduce(
                CreateHabitResult.ReminderTimeChanged(
                    reminderId = intent.reminderId,
                    time = intent.time,
                ),
            )
            is CreateHabitIntent.ReminderEnabledChanged -> reduce(
                CreateHabitResult.ReminderEnabledChanged(
                    reminderId = intent.reminderId,
                    isEnabled = intent.isEnabled,
                ),
            )
            CreateHabitIntent.SaveRequested -> saveHabit()
        }
    }

    private fun selectSchedulePreset(
        preset: CreateHabitSchedulePreset,
    ) {
        val selectedDays = resolveSelectedDays(
            preset = preset,
            customSelectedDays = mutableUiState.value.selectedDays,
        )
        reduce(
            CreateHabitResult.SchedulePresetSelected(
                preset = preset,
                selectedDays = selectedDays,
            ),
        )
    }

    private fun toggleCustomWeekday(
        dayOfWeek: DayOfWeek,
    ) {
        val currentDays = mutableUiState.value.selectedDays
        val updatedDays = if (dayOfWeek in currentDays) {
            currentDays - dayOfWeek
        } else {
            currentDays + dayOfWeek
        }
        reduce(CreateHabitResult.CustomSelectedDaysChanged(updatedDays))
    }

    private fun addReminder() {
        reduce(
            CreateHabitResult.ReminderAdded(
                CreateHabitReminderItem(
                    reminderId = reminderIdGenerator(),
                    time = defaultReminderTime,
                    isEnabled = true,
                ),
            ),
        )
    }

    private fun saveHabit() {
        if (mutableUiState.value.isSaving) {
            return
        }

        when (val validationResult = validateCreateHabitDraftUseCase(mutableUiState.value.toDraft())) {
            is CreateHabitValidationResult.Invalid -> {
                reduce(CreateHabitResult.ValidationUpdated(validationResult.validation))
            }
            is CreateHabitValidationResult.Valid -> {
                reduce(CreateHabitResult.ValidationUpdated(com.threemdroid.habittracker.feature.create_habit.domain.CreateHabitValidationState()))
                reduce(CreateHabitResult.SavingStateChanged(true))
                viewModelScope.launch {
                    when (val result = createHabitUseCase(validationResult.draft)) {
                        is AppResult.Success -> {
                            reduce(CreateHabitResult.SavingStateChanged(false))
                            emitEffect(CreateHabitEffect.HabitCreated(result.value))
                        }
                        is AppResult.Failure -> {
                            reduce(CreateHabitResult.SavingStateChanged(false))
                            emitEffect(
                                CreateHabitEffect.ShowSaveError(CreateHabitSaveError.GENERIC),
                            )
                        }
                    }
                }
            }
        }
    }

    private fun reduce(
        result: CreateHabitResult,
    ) {
        mutableUiState.value = CreateHabitReducer.reduce(
            currentState = mutableUiState.value,
            result = result,
        )
    }

    private fun emitEffect(
        effect: CreateHabitEffect,
    ) {
        viewModelScope.launch {
            effectsChannel.send(effect)
        }
    }
}

private fun CreateHabitUiState.toDraft(): CreateHabitDraft = CreateHabitDraft(
    name = nameInput,
    selectedIconToken = selectedIconToken,
    selectedColorToken = selectedColorToken,
    habitType = habitType,
    targetValueInput = targetValueInput,
    defaultIncrementInput = defaultIncrementInput,
    unitLabelInput = unitLabelInput,
    allowsMultipleUpdatesPerDay = allowsMultipleUpdatesPerDay,
    schedulePreset = schedulePreset,
    selectedDays = selectedDays,
    reminders = reminders.map { reminder ->
        CreateHabitReminderDraft(
            reminderId = reminder.reminderId,
            time = reminder.time,
            isEnabled = reminder.isEnabled,
        )
    },
)
