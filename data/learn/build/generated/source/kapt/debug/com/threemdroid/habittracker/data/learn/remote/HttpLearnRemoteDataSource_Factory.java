package com.threemdroid.habittracker.data.learn.remote;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class HttpLearnRemoteDataSource_Factory implements Factory<HttpLearnRemoteDataSource> {
  @Override
  public HttpLearnRemoteDataSource get() {
    return newInstance();
  }

  public static HttpLearnRemoteDataSource_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HttpLearnRemoteDataSource newInstance() {
    return new HttpLearnRemoteDataSource();
  }

  private static final class InstanceHolder {
    static final HttpLearnRemoteDataSource_Factory INSTANCE = new HttpLearnRemoteDataSource_Factory();
  }
}
