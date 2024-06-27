package com.akatz.compose.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.akatz.colors.Colors

@Composable
fun IncrementalNumberSelector(
    title: String,
    currentValue: Int,
    incrementAction: () -> Unit,
    decrementAction: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(130.dp)
            .height(60.dp)
            .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                text = title,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.scale(1f),
                    colors = ButtonColors(
                        containerColor = Colors.background_grey,
                        contentColor = Color.Black,
                        disabledContainerColor = Colors.background_grey,
                        disabledContentColor = Color.Black
                    ),
                    onClick = decrementAction) {
                    Text(text = "<")
                }
                Text(
                    text = currentValue.toString()
                )
                Button(modifier = Modifier.scale(1f),
                    colors = ButtonColors(
                        containerColor = Colors.background_grey,
                        contentColor = Color.Black,
                        disabledContainerColor = Colors.background_grey,
                        disabledContentColor = Color.Black
                    ), onClick = incrementAction) {
                    Text(text = ">")
                }
            }
        }
    }
}