package es.rlujancreations.convention

/**
 * Created by Raúl L.C. on 15/6/24.
 */

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        buildFeatures {
            compose = true
        }

        //It's not necessary with kotlin 2.0
//        composeOptions {
//            kotlinCompilerExtensionVersion = libs
//                .findVersion("composeCompiler")
//                .get()
//                .toString()
//        }

        dependencies {
            val bom = libs.findLibrary("androidx.compose.bom").get()
            "implementation"(platform(bom))
            "androidTestImplementation"(platform(bom))
            "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
        }
    }
}