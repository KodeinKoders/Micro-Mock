plugins {
    kodein.gradlePlugin
    `kotlin-dsl`
    alias(libs.plugins.buildConfig)
}

dependencies {
    implementation(libs.ksp.gradlePlugin)
    implementation(kodeinGlobals.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins.register("mockmp") {
        id = "org.kodein.mock.mockmp"
        implementationClass = "org.kodein.mock.gradle.MocKMPGradlePlugin"
        displayName = "MocKMP"
        description = "Applies the MocKMP symbol processor to a Kotlin/Multiplatform project"
        @Suppress("UnstableApiUsage")
        tags.set(listOf("kotlin", "mock", "test"))
    }
}

buildConfig {
    packageName("org.kodein.mock.gradle")
    buildConfigField("String", "VERSION", "\"${project.version}\"")
}

kotlin.target.compilations.all {
    compileTaskProvider.configure {
        compilerOptions.freeCompilerArgs.add("-Xsuppress-version-warnings")
    }
}

kodeinUpload {
    name = "mockmp-gradle-plugin"
    description = "MocKMP Gradle Plugin"
}
