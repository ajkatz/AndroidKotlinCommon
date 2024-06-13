package com.akatz.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.akatz.compose.viewmodel.ViewModelSetup
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.akatz.compose.activity.content.DefaultFooter
import com.akatz.compose.activity.content.DefaultHeader
import com.akatz.compose.viewmodel.generateViewModel
import com.akatz.platform.performance.AppStartProfiler
import com.akatz.platform.performance.runProfiled

private const val defaultHeaderHeight = 55
private const val defaultFooterHeight = 65

abstract class BaseComposeActivity<T : ViewModel>(
    private val viewModelSetup: ViewModelSetup<T>? = null,
    private val headerHeightDp: Dp = defaultHeaderHeight.dp,
    private val footerHeightDp: Dp = defaultFooterHeight.dp
) : ComponentActivity() {

    override fun onStart() {
        super.onStart()
        AppStartProfiler.end()
    }

    override fun onCreate(savedInstanceState: Bundle?) = runProfiled(
        "${this::class.simpleName}.onCreate",
        200L
    ) {
        super.onCreate(savedInstanceState)
        AppStartProfiler.pageLoad()

        val viewModel: T? = viewModelSetup?.let {
            generateViewModel(viewModelStoreOwner = this, setup = it)
        }

        setContent {
            if (headerHeightDp > 0.dp) {
                GetHeader(headerHeightDp, viewModel)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = headerHeightDp, bottom = footerHeightDp)
            ) {
                BaseContent(viewModel)
            }
            if (footerHeightDp > 0.dp) {
                GetFooter(footerHeightDp, viewModel)
            }
        }
    }

    @Composable
    abstract fun BaseContent(viewModel: T?)

    @Composable
    open fun GetHeader(heightDp: Dp, viewModel: T?) {
        DefaultHeader(height = heightDp, title = getContentTitle())
    }

    @Composable
    open fun GetFooter(heightDp: Dp, viewModel: T?) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DefaultFooter(height = heightDp, rowContent = { GetFooterContent(viewModel) })
        }
    }

    @Composable
    open fun GetFooterContent(viewModel: T?) { }

    open fun getContentTitle(): String {
        return "Default Title"
    }
}