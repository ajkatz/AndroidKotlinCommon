package com.akatz.testenv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.akatz.colors.multiGradient
import com.akatz.compose.activity.BaseComposeActivity
import com.akatz.compose.viewmodel.ViewModelSetup
import com.akatz.compose.widget.IncrementalNumberSelector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainActivity : BaseComposeActivity<GradientViewModel>(
    viewModelSetup = ViewModelSetup(clazz = GradientViewModel::class.java),
) {

    @Composable
    override fun BaseContent(viewModel: GradientViewModel?) {
        GradientTest(viewModel)
    }

    @Composable
    override fun GetFooterContent(viewModel: GradientViewModel?) {
        val steps = viewModel?.steps?.collectAsState()?.value ?: 5
        val dpSize = viewModel?.dpSize?.collectAsState()?.value ?: 25

        Row {
            IncrementalNumberSelector(
                title = "# of Steps",
                currentValue = steps,
                incrementAction = { viewModel?.incrementStep() },
                decrementAction = { viewModel?.decrementStep() }
            )
            IncrementalNumberSelector(
                title = "Size of step (dp)",
                currentValue = dpSize,
                incrementAction = { viewModel?.incrementDp() },
                decrementAction = { viewModel?.decrementDp() }
            )
        }
    }

    override fun getContentTitle(): String {
        return "Gradient Test"
    }
}

@Composable
fun GradientTest(viewModel: GradientViewModel?) {
    val gradient = viewModel?.gradient?.collectAsState()?.value ?: emptyList()
    val dpSize = viewModel?.dpSize?.collectAsState()?.value ?: 25

    LazyColumn {
        items(gradient) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dpSize.dp)
                    .background(item)
            )
        }
    }
}

class GradientViewModel: ViewModel() {
    private val _steps = MutableStateFlow(5)
    val steps: StateFlow<Int> = _steps

    private val _dpSize = MutableStateFlow(25)
    val dpSize: StateFlow<Int> = _dpSize
    
    private val _gradient = MutableStateFlow(createGradient())
    val gradient: StateFlow<List<Color>> = _gradient
    
    fun decrementStep() {
        if (_steps.value > 0) _steps.update { _steps.value - 1 }
        _gradient.update { createGradient() }
    }
    fun incrementStep() {
        _steps.update { _steps.value + 1 }
        _gradient.update { createGradient() }
    }

    fun decrementDp() {
        if (_dpSize.value > 1) _dpSize.update { _dpSize.value - 1 }
    }
    fun incrementDp() {
        _dpSize.update { _dpSize.value + 1 }
    }

    private fun createGradient(): List<Color> {
        return multiGradient(
            listOf(
                Color.Red,
                Color(255, 165,0),
                Color.Yellow,
                Color.Green,
                Color.Blue,
                Color.Magenta
            ), _steps.value
        )
    }
}