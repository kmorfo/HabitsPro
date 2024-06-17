plugins {
    `kotlin-dsl`
}
group = "es.rlujancreations.habitsapppro.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "habitsapppro.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "habitsapppro.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "habitsapppro.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "habitsapppro.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "habitsapppro.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidRoomConventionPlugin") {
            id = "habitsapppro.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidRetrofitConventionPlugin") {
            id = "habitsapppro.android.retrofit"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }
        register("jvmLibrary") {
            id = "habitsapppro.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidHiltConventionPlugin") {
            id = "habitsapppro.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidHiltNavigationConventionPlugin") {
            id = "habitsapppro.android.hilt.navigation"
            implementationClass = "AndroidHiltNavigationConventionPlugin"
        }
    }
}