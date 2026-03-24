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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\t\b\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u0002H\u0012\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0006\u0010\u0013\u001a\u00020\u000fH\u0082H\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0082@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/threemdroid/habittracker/data/learn/remote/HttpLearnRemoteDataSource;", "Lcom/threemdroid/habittracker/data/learn/remote/LearnRemoteDataSource;", "<init>", "()V", "client", "Lokhttp3/OkHttpClient;", "json", "Lkotlinx/serialization/json/Json;", "fetchCategories", "", "Lcom/threemdroid/habittracker/data/learn/model/LearnCategoryDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDetailItems", "Lcom/threemdroid/habittracker/data/learn/model/LearnDetailItemDto;", "categoryId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch", "T", "url", "fetchBody", "learn_debug"})
public final class HttpLearnRemoteDataSource implements com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource {
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.serialization.json.Json json = null;
    
    @javax.inject.Inject()
    public HttpLearnRemoteDataSource() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object fetchCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.threemdroid.habittracker.data.learn.model.LearnCategoryDto>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object fetchDetailItems(@org.jetbrains.annotations.NotNull()
    java.lang.String categoryId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.threemdroid.habittracker.data.learn.model.LearnDetailItemDto>> $completion) {
        return null;
    }
    
    private final java.lang.Object fetchBody(java.lang.String url, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}