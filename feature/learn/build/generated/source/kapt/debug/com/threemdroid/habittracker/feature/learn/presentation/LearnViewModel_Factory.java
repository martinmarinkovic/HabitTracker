package com.threemdroid.habittracker.feature.learn.presentation;

import com.threemdroid.habittracker.feature.learn.domain.ObserveLearnContentUseCase;
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
public final class LearnViewModel_Factory implements Factory<LearnViewModel> {
  private final Provider<ObserveLearnContentUseCase> observeLearnContentUseCaseProvider;

  private LearnViewModel_Factory(
      Provider<ObserveLearnContentUseCase> observeLearnContentUseCaseProvider) {
    this.observeLearnContentUseCaseProvider = observeLearnContentUseCaseProvider;
  }

  @Override
  public LearnViewModel get() {
    return newInstance(observeLearnContentUseCaseProvider.get());
  }

  public static LearnViewModel_Factory create(
      Provider<ObserveLearnContentUseCase> observeLearnContentUseCaseProvider) {
    return new LearnViewModel_Factory(observeLearnContentUseCaseProvider);
  }

  public static LearnViewModel newInstance(ObserveLearnContentUseCase observeLearnContentUseCase) {
    return new LearnViewModel(observeLearnContentUseCase);
  }
}
