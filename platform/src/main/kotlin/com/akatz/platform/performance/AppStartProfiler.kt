package com.akatz.platform.performance

import com.akatz.platform.init.InitLatch
import kotlin.properties.Delegates

object AppStartProfiler {
    val startInitLatch = InitLatch()
    val pageLoadInitLatch = InitLatch()
    val endInitLatch = InitLatch()
    var start by Delegates.notNull<Long>()
    var pageLoad by Delegates.notNull<Long>()

    fun start() {
        startInitLatch.trigger()
        start = System.currentTimeMillis()
    }

    fun pageLoad() = pageLoadInitLatch.ignoreAfterFirstRun {
        if (!startInitLatch.inititalized) return@ignoreAfterFirstRun
        pageLoad = System.currentTimeMillis()
        logTraceResult(start, pageLoad, thresholdMs = 200L, "AppStart_PageLoad")
    }

    fun end() = endInitLatch.ignoreAfterFirstRun {
        if (!startInitLatch.inititalized) return@ignoreAfterFirstRun
        logTraceResult(
            start,
            System.currentTimeMillis(),
            thresholdMs = 200L,
            "AppStart_Complete"
        )
    }
}