package com.phone.phonelcdparts.ui.screen.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class SignInViewModel(): ViewModel() {
    var emailText: String by mutableStateOf("jenul1@phonelcdparts.com")
    var passwordText: String by mutableStateOf("SoftAdminMage2022")
    var isPasswordToggle:Boolean by mutableStateOf(false)
}