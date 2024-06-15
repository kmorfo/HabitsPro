plugins {
    `kotlin-dsl`
}
group = "es.rlujancreations.habitsapppro.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "habitsapppro.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}