rootProject.name = "Employee"

pluginManagement {
    val kotlinVersion : String by settings
    val gradleAndroidVersion: String by settings
    val springBootVersion : String by settings
    val springDependencyManagementVersion : String by settings
    val googleJibVersion : String by settings

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    resolutionStrategy {
        val androidPlugin = "com.android.tools.build:gradle"
        eachPlugin {
            when(requested.id.id) {
                "com.android.library" -> useModule("$androidPlugin:$gradleAndroidVersion")
            }
        }
    }

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        kotlin("kapt") version kotlinVersion apply false
        kotlin("multiplatform") version kotlinVersion apply false
        kotlin("plugin.spring") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("com.android.library") version gradleAndroidVersion apply false
        id("org.springframework.boot") version springBootVersion apply false
        id("io.spring.dependency-management") version springDependencyManagementVersion apply false
        id("com.google.cloud.tools.jib") version googleJibVersion apply false
    }
}

include("api", "server")

