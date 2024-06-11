package com.akatz.colors

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertEquals

const val strictFloatTolerance = 0.00001f
const val floatTolerance = 0.001f
const val looseFloatTolerance = 0.01f

fun Color.assertEquals(expected: Color) {
    this.red.assertEquals(expected.red)
    this.green.assertEquals(expected.green)
    this.blue.assertEquals(expected.blue)
    this.alpha.assertEquals(expected.alpha)
}

fun Float.assertEquals(expected: Float, tolerance: FloatTolerance = FloatTolerance.STANDARD) {
    val floatTolerance = when (tolerance) {
        FloatTolerance.STANDARD -> floatTolerance
        FloatTolerance.STRICT -> strictFloatTolerance
        FloatTolerance.LOOSE -> looseFloatTolerance
    }
    assertEquals(expected, this, floatTolerance)
}

enum class FloatTolerance {
    STANDARD,
    STRICT,
    LOOSE
}

class TestUtils {
    @Test
    fun `test primary colors pass assertions from floats`() {
        Color(1.0f, 0.0f, 0.0f).assertEquals(Color.Red)
        Color(0.0f, 1.0f, 0.0f).assertEquals(Color.Green)
        Color(0.0f, 0.0f, 1.0f).assertEquals(Color.Blue)
    }

    @Test
    fun `test primary colors pass assertions from ints`() {
        Color(255, 0, 0, 255).assertEquals(Color.Red)
        Color(0, 255, 0, 255).assertEquals(Color.Green)
        Color(0, 0, 255, 255).assertEquals(Color.Blue)
    }
}