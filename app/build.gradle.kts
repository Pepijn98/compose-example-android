plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Versions.targetSdk)

    defaultConfig {
        applicationId = "dev.vdbroek.pepijn98"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
        kotlinCompilerVersion = Versions.kotlin
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.preference)
    implementation(Dependencies.AndroidX.lifecycle)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.layout)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.router)

    implementation(Dependencies.Google.material)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.AndroidX.junitExt)
    androidTestImplementation(Dependencies.Test.AndroidX.Espresso.core)
}
