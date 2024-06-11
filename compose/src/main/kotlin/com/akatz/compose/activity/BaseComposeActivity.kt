package com.akatz.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.akatz.compose.viewmodel.ViewModelSetup
import androidx.compose.material3.Text
import com.akatz.compose.viewmodel.generateViewModel

open class BaseComposeActivity<T : ViewModel>(
    private val viewModelSetup: ViewModelSetup<T>? = null
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: T? = viewModelSetup?.let {
            generateViewModel(this, it)
        }

        setContent {
            BaseContent(viewModel)
        }
    }

    @Composable
    open fun BaseContent(viewModel: T?) {
        Text(text = "Please replace me")
    }
}