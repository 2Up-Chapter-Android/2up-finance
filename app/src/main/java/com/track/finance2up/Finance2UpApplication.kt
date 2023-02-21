package com.track.finance2up

import android.app.Application
import com.google.android.datatransport.BuildConfig
import timber.log.Timber

class Finance2UpApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}