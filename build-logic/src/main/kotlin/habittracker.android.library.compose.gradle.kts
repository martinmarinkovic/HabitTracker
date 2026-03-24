import com.android.build.api.dsl.LibraryExtension
import com.threemdroid.habittracker.buildlogic.libsCatalog

plugins {
    id("habittracker.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = project.libsCatalog()

extensions.configure<LibraryExtension> {
    buildFeatures {
        compose = true
    }
}

dependencies {
    add("implementation", platform(libs.findLibrary("androidx-compose-bom").get()))
    add("implementation", libs.findLibrary("androidx-compose-foundation").get())
    add("implementation", libs.findLibrary("androidx-compose-material3").get())
    add("implementation", libs.findLibrary("androidx-compose-ui").get())
    add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
    add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))
    add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-junit4").get())
    add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
}
