plugins {
    alias(libs.plugins.habitsapppro.android.feature.ui)
    alias(libs.plugins.habitsapppro.android.hilt.navigation)
}

android {
    namespace = "es.rlujancreations.home.presentation"
}

dependencies {
    implementation(project(":home:domain"))

    // Permissions
    implementation(libs.accompanist.permissions)

    // Compose Dialog
    implementation(libs.core)
    implementation(libs.clock)
}