package com.threemdroid.habittracker.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun ApplicationExtension.configureHabitTrackerApplication() {
    compileSdk = 36
    compileSdkExtension = 1

    defaultConfig {
        minSdk = 28
        targetSdk = 36
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

internal fun LibraryExtension.configureHabitTrackerLibrary(namespace: String) {
    compileSdk = 36
    compileSdkExtension = 1
    this.namespace = namespace

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

internal fun Project.libsCatalog(): VersionCatalog =
    extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

internal fun Project.moduleNamespace(): String {
    val segments = path
        .split(":")
        .filter(String::isNotBlank)
        .joinToString(".") { it.replace("-", "_") }

    return "com.threemdroid.habittracker.$segments"
}

internal fun Project.configureJvmToolchain() {
    extensions.getByType(JavaPluginExtension::class.java).toolchain.languageVersion.set(JavaLanguageVersion.of(11))
    extensions.getByType(KotlinJvmProjectExtension::class.java).compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
}

internal fun Project.configureAndroidKotlin() {
    extensions.getByType(KotlinAndroidProjectExtension::class.java).compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
}
