plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

val compose_version = "1.0.0-alpha05"
val kotlin_version = "1.4.10"

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "dev.vdbroek.pepijn98"
        minSdkVersion(24)
        targetSdkVersion(30)
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
        kotlinCompilerExtensionVersion = compose_version
        kotlinCompilerVersion = kotlin_version
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.google.android.material:material:1.2.1")

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-beta01")

    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.foundation:foundation-layout:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.foundation:foundation:$compose_version")
    implementation("androidx.ui:ui-tooling:$compose_version")

    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}