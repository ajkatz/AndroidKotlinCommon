package com.akatz.testenv

import android.app.Application
import com.akatz.platform.performance.AppStartProfiler

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppStartProfiler.start()
    }
}