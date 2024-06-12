package com.akatz.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

data class ViewModelSetup<T : ViewModel>(
    val factory: ViewModelProvider.Factory? = ViewModelProvider.NewInstanceFactory(),
    val clazz: Class<T>
)

fun <T : ViewModel> generateViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    setup: ViewModelSetup<T>
): T {
    return if (setup.factory != null) {
        ViewModelProvider(viewModelStoreOwner, setup.factory)[setup.clazz]
    } else {
        ViewModelProvider(viewModelStoreOwner)[setup.clazz]
    }
}
