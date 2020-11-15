package com.adson.programmatic

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.StrictMode
import androidx.multidex.MultiDex
import com.bugfender.sdk.Bugfender
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class App : Application() {

    companion object {
        // Prevent need in a singleton (global) reference to the application object.
        operator fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        JodaTimeAndroid.init(this)
        setupDebugMode()
        configureBugfenderLogger()

        super.onCreate()
        Timber.i("\n***** ${getString(R.string.app_name)} App build version v.${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE}) *****\n")

        GlobalExceptionHandler.init(this)
    }

    private fun setupDebugMode() {
        if (!BuildConfig.DEBUG_MODE) {
            return
        }
        configureStrictMode()
        Timber.plant(Timber.DebugTree())
    }

    private fun configureStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())
    }

    private fun configureBugfenderLogger() {
        if (!Build.FINGERPRINT.contains("generic")) {
            Bugfender.init(this, BuildConfig.API_KEY_BUGFENDER, false)
            Bugfender.enableCrashReporting()
            Bugfender.enableLogcatLogging()
        }
    }
}