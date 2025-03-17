plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.gradle.plugin)
    alias(libs.plugins.compose.compiler)
}

group = "com.verimsolution.tailwind"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

dependencies {
    implementation(libs.androidx.ui)
    implementation(libs.androidx.compose.foundation)
    // https://mvnrepository.com/artifact/androidx.compose.material/material-ripple
    implementation(libs.androidx.material.ripple)
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


