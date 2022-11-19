import com.darabi.bardia.buildsrc.Application
import com.darabi.bardia.buildsrc.Dependencies.customButtonDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {

    namespace = Application.ID

    defaultConfig {

        minSdk = Application.MIN_SDK_VERSION
        compileSdk = Application.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Application.JVM_VERSION
    }
}

dependencies {

    customButtonDependencies()
}