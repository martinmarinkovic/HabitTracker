package com.threemdroid.habittracker.data.learn.repository;

import com.threemdroid.habittracker.core.common.result.AppError;
import com.threemdroid.habittracker.core.common.result.AppResult;
import com.threemdroid.habittracker.core.model.learn.LearnContent;
import com.threemdroid.habittracker.data.learn.remote.LearnHttpException;
import com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource;
import com.threemdroid.habittracker.domain.learn.LearnRepository;
import java.io.IOException;
import javax.inject.Inject;
import kotlinx.coroutines.flow.Flow;
import kotlinx.serialization.SerializationException;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/threemdroid/habittracker/data/learn/repository/LearnRepositoryImpl;", "Lcom/threemdroid/habittracker/domain/learn/LearnRepository;", "remoteDataSource", "Lcom/threemdroid/habittracker/data/learn/remote/LearnRemoteDataSource;", "<init>", "(Lcom/threemdroid/habittracker/data/learn/remote/LearnRemoteDataSource;)V", "observeLearnContent", "Lkotlinx/coroutines/flow/Flow;", "Lcom/threemdroid/habittracker/core/common/result/AppResult;", "", "Lcom/threemdroid/habittracker/core/model/learn/LearnContent;", "learn_debug"})
public final class LearnRepositoryImpl implements com.threemdroid.habittracker.domain.learn.LearnRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource remoteDataSource = null;
    
    @javax.inject.Inject()
    public LearnRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource remoteDataSource) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.threemdroid.habittracker.core.common.result.AppResult<java.util.List<com.threemdroid.habittracker.core.model.learn.LearnContent>>> observeLearnContent() {
        return null;
    }
}