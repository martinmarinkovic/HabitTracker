plugins {
    id("habittracker.android.feature")
}

dependencies {
    api(project(":core:navigation"))
    implementation(project(":core:ui"))
}
