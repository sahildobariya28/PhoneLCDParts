package com.phone.phonelcdparts.ui.screen.order_list

import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.domain.use_case.OrderUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OrdersViewModel: ViewModel(), KoinComponent {

    private val orderUseCase: OrderUseCase by inject()
    private val _orderList = MutableStateFlow(ListDataState())
    val orderList: StateFlow<ListDataState> = _orderList

    init {
        getOrderList()
    }

    fun getOrderList() {
        orderUseCase.invoke("23471").onEach {
            when (it) {
                is Resource.Loading -> {
                    _orderList.value =
                        ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }

                is Resource.Success -> {
                    _orderList.value =
                        ListDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _orderList.value =
                            ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

