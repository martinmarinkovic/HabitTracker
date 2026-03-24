package com.threemdroid.habittracker.data.learn.remote;

import com.threemdroid.habittracker.data.learn.model.LearnCategoryDto;
import com.threemdroid.habittracker.data.learn.model.LearnDetailItemDto;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/data/learn/remote/LearnRemoteDataSource;", "", "fetchCategories", "", "Lcom/threemdroid/habittracker/data/learn/model/LearnCategoryDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDetailItems", "Lcom/threemdroid/habittracker/data/learn/model/LearnDetailItemDto;", "categoryId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "learn_debug"})
public abstract interface LearnRemoteDataSource {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.threemdroid.habittracker.data.learn.model.LearnCategoryDto>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchDetailItems(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.threemdroid.habittracker.data.learn.model.LearnDetailItemDto>> $completion);
}