package com.threemdroid.habittracker.feature.learn.domain;

import com.threemdroid.habittracker.core.common.result.AppResult;
import com.threemdroid.habittracker.core.model.learn.LearnContent;
import com.threemdroid.habittracker.core.model.learn.LearnContentSection;
import com.threemdroid.habittracker.domain.learn.LearnRepository;
import com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel;
import javax.inject.Inject;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0007H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/threemdroid/habittracker/feature/learn/domain/ObserveLearnContentUseCase;", "", "learnRepository", "Lcom/threemdroid/habittracker/domain/learn/LearnRepository;", "<init>", "(Lcom/threemdroid/habittracker/domain/learn/LearnRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/threemdroid/habittracker/core/common/result/AppResult;", "", "Lcom/threemdroid/habittracker/feature/learn/contract/LearnArticleUiModel;", "learn_debug"})
public final class ObserveLearnContentUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.domain.learn.LearnRepository learnRepository = null;
    
    @javax.inject.Inject()
    public ObserveLearnContentUseCase(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.domain.learn.LearnRepository learnRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.feature.learn.contract.LearnArticleUiModel>>> invoke() {
        return null;
    }
}