plugins {
    alias(libs.plugins.habitsapppro.android.feature.ui)
    alias(libs.plugins.habitsapppro.android.hilt.navigation)
}

android {
    namespace = "es.rlujancreations.authentication.presentation"
}

dependencies {
    implementation(project(":authentication:domain"))
}