package com.threemdroid.habittracker.feature.learn.presentation;

import androidx.lifecycle.ViewModel;
import com.threemdroid.habittracker.core.common.result.AppError;
import com.threemdroid.habittracker.core.common.result.AppResult;
import com.threemdroid.habittracker.feature.learn.contract.LearnEffect;
import com.threemdroid.habittracker.feature.learn.contract.LearnIntent;
import com.threemdroid.habittracker.feature.learn.contract.LearnUiState;
import com.threemdroid.habittracker.feature.learn.domain.ObserveLearnContentUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0002J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/threemdroid/habittracker/feature/learn/presentation/LearnViewModel;", "Landroidx/lifecycle/ViewModel;", "observeLearnContentUseCase", "Lcom/threemdroid/habittracker/feature/learn/domain/ObserveLearnContentUseCase;", "<init>", "(Lcom/threemdroid/habittracker/feature/learn/domain/ObserveLearnContentUseCase;)V", "mutableUiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/threemdroid/habittracker/feature/learn/contract/LearnUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "effectsChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/threemdroid/habittracker/feature/learn/contract/LearnEffect;", "effects", "Lkotlinx/coroutines/flow/Flow;", "getEffects", "()Lkotlinx/coroutines/flow/Flow;", "observeJob", "Lkotlinx/coroutines/Job;", "handleIntent", "", "intent", "Lcom/threemdroid/habittracker/feature/learn/contract/LearnIntent;", "selectArticle", "articleId", "", "dismissDetail", "emitVideoEffectIfAvailable", "loadContent", "reduce", "result", "Lcom/threemdroid/habittracker/feature/learn/presentation/LearnResult;", "learn_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LearnViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.feature.learn.domain.ObserveLearnContentUseCase observeLearnContentUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.threemdroid.habittracker.feature.learn.contract.LearnUiState> mutableUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.threemdroid.habittracker.feature.learn.contract.LearnUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.threemdroid.habittracker.feature.learn.contract.LearnEffect> effectsChannel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.feature.learn.contract.LearnEffect> effects = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job observeJob;
    
    @javax.inject.Inject()
    public LearnViewModel(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.learn.domain.ObserveLearnContentUseCase observeLearnContentUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.threemdroid.habittracker.feature.learn.contract.LearnUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.feature.learn.contract.LearnEffect> getEffects() {
        return null;
    }
    
    public final void handleIntent(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.feature.learn.contract.LearnIntent intent) {
    }
    
    private final void selectArticle(java.lang.String articleId) {
    }
    
    private final void dismissDetail() {
    }
    
    private final void emitVideoEffectIfAvailable() {
    }
    
    private final void loadContent() {
    }
    
    private final void reduce(com.threemdroid.habittracker.feature.learn.presentation.LearnResult result) {
    }
}