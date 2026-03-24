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

@kotlin.Metadata(mv = {2, 2, 0}, k = 2, xi = 48, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u00a8\u0006\u0004"}, d2 = {"toLearnAppError", "Lcom/threemdroid/habittracker/core/common/result/AppError;", "throwable", "", "learn_debug"})
public final class LearnRepositoryImplKt {
    
    private static final com.threemdroid.habittracker.core.common.result.AppError toLearnAppError(java.lang.Throwable throwable) {
        return null;
    }
}