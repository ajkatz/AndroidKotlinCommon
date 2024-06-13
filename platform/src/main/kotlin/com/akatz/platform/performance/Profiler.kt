package com.akatz.platform.performance

import android.annotation.SuppressLint
import androidx.tracing.Trace
import com.akatz.platform.logging.logDebug
import com.akatz.platform.logging.logWarn

object Profiler {
    private var start = 0L
    private var threshold: Long? = null
    private var label = ""

    @SuppressLint("UnclosedTrace")
    fun beginSection(
        label: String,
        thresholdMs: Long? = null,
    ) {
        start = System.currentTimeMillis()
        threshold = thresholdMs
        this.label = label
        Trace.beginSection(label)
    }

    fun endSection() {
        Trace.endSection()
        val end = System.currentTimeMillis()
        logTraceResult(start, end, threshold, label)
    }
}

fun runProfiled(
    label: String,
    thresholdMs: Long? = null,
    block: () -> Unit)
{
    val start = System.currentTimeMillis()
    Trace.beginSection(label)
    try {
        block()
    } finally {
        Trace.endSection()
        val end = System.currentTimeMillis()
        logTraceResult(start, end, thresholdMs, label)
    }
}



internal fun logTraceResult(
    start: Long,
    end: Long,
    thresholdMs: Long?,
    label: String
) {
    val timeMs = end - start
    if (thresholdMs != null && timeMs >= thresholdMs) {
        logWarn(
            "$label took $timeMs ms (expected: ${thresholdMs}ms",
            tagPostfix = "_profiler"
        )
    } else {
        logDebug(
            "${label} took ${end - start} ms",
            tagPostfix = "_profiler"
        )
    }
}
