package com.adson.programmatic

import android.content.Context
import android.util.Log
import timber.log.Timber

internal class GlobalExceptionHandler private constructor(private val mContext: Context) : Thread.UncaughtExceptionHandler {

    companion object {
        fun init(context: Context): GlobalExceptionHandler {
            return GlobalExceptionHandler(context)
        }
    }

    private val mRootHandler: Thread.UncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        mRootHandler.uncaughtException(thread, ex)

        Timber.log(Log.ERROR, GlobalExceptionHandler::class.java.simpleName, "Global App Crash")
        Timber.e(ex)
    }
}