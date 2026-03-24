plugins {
    id("habittracker.android.library")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))
    implementation(project(":domain:habits"))
}
