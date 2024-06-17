plugins {
    alias(libs.plugins.google.services)
    alias(libs.plugins.habitsapppro.android.application.compose)
    alias(libs.plugins.habitsapppro.android.hilt.navigation)
    alias(libs.plugins.habitsapppro.android.retrofit)
}

android {
    namespace = "es.rlujancreations.habitsapppro"
    compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()

    defaultConfig {
        testInstrumentationRunner = "es.rlujancreations.habitsapppro.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":settings:presentation"))
    implementation(project(":core:data"))
    implementation(project(":core:presentation"))
    implementation(project(":onboarding:data"))
    implementation(project(":onboarding:domain"))
    implementation(project(":onboarding:presentation"))
    implementation(project(":authentication:data"))
    implementation(project(":authentication:domain"))
    implementation(project(":authentication:presentation"))
    implementation(project(":home:domain"))
    implementation(project(":home:presentation"))
    implementation(project(":home:data"))

    //it is necessary for minusDays and enable it in compileOptions
    //Get day of week api 25 or lower
//    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.core)
    implementation(libs.clock)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.auth)

    // Coil
    implementation(libs.coil.compose)

    // Permissions
    implementation(libs.accompanist.permissions)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.work.testing)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}