package com.phone.phonelcdparts.ui.screen.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.phone.phonelcdparts.domain.use_case.ProductInfoUseCase
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.core.SingleDataState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductInfoScreenViewModel(val sku: String): ViewModel(), KoinComponent {
    private val productInfoUseCase: ProductInfoUseCase by inject()

    private val _productInfo = MutableStateFlow<SingleDataState>(SingleDataState())

    val productInfo: StateFlow<SingleDataState> = _productInfo

//    var skuId by mutableStateOf("")

    init {
        getProductInfo(sku)
    }


    fun getProductInfo(sku: String){
        productInfoUseCase.invoke(sku).onEach {
            when(it){
                is Resource.Loading ->{
                    _productInfo.value = SingleDataState(isLoading = true, data = null, status = it.status)
                }
                is Resource.Success -> {
                    _productInfo.value = SingleDataState(isLoading = false, data = it.data, status = it.status)
                }
                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _productInfo.value = SingleDataState(isLoading = false, data = null, status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}