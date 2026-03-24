import com.android.build.api.dsl.LibraryExtension
import com.threemdroid.habittracker.buildlogic.configureAndroidKotlin
import com.threemdroid.habittracker.buildlogic.configureHabitTrackerLibrary
import com.threemdroid.habittracker.buildlogic.libsCatalog
import com.threemdroid.habittracker.buildlogic.moduleNamespace

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

val libs = project.libsCatalog()

project.configureAndroidKotlin()

extensions.configure<LibraryExtension> {
    configureHabitTrackerLibrary(project.moduleNamespace())
}

dependencies {
    add("implementation", libs.findLibrary("androidx-core-ktx").get())
    add("testImplementation", libs.findLibrary("junit").get())
}
