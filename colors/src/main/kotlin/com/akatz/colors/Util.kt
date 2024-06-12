package com.akatz.colors

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import kotlin.math.max

fun colorGradient(
    startColor: Color,
    endColor: Color,
    totalColors: Int
): List<Color> {
    val steps = (if (totalColors < 2) 2 else totalColors) - 1
    val redStep = (endColor.red - startColor.red) / steps
    val greenStep = (endColor.green - startColor.green) / steps
    val blueStep = (endColor.blue - startColor.blue) / steps

    val colorList = mutableListOf(startColor)

    for (i in 1..steps) {
        colorList.add(Color(
            red = startColor.red + redStep * i,
            green = startColor.green + greenStep * i,
            blue = startColor.blue + blueStep * i
        ))
    }

    return colorList
}

fun multiGradient(
    colors: List<Color>,
    stepsBetween: Int
): List<Color> {
    val totalColorsPerList = max(stepsBetween, 0) + 2
    val colorList = mutableListOf(colors[0])
    for (i in 0..< colors.lastIndex) {
        val gradient = colorGradient(colors[i], colors[i + 1], totalColorsPerList)
        colorList.addAll(gradient.subList(1, gradient.size))
    }
    return colorList
}

fun multiGradientSize(colorListSize: Int, stepsBetween: Int): Int =
    (colorListSize - 1) * stepsBetween + colorListSize

fun List<Color>.multiGradientSize(stepsBetween: Int) = ((this.size - 1) * stepsBetween) + this.size

fun String.parseColor(): Color {
    // Remove the '#' character if it's present
    val color = this.removePrefix("#")

    // Parse the color string based on its length
    return when (color.length) {
        6 -> {
            // Color without alpha, assume alpha is FF
            val colorInt = color.toLong(16) or 0x00000000FF000000
            Color(colorInt.toULong())
        }
        8 -> {
            // Color with alpha
            val colorInt = color.toLong(16)
            Color(colorInt.toULong())
        }
        else -> throw IllegalArgumentException("Invalid color string format")
    }
}

fun String.toColor(): Color = Color(this.toColorInt())