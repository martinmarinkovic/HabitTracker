plugins {
    id("habittracker.android.library")
    id("habittracker.kotlin.serialization")
}

apply(plugin = "habittracker.hilt")

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":domain:learn"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.core)
}
