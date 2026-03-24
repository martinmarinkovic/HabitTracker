package com.threemdroid.habittracker.feature.activity.presentation;

import com.threemdroid.habittracker.feature.activity.domain.ObserveActivityDataUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.time.Clock;
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
public final class ActivityViewModel_Factory implements Factory<ActivityViewModel> {
  private final Provider<ObserveActivityDataUseCase> observeActivityDataUseCaseProvider;

  private final Provider<Clock> clockProvider;

  private ActivityViewModel_Factory(
      Provider<ObserveActivityDataUseCase> observeActivityDataUseCaseProvider,
      Provider<Clock> clockProvider) {
    this.observeActivityDataUseCaseProvider = observeActivityDataUseCaseProvider;
    this.clockProvider = clockProvider;
  }

  @Override
  public ActivityViewModel get() {
    return newInstance(observeActivityDataUseCaseProvider.get(), clockProvider.get());
  }

  public static ActivityViewModel_Factory create(
      Provider<ObserveActivityDataUseCase> observeActivityDataUseCaseProvider,
      Provider<Clock> clockProvider) {
    return new ActivityViewModel_Factory(observeActivityDataUseCaseProvider, clockProvider);
  }

  public static ActivityViewModel newInstance(ObserveActivityDataUseCase observeActivityDataUseCase,
      Clock clock) {
    return new ActivityViewModel(observeActivityDataUseCase, clock);
  }
}
