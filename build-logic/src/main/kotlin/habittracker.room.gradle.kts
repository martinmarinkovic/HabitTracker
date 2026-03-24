import com.threemdroid.habittracker.buildlogic.libsCatalog
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

plugins {
    id("org.jetbrains.kotlin.kapt")
}

val libs = project.libsCatalog()

extensions.configure<KaptExtension> {
    arguments {
        arg("room.schemaLocation", project.layout.projectDirectory.dir("schemas").asFile.path)
    }
}

dependencies {
    add("implementation", libs.findLibrary("androidx-room-ktx").get())
    add("implementation", libs.findLibrary("androidx-room-runtime").get())
    add("kapt", libs.findLibrary("androidx-room-compiler").get())
    add("testImplementation", libs.findLibrary("androidx-room-testing").get())
}
