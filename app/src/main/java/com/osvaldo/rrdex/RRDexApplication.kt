package com.osvaldo.rrdex

import android.app.Application
import com.osvaldo.rrdex.core.myAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RRDexApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@RRDexApplication)
            // Load modules
            modules(myAppModules)
        }
    }

}