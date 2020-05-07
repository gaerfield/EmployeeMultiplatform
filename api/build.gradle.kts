import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

val ideaActive = System.getProperty("idea.active") == "true"

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

repositories {
    google()
    jcenter()
}

val kotlinVersion: String by extra

group="de.esag.employee.api"
version="1.0-SNAPSHOT"

android {
    compileSdkVersion(28)
    buildToolsVersion = "29.0.2"
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(28)
    }
    sourceSets {
        getByName("androidTest").setRoot("src/androidTest")
        getByName("debug").setRoot("src/androidDebug")
        getByName("main").setRoot("src/androidMain")
        getByName("test").setRoot("src/androidTest")
    }
}

val frameworkName = "EmployeeApi"

kotlin {
    jvm()
    js()
    android()

    val setFrameworkBasename = { target : KotlinNativeTarget ->
        target.binaries.framework { baseName = frameworkName }
    }
    iosArm32("iosArm32").also { setFrameworkBasename(it) }
    iosArm64("iosArm64").also { setFrameworkBasename(it) }
    iosX64("iosX64").also { setFrameworkBasename(it) }
    if (ideaActive) {
        iosX64("ios").also { setFrameworkBasename(it) }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
                api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

//                api("io.ktor:ktor-client-serialization-jvm:$ktor_version")
//                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization_version")
//                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
//                api("io.ktor:ktor-client-core-jvm:$ktor_version")
            }
        }

        val mobileMain by creating {
            dependsOn(commonMain)
        }

        val androidMain by getting {
            dependsOn(mobileMain)
            dependsOn(jvmMain)
        }

        val iosMain = if (ideaActive) getByName("iosMain") else create("iosMain")
        iosMain.apply {
            dependsOn(mobileMain)
            dependencies {
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutines_version")
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization_version")
//                implementation("io.ktor:ktor-client-ios:$ktor_version")
//                implementation("io.ktor:ktor-client-serialization-native:$ktor_version")
            }
        }

        val iosArm32Main by getting {}
        val iosArm64Main by getting {}
        val iosX64Main by getting {}

        configure(listOf(iosArm32Main, iosArm64Main, iosX64Main)) {
            dependsOn(iosMain)
        }
    }
}

tasks.register<FatFrameworkTask>("debugFatFramework") {
    baseName = frameworkName
    group = "Universal framework"
    description = "Builds a universal (fat) debug framework"

    from(kotlin.iosX64().binaries.getFramework("DEBUG"))
}

tasks.register<FatFrameworkTask>("releaseFatFramework") {
    baseName = frameworkName
    group = "Universal framework"
    description = "Builds a universal (release) debug framework"

    from(
        kotlin.iosArm64().binaries.getFramework("RELEASE"),
        kotlin.iosArm32().binaries.getFramework("RELEASE"))
}