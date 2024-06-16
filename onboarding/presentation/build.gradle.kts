plugins {
    alias(libs.plugins.habitsapppro.android.feature.ui)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "es.rlujancreations.onboarding.presentation"
}

dependencies {

    implementation(project(":onboarding:domain"))

    // Dagger-Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    // Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.navigation.compose)
    ksp(libs.hilt.android.compiler)

}