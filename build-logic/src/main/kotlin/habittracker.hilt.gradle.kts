import com.threemdroid.habittracker.buildlogic.libsCatalog
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

plugins {
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
}

val libs = project.libsCatalog()

extensions.configure<KaptExtension> {
    correctErrorTypes = true
}

dependencies {
    add("implementation", libs.findLibrary("hilt-android").get())
    add("kapt", libs.findLibrary("hilt-compiler").get())
}
