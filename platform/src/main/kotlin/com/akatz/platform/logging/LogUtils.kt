package com.akatz.platform.logging

import android.util.Log

private const val tagPrefixDefault = "katz"

private fun log(
    level: (String, String) -> Int,
    message: String,
    tagPrefix: String = tagPrefixDefault,
    tagPostfix: String = ""
) {
    level("${tagPrefix}${tagPostfix}", message)
}

fun logError(
    message: String,
    tagPrefix: String = tagPrefixDefault,
    tagPostfix: String = ""
) {
    log(Log::e, message, tagPrefix, tagPostfix)
}

fun logWarn(
    message: String,
    tagPrefix: String = tagPrefixDefault,
    tagPostfix: String = ""
) {
    log(Log::w, message, tagPrefix, tagPostfix)
}

fun logInfo(
    message: String,
    tagPrefix: String = tagPrefixDefault,
    tagPostfix: String = ""
) {
    log(Log::i, message, tagPrefix, tagPostfix)
}

fun logDebug(
    message: String,
    tagPrefix: String = tagPrefixDefault,
    tagPostfix: String = ""
) {
    log(Log::d, message, tagPrefix, tagPostfix)
}

fun logVerbose(
    message: String,
    tagPrefix: String = tagPrefixDefault,
    tagPostfix: String = ""
) {
    log(Log::v, message, tagPrefix, tagPostfix)
}
