package com.threemdroid.habittracker.data.learn.di;

import com.threemdroid.habittracker.data.learn.remote.HttpLearnRemoteDataSource;
import com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource;
import com.threemdroid.habittracker.data.learn.repository.LearnRepositoryImpl;
import com.threemdroid.habittracker.domain.learn.LearnRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\bH\'\u00a8\u0006\t\u00c0\u0006\u0003"}, d2 = {"Lcom/threemdroid/habittracker/data/learn/di/LearnDataModule;", "", "bindLearnRemoteDataSource", "Lcom/threemdroid/habittracker/data/learn/remote/LearnRemoteDataSource;", "impl", "Lcom/threemdroid/habittracker/data/learn/remote/HttpLearnRemoteDataSource;", "bindLearnRepository", "Lcom/threemdroid/habittracker/domain/learn/LearnRepository;", "Lcom/threemdroid/habittracker/data/learn/repository/LearnRepositoryImpl;", "learn_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract interface LearnDataModule {
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource bindLearnRemoteDataSource(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.data.learn.remote.HttpLearnRemoteDataSource impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.threemdroid.habittracker.domain.learn.LearnRepository bindLearnRepository(@org.jetbrains.annotations.NotNull()
    com.threemdroid.habittracker.data.learn.repository.LearnRepositoryImpl impl);
}