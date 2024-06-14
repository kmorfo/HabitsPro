pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HabitsAppPro"
include(":app")
include(":core")
include(":core:data")
include(":core:presentation")
include(":authentication")
include(":authentication:domain")
include(":authentication:presentation")
include(":authentication:data")
include(":onboarding")
include(":onboarding:domain")
include(":onboarding:data")
include(":onboarding:presentation")
include(":settings")
include(":settings:presentation")
include(":home")
include(":home:domain")
include(":home:data")
include(":home:presentation")
