package es.rlujancreations.convention

/**
 * Created by Raúl L.C. on 16/6/24.
 */
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project


fun DependencyHandlerScope.addUiLayerDependencies(project: Project) {
    "implementation"(project(":core:presentation"))

//    "implementation"(project.libs.findBundle("hilt.compose").get())
    "implementation"(project.libs.findBundle("compose").get())
    "debugImplementation"(project.libs.findBundle("compose.debug").get())
    "androidTestImplementation"(project.libs.findLibrary("androidx.compose.ui.test.junit4").get())
}