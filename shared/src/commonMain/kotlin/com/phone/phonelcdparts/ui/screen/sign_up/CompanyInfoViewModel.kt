package com.phone.phonelcdparts.ui.screen.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class CompanyInfoViewModel: ViewModel() {
    var companyLocationText: String by mutableStateOf("")
    var streetAddressText: String by mutableStateOf("")
    var cityText: String by mutableStateOf("")
    var zipCodeText: String by mutableStateOf("")
    var stateText: String by mutableStateOf("")
    var countryText: String by mutableStateOf("")
    var vatNoText: String by mutableStateOf("")
    var phNoText: String by mutableStateOf("")
}