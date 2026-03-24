plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.hilt.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation("com.google.dagger.hilt.android:com.google.dagger.hilt.android.gradle.plugin:${libs.versions.hilt.get()}")
    implementation("org.jetbrains.kotlin.android:org.jetbrains.kotlin.android.gradle.plugin:${libs.versions.kotlin.get()}")
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:${libs.versions.kotlin.get()}")
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:${libs.versions.kotlin.get()}")
    implementation("org.jetbrains.kotlin.plugin.compose:org.jetbrains.kotlin.plugin.compose.gradle.plugin:${libs.versions.kotlin.get()}")
    implementation("org.jetbrains.kotlin.plugin.serialization:org.jetbrains.kotlin.plugin.serialization.gradle.plugin:${libs.versions.kotlin.get()}")
}
