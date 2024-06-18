import es.rlujancreations.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Created by Raúl L.C. on 17/6/24.
 */
class JvmRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findLibrary("retrofit").get())
                add("implementation", libs.findLibrary("converter.moshi").get())
                add("implementation", libs.findLibrary("logging.interceptor").get())
            }
        }
    }
}
