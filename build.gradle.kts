import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }

    dependencies {
        classpath(Dependencies.android_gradle_plugin)
        classpath(Dependencies.Kotlin.gradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }

    // Enable @OptIn annotation
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()

            freeCompilerArgs += listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xallow-jvm-ir-dependencies",
                "-Xskip-prerelease-check"
            )
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
