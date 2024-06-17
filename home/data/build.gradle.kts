plugins {
    alias(libs.plugins.habitsapppro.android.library)
    alias(libs.plugins.habitsapppro.android.room)
    alias(libs.plugins.habitsapppro.android.hilt)
}

android {
    namespace = "es.rlujancreations.home.data"
}

dependencies {
    implementation(project(":home:domain"))
    implementation(libs.room.ktx)

    //Get day of week api 25 or lower
//    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}