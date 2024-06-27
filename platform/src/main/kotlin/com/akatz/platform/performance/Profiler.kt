package com.akatz.platform.performance

import android.annotation.SuppressLint
import androidx.tracing.Trace
import com.akatz.platform.logging.logDebug
import com.akatz.platform.logging.logWarn
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.measureTime
import kotlin.time.toDuration

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
    val duration = measureTime {
        Trace.beginSection(label)
        block()
        Trace.endSection()
    }
    logTraceResult(duration, thresholdMs, label)
}



internal fun logTraceResult(
    start: Long,
    end: Long,
    thresholdMs: Long?,
    label: String
) {
    logTraceResult(
        createDurationMs(start, end),
        thresholdMs,
        label)
}

internal fun logTraceResult(
    durationMs: Duration,
    thresholdMs: Long?,
    label: String
) {
    if (thresholdMs != null && durationMs.inWholeMilliseconds >= thresholdMs) {
        logWarn(
            "$label took ${durationMs.inWholeMilliseconds} ms (expected: ${thresholdMs}ms",
            tagPostfix = "_profiler"
        )
    } else {
        logDebug(
            "$label took ${durationMs.inWholeMilliseconds} ms",
            tagPostfix = "_profiler"
        )
    }
}

internal fun createDurationMs(start: Long, end: Long): Duration {
    val durationMs = end - start
    return durationMs.toDuration(DurationUnit.MILLISECONDS)
}