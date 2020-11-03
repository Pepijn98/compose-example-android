package dev.vdbroek.pepijn98

import android.app.Application
import dev.vdbroek.pepijn98.util.SplashScreenHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(SplashScreenHelper())
    }
}
