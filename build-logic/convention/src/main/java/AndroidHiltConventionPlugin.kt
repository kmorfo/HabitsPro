import es.rlujancreations.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Created by Raúl L.C. on 17/6/24.
 */

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }

            dependencies {
                add("implementation", libs.findLibrary("dagger.hilt.android").get())
                add("ksp", libs.findLibrary("dagger.hilt.compiler").get())
                add("ksp", libs.findLibrary("hilt.android.compiler").get())
            }
        }
    }
}
