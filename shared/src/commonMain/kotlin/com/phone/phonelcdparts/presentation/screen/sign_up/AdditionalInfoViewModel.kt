package com.phone.phonelcdparts.presentation.screen.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class AdditionalInfoViewModel: ViewModel() {

    var que1: String by mutableStateOf("")
    var que2: String by mutableStateOf("")
    var que3: String by mutableStateOf("")
    var representativeText: String by mutableStateOf("")

}