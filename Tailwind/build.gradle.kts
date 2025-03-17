plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.gradle.plugin)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

dependencies {
    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.foundation)
}

gradlePlugin {
    website = "https://github.com/plelouch7/tailwind-theme-android"
    vcsUrl = "https://github.com/plelouch7/tailwind-theme-android.git"
    plugins {
        create("tailwind-theme-android") {
            id = "com.verimsolution.tailwind"
            displayName = "TailwindCSS Theme Plugin for Mobile"
            description = "A plugin to integrate TailwindCSS themes into mobile projects."
            tags = listOf("tailwind", "theme", "mobile", "android")
            implementationClass = "com.verimsolution.tailwind.TailwindTheme"
        }
    }
}
