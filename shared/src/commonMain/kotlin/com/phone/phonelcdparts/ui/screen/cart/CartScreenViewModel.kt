package com.phone.phonelcdparts.ui.screen.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainProductMediaGallery
import com.phone.phonelcdparts.data.remote.ProductInfoService
import com.phone.phonelcdparts.domain.model.CartItem
import com.phone.phonelcdparts.domain.use_case.ProductMediaUseCase
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
    private val productMediaUseCase: ProductMediaUseCase by inject()
    private val  repository: ProductInfoService by inject()

    private val _cartList = MutableStateFlow<ListDataState>(ListDataState())
    private val _mediaList = MutableStateFlow<ListDataState>(ListDataState())

    val cartList: StateFlow<ListDataState> = _cartList
    val mediaList: StateFlow<ListDataState> = _mediaList


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

    suspend fun getImageUriFromSKU(sku: String): List<String>{

        val(productInfoDTO, status) = repository.getProductMedia(sku)
        val domainData = if (productInfoDTO?.isNotEmpty() == true) productInfoDTO.map { it.toDomainProductMediaGallery() } else emptyList()
        return domainData

//        productMediaUseCase.invoke(sku).onEach {
//            when(it){
//                is Resource.Loading ->{
//                    _mediaList.value = ListDataState(isLoading = true, data = emptyList(), status = it.status)
//                }
//                is Resource.Success -> {
//                    _mediaList.value = ListDataState(isLoading = false, data = it.data, status = it.status)
//                }
//                is Resource.Error -> {
//                    if (!it.status.isSuccess()) {
//                        _mediaList.value = ListDataState(isLoading = false, data = emptyList(), status = it.status)
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)
    }
}