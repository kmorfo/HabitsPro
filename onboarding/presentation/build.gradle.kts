plugins {
    alias(libs.plugins.habitsapppro.android.feature.ui)
    alias(libs.plugins.habitsapppro.android.hilt.navigation)
}

android {
    namespace = "es.rlujancreations.onboarding.presentation"
}

dependencies {
    implementation(project(":onboarding:domain"))
}