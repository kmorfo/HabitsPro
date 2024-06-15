/**
 * Created by Raúl L.C. on 15/6/24.
 */
import com.android.build.api.dsl.ApplicationExtension
import es.rlujancreations.convention.ExtensionType
import es.rlujancreations.convention.configureBuildTypes
import es.rlujancreations.convention.configureKotlinAndroid
import es.rlujancreations.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()

                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    compileSdk = libs.findVersion("projectCompileSdkVersion").get().toString().toInt()
                    minSdk = libs.findVersion("projectMinSdkVersion").get().toString().toInt()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }
        }
    }
}