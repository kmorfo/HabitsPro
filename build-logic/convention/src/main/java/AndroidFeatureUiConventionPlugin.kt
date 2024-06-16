/**
 * Created by Raúl L.C. on 16/6/24.
 */

import es.rlujancreations.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("habitsapppro.android.library.compose")
            }

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}