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
        Hawk.init(applicationContext).build()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}