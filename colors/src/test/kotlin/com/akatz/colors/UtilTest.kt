package com.akatz.colors

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertEquals

class UtilTest {

    @Test
    fun `single gradient minimum output size`() {
        val startColor = Color.Red
        val endColor = Color.Blue

        for (i in -1..2) {
            assertEquals(2, colorGradient(startColor, endColor, i).size)
        }
    }

    @Test
    fun `single gradient expected output size`() {
        val startColor = Color.Red
        val endColor = Color.Blue

        repeat(100) { i ->
            if (i < 2) return@repeat
            assertEquals(i, colorGradient(startColor, endColor, i).size)
        }
    }

    @Test
    fun `single gradient start and end values match`() {
        val startColor = Color.Red
        val endColor = Color.Blue

        repeat(100) { i ->
            if (i < 2) return@repeat
            val gradient = colorGradient(startColor, endColor, i)
            gradient[0].assertEquals(startColor)
            gradient[gradient.lastIndex].assertEquals(endColor)
        }
    }

    @Test
    fun `single gradient step sizes match`() {
        val startColor = Color.Red
        val endColor = Color.Blue

        repeat(100) { i ->
            if (i < 3) return@repeat
            val gradient = colorGradient(startColor, endColor, i)

            val redStep: Float = gradient[1].red - gradient[0].red
            val greenStep: Float = gradient[1].green - gradient[0].green
            val blueStep: Float = gradient[1].blue - gradient[0].blue

            val redStepNext: Float = gradient[2].red - gradient[1].red
            val greenStepNext: Float = gradient[2].green - gradient[1].green
            val blueStepNext: Float = gradient[2].blue - gradient[1].blue

            val redStepFinal: Float = gradient[gradient.lastIndex].red - gradient[gradient.lastIndex - 1].red
            val greenStepFinal: Float = gradient[gradient.lastIndex].green - gradient[gradient.lastIndex - 1].green
            val blueStepFinal: Float = gradient[gradient.lastIndex].blue - gradient[gradient.lastIndex - 1].blue

            redStepNext.assertEquals(redStep, FloatTolerance.LOOSE)
            blueStepNext.assertEquals(blueStep, FloatTolerance.LOOSE)
            greenStepNext.assertEquals(greenStep, FloatTolerance.LOOSE)

            redStepFinal.assertEquals(redStep, FloatTolerance.LOOSE)
            blueStepFinal.assertEquals(blueStep, FloatTolerance.LOOSE)
            greenStepFinal.assertEquals(greenStep, FloatTolerance.LOOSE)
        }
    }

    @Test
    fun `single gradient monotone match`() {
        val color = Color.Red
        for (i in 2.. 100) {
            val gradient = colorGradient(color, color, i)
            gradient.forEach {
                it.assertEquals(color)
            }
        }
    }

    @Test
    fun `multi gradient minimum colors`() {
        val colors = mutableListOf(Color.Red, Color.Blue)

        for (colorListSize in 2..10) {
            for (stepsBetween in -10..0) {
                assertEquals(colorListSize, multiGradient(colors, stepsBetween).size)
            }
            colors.add(Color.Yellow)
        }
    }

    @Test
    fun `multi gradient expected size matches`() {
        val colors = mutableListOf(Color.Red, Color.Blue)

        for (colorListSize in 2..10) {
            for (stepsBetween in 0..10) {
                val gradient = multiGradient(colors, stepsBetween)
                assertEquals(multiGradientSize(colorListSize, stepsBetween), gradient.size)
            }
            colors.add(Color.Yellow)
        }
    }

    @Test
    fun `multi gradient end points match size 2`() {
        val startColor = Color.Red
        val endColor = Color.Blue
        val colors = mutableListOf(startColor, endColor)
        for (stepsBetween in 0..10) {
            val gradient = multiGradient(colors, stepsBetween)
            assertEquals(startColor, gradient[0])
            assertEquals(endColor, gradient[gradient.lastIndex])
        }
    }
    @Test
    fun `multi gradient mid and end points match size 3`() {
        val startColor = Color.Red
        val midColor = Color.Green
        val endColor = Color.Blue
        val colors = mutableListOf(startColor, midColor, endColor)
        for (stepsBetween in 0..10) {
            val gradient = multiGradient(colors, stepsBetween)
            assertEquals(startColor, gradient[0])
            assertEquals(midColor, gradient[gradient.lastIndex / 2])
            assertEquals(endColor, gradient[gradient.lastIndex])
        }
    }

    @Test
    fun `multi gradient monotone match`() {
        val color = Color.Red
        val colors = mutableListOf(color, color)
        for (colorListSize in 2..10) {
            for (stepsBetween in 0..10) {
                val gradient = multiGradient(colors, stepsBetween)
                gradient.forEach {
                    it.assertEquals(color)
                }
            }
            colors.add(color)
        }
    }
}