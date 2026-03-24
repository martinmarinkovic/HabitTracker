package com.threemdroid.habittracker.data.learn.di

import com.threemdroid.habittracker.data.learn.remote.HttpLearnRemoteDataSource
import com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource
import com.threemdroid.habittracker.data.learn.repository.LearnRepositoryImpl
import com.threemdroid.habittracker.domain.learn.LearnRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LearnDataModule {
    @Binds
    @Singleton
    fun bindLearnRemoteDataSource(
        impl: HttpLearnRemoteDataSource,
    ): LearnRemoteDataSource

    @Binds
    @Singleton
    fun bindLearnRepository(
        impl: LearnRepositoryImpl,
    ): LearnRepository
}
