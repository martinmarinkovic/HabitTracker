plugins {
    id("habittracker.android.library")
    id("habittracker.room")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}
