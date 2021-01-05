@file:JvmName("Deps")

object Versions {
    const val compose = "1.0.0-alpha09"
    const val kotlin = "1.4.21"
    const val targetSdk = 30
    const val minSdk = 24
}

object Dependencies {
    const val android_gradle_plugin = "com.android.tools.build:gradle:7.0.0-alpha03"

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.3.2"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val preference = "androidx.preference:preference-ktx:1.1.1"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-beta01"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"

        const val router = "com.github.zsoltk:compose-router:0.23.0"
    }

    object Kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    object Google {
        const val material = "com.google.android.material:material:1.2.1"
    }

    object Test {
        object AndroidX {
            const val junitExt = "androidx.test.ext:junit:1.1.2"

            object Espresso {
                const val core = "androidx.test.espresso:espresso-core:3.2.0"
            }
        }

        const val junit = "junit:junit:4.13"
    }
}
