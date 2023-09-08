package com.phone.phonelcdparts.ui.screen.maincontent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.domain.use_case.CartUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainScreenViewModel: ViewModel(), KoinComponent {

    var navigateClick by mutableStateOf(false)

}