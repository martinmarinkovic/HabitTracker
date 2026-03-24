plugins {
    id("habittracker.android.feature")
}

apply(plugin = "habittracker.hilt")

dependencies {
    implementation(project(":core:designsystem"))
    api(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":domain:habits"))
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    testImplementation(project(":core:testing"))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
