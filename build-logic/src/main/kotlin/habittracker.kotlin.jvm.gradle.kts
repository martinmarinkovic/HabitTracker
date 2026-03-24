import com.threemdroid.habittracker.buildlogic.configureJvmToolchain
import com.threemdroid.habittracker.buildlogic.libsCatalog

plugins {
    id("org.jetbrains.kotlin.jvm")
}

val libs = project.libsCatalog()

project.configureJvmToolchain()

dependencies {
    add("testImplementation", libs.findLibrary("junit").get())
}
