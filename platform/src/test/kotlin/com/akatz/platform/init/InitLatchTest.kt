package com.akatz.platform.init

import kotlin.test.Test
import kotlin.test.assertEquals

class InitLatchTest {

    @Test
    fun `single use does not throw`() {
        val latch = InitLatch()
        latch.trigger()
    }

    @Test
    fun `ignore after first run does not throw`() {
        val latch = InitLatch()
        repeat(100) {
            latch.ignoreAfterFirstRun { }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `double use throws`() {
        val latch = InitLatch()
        latch.trigger()
        latch.trigger()
    }

    @Test
    fun `ignores after first run`() {
        var counter = 0
        val latch = InitLatch()
        latch.ignoreAfterFirstRun { counter++ }
        latch.ignoreAfterFirstRun { counter++ }
        assertEquals(1, counter)
    }

    @Test
    fun `ignores after first run after trigger`() {
        var counter = 0
        val latch = InitLatch()
        latch.trigger()
        latch.ignoreAfterFirstRun { counter++ }
        assertEquals(0, counter)
    }

    @Test(expected = IllegalStateException::class)
    fun `double use throws after ignoreAfterFirstRun`() {
        val latch = InitLatch()
        latch.ignoreAfterFirstRun { }
        latch.trigger()
    }
}