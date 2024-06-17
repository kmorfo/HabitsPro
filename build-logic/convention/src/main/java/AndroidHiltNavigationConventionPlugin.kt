import es.rlujancreations.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Created by Raúl L.C. on 17/6/24.
 */

class AndroidHiltNavigationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("habitsapppro.android.hilt")
            }

            dependencies {
                "implementation"(
                    libs.findLibrary("androidx.hilt.navigation.compose").get()
                )
            }
        }
    }
}