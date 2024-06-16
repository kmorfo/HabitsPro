plugins {
    alias(libs.plugins.habitsapppro.android.feature.ui)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "es.rlujancreations.home.presentation"
}

dependencies {
    implementation(project(":home:domain"))

    // Permissions
    implementation(libs.accompanist.permissions)

    // Dagger-Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    // Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.navigation.compose)
    ksp(libs.hilt.android.compiler)

    // Compose Dialog
    implementation(libs.core)
    implementation(libs.clock)
}