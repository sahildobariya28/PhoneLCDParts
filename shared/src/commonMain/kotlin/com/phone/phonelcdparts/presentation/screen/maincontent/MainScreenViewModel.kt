package com.phone.phonelcdparts.presentation.screen.maincontent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent

class MainScreenViewModel: ViewModel(), KoinComponent {

    var navigateClick by mutableStateOf(false)

}