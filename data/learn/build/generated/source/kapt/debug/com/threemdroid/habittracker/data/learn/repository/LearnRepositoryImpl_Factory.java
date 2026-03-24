package com.threemdroid.habittracker.data.learn.repository;

import com.threemdroid.habittracker.data.learn.remote.LearnRemoteDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class LearnRepositoryImpl_Factory implements Factory<LearnRepositoryImpl> {
  private final Provider<LearnRemoteDataSource> remoteDataSourceProvider;

  private LearnRepositoryImpl_Factory(Provider<LearnRemoteDataSource> remoteDataSourceProvider) {
    this.remoteDataSourceProvider = remoteDataSourceProvider;
  }

  @Override
  public LearnRepositoryImpl get() {
    return newInstance(remoteDataSourceProvider.get());
  }

  public static LearnRepositoryImpl_Factory create(
      Provider<LearnRemoteDataSource> remoteDataSourceProvider) {
    return new LearnRepositoryImpl_Factory(remoteDataSourceProvider);
  }

  public static LearnRepositoryImpl newInstance(LearnRemoteDataSource remoteDataSource) {
    return new LearnRepositoryImpl(remoteDataSource);
  }
}
