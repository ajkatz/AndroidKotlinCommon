package com.akatz.platform.performance

import androidx.tracing.Trace
import com.akatz.platform.logging.logDebug
import com.akatz.platform.logging.logWarn

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
}