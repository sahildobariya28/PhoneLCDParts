package com.phone.phonelcdparts

import AppContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.phone.phonelcdparts.states.Action
import com.phone.phonelcdparts.ui.ApplicationContainer
import com.phone.phonelcdparts.util.BrowserWrapper
import com.phone.phonelcdparts.util.KMMPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import com.phone.phonelcdparts.states.createStore
import com.phone.phonelcdparts.util.SafeArea

lateinit var browserWrapper: BrowserWrapper
var applicationContext: KMMPreference? = null
val store = CoroutineScope(SupervisorJob()).createStore()
var topSafeArea: Float = 0f
var bottomSafeArea: Float = 0f

@Composable
fun App(pref: KMMPreference, browser: BrowserWrapper) {
    browserWrapper = browser
    applicationContext = pref

    SafeArea{ top, bottom ->
        topSafeArea = top
        bottomSafeArea = bottom

        MaterialTheme {
            println("sdfwerwvsdfdsv      " + applicationContext?.getString("login_token"))
            Box {
//            AppContent()
                ApplicationContainer()
            }
        }

    }
}

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}