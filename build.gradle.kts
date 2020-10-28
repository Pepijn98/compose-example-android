import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
        // maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        // maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }

    // Enable @OptIn annotation
    tasks.withType(KotlinCompile::class).all {
        kotlinOptions {
            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}