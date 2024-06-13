package com.akatz.platform.init

class InitLatch {
    var inititalized = false

    fun trigger() {
        if (inititalized) throw IllegalStateException("Already initialized")
        inititalized = true
    }

    fun ignoreAfterFirstRun(block: () -> Unit) {
        if (inititalized) return
        inititalized = true
        block()
    }
}