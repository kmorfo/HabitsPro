plugins {
    alias(libs.plugins.habitsapppro.android.library)
    alias(libs.plugins.habitsapppro.android.hilt)
}

android {
    namespace = "es.rlujancreations.authentication.data"
}

dependencies {
    implementation(project(":authentication:domain"))

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}