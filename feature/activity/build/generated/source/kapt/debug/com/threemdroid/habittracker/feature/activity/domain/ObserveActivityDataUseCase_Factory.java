package com.threemdroid.habittracker.feature.activity.domain;

import com.threemdroid.habittracker.domain.habits.HabitsRepository;
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
public final class ObserveActivityDataUseCase_Factory implements Factory<ObserveActivityDataUseCase> {
  private final Provider<HabitsRepository> habitsRepositoryProvider;

  private final Provider<Clock> clockProvider;

  private ObserveActivityDataUseCase_Factory(Provider<HabitsRepository> habitsRepositoryProvider,
      Provider<Clock> clockProvider) {
    this.habitsRepositoryProvider = habitsRepositoryProvider;
    this.clockProvider = clockProvider;
  }

  @Override
  public ObserveActivityDataUseCase get() {
    return newInstance(habitsRepositoryProvider.get(), clockProvider.get());
  }

  public static ObserveActivityDataUseCase_Factory create(
      Provider<HabitsRepository> habitsRepositoryProvider, Provider<Clock> clockProvider) {
    return new ObserveActivityDataUseCase_Factory(habitsRepositoryProvider, clockProvider);
  }

  public static ObserveActivityDataUseCase newInstance(HabitsRepository habitsRepository,
      Clock clock) {
    return new ObserveActivityDataUseCase(habitsRepository, clock);
  }
}
