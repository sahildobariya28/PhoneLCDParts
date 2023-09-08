package com.phone.phonelcdparts.ui.screen.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class SignUpViewModel(): ViewModel() {

    var firstNameText: String by mutableStateOf("")
    var lastNameText: String by mutableStateOf("")
    var emailText: String by mutableStateOf("")
    var passwordText: String by mutableStateOf("")
    var conformPasswordText: String by mutableStateOf("")
    var isPasswordToggle: Boolean by mutableStateOf(false)
    var isConformPasswordToggle: Boolean by mutableStateOf(false)
}