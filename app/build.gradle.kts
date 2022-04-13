plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Versions.targetSdk

    defaultConfig {
        applicationId = "dev.vdbroek.pepijn98"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false
        }

        getByName("debug") {
            versionNameSuffix = "-DEBUG"
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
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
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.router)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.activity_ktx)

    implementation(Dependencies.Google.material)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.AndroidX.junitExt)
    androidTestImplementation(Dependencies.Test.AndroidX.Espresso.core)
}
