package com.track.finance2up

import android.app.Application
import com.orhanobut.hawk.BuildConfig
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Finance2UpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupHawk()
        setupTimber()
    }

    private fun setupHawk() {
        Hawk.init(applicationContext).build()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}