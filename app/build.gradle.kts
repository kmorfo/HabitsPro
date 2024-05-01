plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "es.rlujancreations.habitsapppro"
    compileSdk = 34

    defaultConfig {
        applicationId = "es.rlujancreations.habitsapppro"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//    constraints {
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
//            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
//        }
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
//            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
//        }
//    }

    //it is necessary for minusDays and enable it in compileOptions
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    val timePicker = "1.1.0"
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:$timePicker")
    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:$timePicker")

    val composeVersion = "1.6.6"
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material3:material3:1.2.1")

    // Compose Navigation
    implementation("com.google.android.gms:play-services-auth:21.1.0")

    // Dagger Hilt
    val hiltVersion = "2.49"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    //Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-auth")
    //Firebase google auth
    implementation("com.google.android.gms:play-services-auth:21.1.0")
    //Firebase Analytics
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")

    // Pager
    val accompanistVersion = "0.28.0"
//    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
//    implementation("com.google.accompanist:accompanist-pager-indicators:$accompanistVersion")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:$accompanistVersion")

    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // WorkManager
    val workmanagerVersion = "2.9.0"
    implementation("androidx.work:work-runtime-ktx:$workmanagerVersion")
    implementation("androidx.hilt:hilt-work:1.2.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$hiltVersion")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    testImplementation("app.cash.turbine:turbine:0.7.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    val mockkVersion = "1.13.4"
    testImplementation("io.mockk:mockk:$mockkVersion")
    androidTestImplementation("androidx.work:work-testing:$workmanagerVersion")
}