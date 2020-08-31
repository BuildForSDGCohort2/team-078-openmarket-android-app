package com.buildforsdg.openmarket.app

import android.app.Application
import com.buildforsdg.openmarket.BuildConfig
import timber.log.Timber

/**
 * @author by Lawrence on 8/31/20.
 * for OpenMarket
 */
class OpenMarketApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}