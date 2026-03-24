plugins {
    id("habittracker.android.application.compose")
}

apply(plugin = "habittracker.hilt")

android {
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":data:activity"))
    implementation(project(":data:habits"))
    implementation(project(":domain:activity"))
    implementation(project(":domain:habits"))
    implementation(project(":feature:activity"))
    implementation(project(":feature:create_habit"))
    implementation(project(":feature:home"))
    implementation(project(":feature:learn"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:settings"))

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.room.runtime)
}
