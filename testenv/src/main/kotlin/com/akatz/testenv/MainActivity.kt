package com.akatz.testenv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.akatz.colors.multiGradient
import com.akatz.compose.activity.BaseComposeActivity

class MainActivity : BaseComposeActivity<ViewModel>() {

    @Composable
    override fun BaseContent(viewModel: ViewModel?) {
        GradientTest()
    }
}

@Composable
fun GradientTest() {
    val gradient = multiGradient(
        listOf(
            Color.Red,
            Color(255, 165,0),
            Color.Yellow,
            Color.Green,
            Color.Blue,
            Color.Magenta
        ), 5
    )

    LazyColumn {
        items(gradient) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                    .background(item)
            )
        }
    }
}