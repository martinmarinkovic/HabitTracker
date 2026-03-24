plugins {
    id("habittracker.android.library.compose")
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation(libs.coil.compose)
}
