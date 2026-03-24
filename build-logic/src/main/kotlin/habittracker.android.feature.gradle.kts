import com.threemdroid.habittracker.buildlogic.libsCatalog

plugins {
    id("habittracker.android.library.compose")
}

val libs = project.libsCatalog()

dependencies {
    add("implementation", libs.findLibrary("androidx-lifecycle-runtime-compose").get())
    add("implementation", libs.findLibrary("androidx-navigation-compose").get())
}
