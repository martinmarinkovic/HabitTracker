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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/threemdroid/habittracker/data/learn/remote/LearnHttpException;", "Ljava/io/IOException;", "code", "", "message", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "learn_debug"})
public final class LearnHttpException extends java.io.IOException {
    private final int code = 0;
    
    public LearnHttpException(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        super();
    }
    
    public final int getCode() {
        return 0;
    }
}