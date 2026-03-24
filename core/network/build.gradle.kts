plugins {
    id("habittracker.kotlin.jvm")
    id("habittracker.kotlin.serialization")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization)
}
