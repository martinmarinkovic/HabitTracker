import com.android.build.api.dsl.ApplicationExtension
import com.threemdroid.habittracker.buildlogic.configureAndroidKotlin
import com.threemdroid.habittracker.buildlogic.configureHabitTrackerApplication
import com.threemdroid.habittracker.buildlogic.libsCatalog

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val libs = project.libsCatalog()

project.configureAndroidKotlin()

extensions.configure<ApplicationExtension> {
    configureHabitTrackerApplication()

    namespace = "com.threemdroid.habittracker"

    defaultConfig.applicationId = "com.threemdroid.habittracker"
}

dependencies {
    add("implementation", libs.findLibrary("androidx-core-ktx").get())
    add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
    add("testImplementation", libs.findLibrary("junit").get())
    add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
    add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
}
