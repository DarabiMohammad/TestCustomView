package com.darabi.bardia.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Application {

    // Main Configs
    const val ID = "com.darabi.custombutton"
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val JVM_VERSION = "1.8"
}

object Dependencies {

    private const val KAPT = "kapt"
    private const val IMPLEMENTATION = "implementation"
    private const val TEST_IMPLEMENTATION = "testImplementation"
    private const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"

    private const val CORE_VERSION = "1.9.0"
    private const val APPCOMPAT_VERSION = "1.5.1"
    private const val COROUTINES_VERSION = "1.4.2"
    private const val CONSTRAINT_VERSION = "2.1.4"
    private const val MATERIAL_VERSION = "1.7.0"
    private const val LIFECYCLE_VERSION = "2.2.0"
    private const val HILT_VERSION = "2.44"

    // test versions
    private const val JUNIT_VERSION = "4.13.2"
    private const val ANDROID_JUNIT_VERSION = "1.1.3"
    private const val ESPRESSO_VERSION = "3.4.0"

    private val libs = listOf (

        // core
        "androidx.core:core-ktx:${CORE_VERSION}",
        "androidx.appcompat:appcompat:${APPCOMPAT_VERSION}",
        "androidx.activity:activity-ktx:${APPCOMPAT_VERSION}",
        "androidx.fragment:fragment-ktx:${APPCOMPAT_VERSION}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${COROUTINES_VERSION}",

        // di
        "com.google.dagger:hilt-android:${HILT_VERSION}",

        // design
        "androidx.constraintlayout:constraintlayout:${CONSTRAINT_VERSION}",
        "com.google.android.material:material:${MATERIAL_VERSION}",

        // lifeccyle
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LIFECYCLE_VERSION}",
        "androidx.lifecycle:lifecycle-extensions:${LIFECYCLE_VERSION}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${LIFECYCLE_VERSION}",
    )

    private val processors = listOf (
        // di
        "com.google.dagger:hilt-android-compiler:$HILT_VERSION",
    )

    private val testLibs = listOf (
        "junit:junit:${JUNIT_VERSION}",
    )

    private val androidTestLibs = listOf (
        "androidx.test.ext:junit:${ANDROID_JUNIT_VERSION}",
        "androidx.test.espresso:espresso-core:${ESPRESSO_VERSION}",
    )

    private val customButtonLibs = listOf(

        // core
        "androidx.core:core-ktx:${CORE_VERSION}",
        "androidx.appcompat:appcompat:${APPCOMPAT_VERSION}",

        // design
        "com.google.android.material:material:${MATERIAL_VERSION}",
    )

    private val customButtonTestLibs = listOf(
        "junit:junit:${JUNIT_VERSION}",
    )

    private val customButtonAndroidTestLibs = listOf(
        "androidx.test.ext:junit:${ANDROID_JUNIT_VERSION}",
        "androidx.test.espresso:espresso-core:${ESPRESSO_VERSION}",
    )

    fun DependencyHandler.dependencies() {

        add(IMPLEMENTATION, project(":customButton"))

        libs.forEach { dependency ->
            add(IMPLEMENTATION, dependency)
        }

        processors.forEach { processor ->
            add(KAPT, processor)
        }

        testLibs.forEach { testLib ->
            add(TEST_IMPLEMENTATION, testLib)
        }

        androidTestLibs.forEach { androidTestLib ->
            add(ANDROID_TEST_IMPLEMENTATION, androidTestLib)
        }
    }

    fun DependencyHandler.customButtonDependencies() {

        customButtonLibs.forEach { dependency ->
            add(IMPLEMENTATION, dependency)
        }

        customButtonTestLibs.forEach { testLib ->
            add(TEST_IMPLEMENTATION, testLib)
        }

        customButtonAndroidTestLibs.forEach { androidTestLib ->
            add(ANDROID_TEST_IMPLEMENTATION, androidTestLib)
        }
    }
}