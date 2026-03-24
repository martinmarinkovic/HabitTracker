package com.threemdroid.habittracker.feature.learn.domain;

import com.threemdroid.habittracker.domain.learn.LearnRepository;
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
public final class ObserveLearnContentUseCase_Factory implements Factory<ObserveLearnContentUseCase> {
  private final Provider<LearnRepository> learnRepositoryProvider;

  private ObserveLearnContentUseCase_Factory(Provider<LearnRepository> learnRepositoryProvider) {
    this.learnRepositoryProvider = learnRepositoryProvider;
  }

  @Override
  public ObserveLearnContentUseCase get() {
    return newInstance(learnRepositoryProvider.get());
  }

  public static ObserveLearnContentUseCase_Factory create(
      Provider<LearnRepository> learnRepositoryProvider) {
    return new ObserveLearnContentUseCase_Factory(learnRepositoryProvider);
  }

  public static ObserveLearnContentUseCase newInstance(LearnRepository learnRepository) {
    return new ObserveLearnContentUseCase(learnRepository);
  }
}
