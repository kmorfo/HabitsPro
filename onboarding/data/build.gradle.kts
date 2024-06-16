plugins {
    alias(libs.plugins.habitsapppro.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "es.rlujancreations.onboarding.data"
}

dependencies {
    implementation(project(":onboarding:domain"))

    // Dagger-Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    // Hilt
    implementation(libs.navigation.compose)
    ksp(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}