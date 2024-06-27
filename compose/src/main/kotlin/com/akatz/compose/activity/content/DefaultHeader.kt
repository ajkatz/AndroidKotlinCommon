package com.akatz.compose.activity.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.akatz.colors.Colors

@Composable
fun DefaultHeader(height: Dp, title: String, headerContent: @Composable () -> Unit = {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = title,
        textAlign = TextAlign.Center,
        fontSize = 42.sp,
    )
}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Colors.background_grey)
    ) {
        headerContent()
    }
}