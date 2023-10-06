package com.phone.phonelcdparts.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.phone.phonelcdparts.store


@Composable
actual fun BackHandler(isEnabled: Boolean, onBack: () -> Unit) {
    LaunchedEffect(isEnabled) {
        store.events.collect {
            if(isEnabled) {
                onBack()
            }
        }
    }
}