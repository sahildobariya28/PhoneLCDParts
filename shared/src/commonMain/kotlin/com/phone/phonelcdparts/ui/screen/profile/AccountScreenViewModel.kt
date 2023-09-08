package com.phone.phonelcdparts.ui.screen.profile

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.core.SingleDataState
import com.phone.phonelcdparts.domain.use_case.UserInfoUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccountScreenViewModel: ViewModel(), KoinComponent {

    private val userinfoUseCase: UserInfoUseCase by inject()
    private val _userInfo = MutableStateFlow<SingleDataState>(SingleDataState())
    val userInfo: StateFlow<SingleDataState> = _userInfo

    init {
        getUserInfo()
    }

    fun getUserInfo(){
        userinfoUseCase.invoke().onEach {
            when(it){
                is Resource.Loading ->{
                    _userInfo.value = SingleDataState(isLoading = true, data = null, status = it.status)
                }
                is Resource.Success -> {
                    _userInfo.value = SingleDataState(isLoading = false, data = it.data, status = it.status)
                }
                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _userInfo.value = SingleDataState(isLoading = false, data = null, status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}