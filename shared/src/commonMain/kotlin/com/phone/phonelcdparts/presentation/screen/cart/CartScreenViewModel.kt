package com.phone.phonelcdparts.presentation.screen.cart

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

class CartScreenViewModel: ViewModel(), KoinComponent {
    private val cartUseCase: CartUseCase by inject()

    private val _cartList = MutableStateFlow(ListDataState())

    val cartList: StateFlow<ListDataState> = _cartList
    init {
        getCartList()
    }

    fun getCartList(){
        cartUseCase.invoke().onEach {
            when(it){
                is Resource.Loading ->{
                    _cartList.value = ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }
                is Resource.Success -> {
                    _cartList.value = ListDataState(isLoading = false, data = it.data, status = it.status)
                }
                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _cartList.value = ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}