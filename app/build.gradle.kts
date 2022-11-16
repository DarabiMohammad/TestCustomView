import com.darabi.bardia.buildsrc.Application
import com.darabi.bardia.buildsrc.Dependencies.dependencies

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = Application.ID

    defaultConfig {

        applicationId = Application.ID
        minSdk = Application.MIN_SDK_VERSION
        compileSdk = Application.TARGET_SDK_VERSION
        versionCode = Application.VERSION_CODE
        versionName = Application.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = Application.JVM_VERSION
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    dependencies()
}