package dev.vdbroek.pepijn98.utils

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import dev.vdbroek.pepijn98.R

class SplashScreenHelper : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        try {
            val activityInfo = activity.packageManager.getActivityInfo(
                activity.componentName,
                PackageManager.GET_META_DATA
            )
            val metaData = activityInfo.metaData
            val theme = metaData?.getInt("theme", R.style.Theme_Pepijn98_NoActionBar) ?: R.style.Theme_Pepijn98_NoActionBar
            activity.setTheme(theme)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun onActivityStarted(activity: Activity) {
        // do nothing
    }

    override fun onActivityResumed(activity: Activity) {
        // do nothing
    }

    override fun onActivityPaused(activity: Activity) {
        // do nothing
    }

    override fun onActivityStopped(activity: Activity) {
        // do nothing
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // do nothing
    }

    override fun onActivityDestroyed(activity: Activity) {
        // do nothing
    }
}
