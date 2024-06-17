plugins {
    alias(libs.plugins.habitsapppro.android.library)
    alias(libs.plugins.habitsapppro.android.hilt)
}

android {
    namespace = "es.rlujancreations.onboarding.data"
}

dependencies {
    implementation(project(":onboarding:domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}