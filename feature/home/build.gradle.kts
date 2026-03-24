plugins {
    id("habittracker.android.feature")
}

dependencies {
    api(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":domain:activity"))
    implementation(project(":domain:habits"))
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")

    testImplementation(project(":core:testing"))
}
