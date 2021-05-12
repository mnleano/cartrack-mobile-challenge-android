package com.neds.cartrackmobilechallenge.ui

import android.app.Application
import com.neds.cartrackmobilechallenge.BuildConfig
import com.neds.cartrackmobilechallenge.infrastructure.Lg
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(modules))
        }

        if (BuildConfig.DEBUG) {
            val boxStore: BoxStore by inject()
            val started = AndroidObjectBrowser(boxStore).start(this)
            Lg.i("ObjectBrowser", "Started: $started")
        }

        val fileLogger = Lg.FileLogCollector("Logs", BuildConfig.APPLICATION_ID, 3)
        Lg.addLogger(fileLogger)
        Lg.addDebugLogger()
    }
}