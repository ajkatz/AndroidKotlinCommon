package com.akatz.compose.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IncrementalNumberSelector(
    title: String,
    currentValue: Int,
    incrementAction: () -> Unit,
    decrementAction: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(60.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(modifier = Modifier.padding(2.dp), onClick = decrementAction) {
                    Text(text = "<")
                }
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = currentValue.toString()
                )
                Button(modifier = Modifier.padding(2.dp), onClick = incrementAction) {
                    Text(text = ">")
                }
            }
        }
    }
}