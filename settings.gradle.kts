pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HabitTracker"
include(":app")
include(":core:common")
include(":core:model")
include(":core:ui")
include(":core:designsystem")
include(":core:database")
include(":core:network")
include(":core:navigation")
include(":core:testing")
include(":data:habits")
include(":data:activity")
include(":data:learn")
include(":data:profile")
include(":data:settings")
include(":domain:habits")
include(":domain:activity")
include(":domain:learn")
include(":domain:profile")
include(":domain:settings")
include(":feature:home")
include(":feature:create_habit")
include(":feature:activity")
include(":feature:learn")
include(":feature:profile")
include(":feature:settings")
 
