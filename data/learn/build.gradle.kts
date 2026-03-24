plugins {
    id("habittracker.android.library")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":domain:learn"))
}
