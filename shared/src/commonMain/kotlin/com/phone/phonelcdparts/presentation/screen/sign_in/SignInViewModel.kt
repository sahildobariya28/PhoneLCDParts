package com.phone.phonelcdparts.presentation.screen.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.core.SingleDataState
import com.phone.phonelcdparts.domain.use_case.LoginUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInViewModel: ViewModel(), KoinComponent {
    var emailText: String by mutableStateOf("jenul1@phonelcdparts.com")
    var passwordText: String by mutableStateOf("SoftAdminMage2022")
    var isPasswordToggle:Boolean by mutableStateOf(false)

    private val loginUseCase: LoginUseCase by inject()
    private val _accessToken = MutableStateFlow(SingleDataState())
    val accessToken: StateFlow<SingleDataState> = _accessToken


    fun getLogin(username: String, password: String) {
        loginUseCase.invoke(username, password).onEach {
            when (it) {
                is Resource.Loading -> {
                    _accessToken.value =
                        SingleDataState(isLoading = true, data = "Not Found", status = it.status)
                }

                is Resource.Success -> {
                    _accessToken.value =
                        SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _accessToken.value =
                            SingleDataState(isLoading = false, data = "Not Found", status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}