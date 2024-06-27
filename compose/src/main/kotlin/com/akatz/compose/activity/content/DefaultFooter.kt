package com.akatz.compose.activity.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.akatz.colors.Colors

@Composable
fun BoxScope.DefaultFooter(height: Dp, rowContent: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Colors.background_grey)
            .align(Alignment.BottomCenter)
    ) {
        rowContent()
    }
}