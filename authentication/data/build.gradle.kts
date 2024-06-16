plugins {
    alias(libs.plugins.habitsapppro.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "es.rlujancreations.authentication.data"
}

dependencies {
    implementation(project(":authentication:domain"))

    // Dagger-Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    // Hilt
    implementation(libs.navigation.compose)
    ksp(libs.hilt.android.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}