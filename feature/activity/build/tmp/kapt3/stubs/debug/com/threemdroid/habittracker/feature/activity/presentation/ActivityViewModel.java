package com.threemdroid.habittracker.feature.activity.presentation;

import androidx.lifecycle.ViewModel;
import com.threemdroid.habittracker.core.common.result.AppResult;
import com.threemdroid.habittracker.feature.activity.contract.ActivityEffect;
import com.threemdroid.habittracker.feature.activity.contract.ActivityIntent;
import com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod;
import com.threemdroid.habittracker.feature.activity.contract.ActivityUiState;
import com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.time.Clock;
import java.time.LocalDate;
import javax.inject.Inject;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\tH\u0002J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010\'\u001a\u00020\u001f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\b\u0010*\u001a\u00020\u001fH\u0002J\u0010\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityViewModel;", "Landroidx/lifecycle/ViewModel;", "observeActivityDataUseCase", "Lcom/threemdroid/habittracker/feature/activity/domain/ObserveActivityDataUseCase;", "clock", "Ljava/time/Clock;", "<init>", "(Lcom/threemdroid/habittracker/feature/activity/domain/ObserveActivityDataUseCase;Ljava/time/Clock;)V", "defaultPeriod", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityPeriod;", "initialAnchorDate", "Ljava/time/LocalDate;", "initialWindow", "Lcom/threemdroid/habittracker/feature/activity/domain/ActivityPeriodWindow;", "mutableUiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "effectsChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityEffect;", "effects", "Lkotlinx/coroutines/flow/Flow;", "getEffects", "()Lkotlinx/coroutines/flow/Flow;", "activityJob", "Lkotlinx/coroutines/Job;", "handleIntent", "", "intent", "Lcom/threemdroid/habittracker/feature/activity/contract/ActivityIntent;", "selectPeriod", "period", "shiftPeriod", "isNext", "", "selectHabitFilter", "habitId", "", "observeCurrentSelection", "reduce", "result", "Lcom/threemdroid/habittracker/feature/activity/presentation/ActivityResult;", "activity_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ActivityViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase observeActivityDataUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.Clock clock = null;
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod defaultPeriod = com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod.WEEKLY;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDate initialAnchorDate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.activity.domain.ActivityPeriodWindow initialWindow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.threemdroid.habittracker.feature.activity.contract.ActivityUiState> mutableUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.threemdroid.habittracker.feature.activity.contract.ActivityUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.threemdroid.habittracker.feature.activity.contract.ActivityEffect> effectsChannel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.feature.activity.contract.ActivityEffect> effects = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job activityJob;
    
    @javax.inject.Inject()
    public ActivityViewModel(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase observeActivityDataUseCase, @org.jetbrains.annotations.NotNull()
    java.time.Clock clock) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.threemdroid.habittracker.feature.activity.contract.ActivityUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.feature.activity.contract.ActivityEffect> getEffects() {
        return null;
    }
    
    public final void handleIntent(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.activity.contract.ActivityIntent intent) {
    }
    
    private final void selectPeriod(com.threemdroid.habittracker.feature.activity.contract.ActivityPeriod period) {
    }
    
    private final void shiftPeriod(boolean isNext) {
    }
    
    private final void selectHabitFilter(java.lang.String habitId) {
    }
    
    private final void observeCurrentSelection() {
    }
    
    private final void reduce(com.threemdroid.habittracker.feature.activity.presentation.ActivityResult result) {
    }
}