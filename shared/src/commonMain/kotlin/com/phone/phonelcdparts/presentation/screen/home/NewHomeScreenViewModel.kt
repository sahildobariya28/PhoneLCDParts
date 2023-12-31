package com.phone.phonelcdparts.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.core.SingleDataState
import com.phone.phonelcdparts.domain.use_case.BannerUseCase
import com.phone.phonelcdparts.domain.use_case.BestSellerUseCase
import com.phone.phonelcdparts.domain.use_case.BrandUseCase
import com.phone.phonelcdparts.domain.use_case.GetQuoteIdUseCase
import com.phone.phonelcdparts.domain.use_case.InsertCartItemUseCase
import com.phone.phonelcdparts.domain.use_case.NewProductUseCase
import com.phone.phonelcdparts.domain.use_case.ProductUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NewHomeScreenViewModel : ViewModel(), KoinComponent {

    private val bannerUseCase: BannerUseCase by inject()
    private val brandUseCase: BrandUseCase by inject()
    private val bestSellerUseCase: NewProductUseCase by inject()
    private val newProductUseCase: BestSellerUseCase by inject()
    private val productUseCase: ProductUseCase by inject()
    private val insertCartItemUseCase: InsertCartItemUseCase by inject()
    private val getQuoteIdUseCase: GetQuoteIdUseCase by inject()

    private val _bannerList = MutableStateFlow(ListDataState())
    private val _brandList = MutableStateFlow(ListDataState())
    private val _bestSellerList = MutableStateFlow(ListDataState())
    private val _newProductList = MutableStateFlow(ListDataState())
    private val _productList = MutableStateFlow(ListDataState())
    private val _insertedItemCount = MutableStateFlow(SingleDataState())
    private val _quoteID = MutableStateFlow(SingleDataState())

    val bannerList: StateFlow<ListDataState> = _bannerList
    val brandList: StateFlow<ListDataState> = _brandList
    val bestSellerList: StateFlow<ListDataState> = _bestSellerList
    val newProductList: StateFlow<ListDataState> = _newProductList
    val productList: StateFlow<ListDataState> = _productList
    val insertedItemCount: StateFlow<SingleDataState> = _insertedItemCount
    val quoteID: StateFlow<SingleDataState> = _quoteID


    var isQuickView by mutableStateOf(false)

    init {
        getBannerList()
        getBrandList()
        getBestSellerList()
        getNewProductList()
        getProductList()
        getQuoteId()
    }

    private fun getQuoteId(){
        getQuoteIdUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _insertedItemCount.value =
                        SingleDataState(isLoading = true, data = false, status = it.status)
                }

                is Resource.Success -> {
                    _insertedItemCount.value = SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _insertedItemCount.value = SingleDataState(isLoading = false, data = false, status = it.status)
                    }
                }
            }
        }
    }

    fun insertCartItem(sku: String, count: Int = 1, quote_id:String){
        insertCartItemUseCase.invoke(sku, count, quote_id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _insertedItemCount.value =
                        SingleDataState(isLoading = true, data = false, status = it.status)
                }

                is Resource.Success -> {
                    _insertedItemCount.value = SingleDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _insertedItemCount.value = SingleDataState(isLoading = false, data = false, status = it.status)
                    }
                }
            }
        }
    }
    fun getBannerList() {
        bannerUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _bannerList.value =
                        ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }

                is Resource.Success -> {
                    _bannerList.value =
                        ListDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _bannerList.value =
                            ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getBrandList() {
        brandUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _brandList.value =
                        ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }

                is Resource.Success -> {
                    _brandList.value =
                        ListDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _brandList.value =
                            ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getBestSellerList() {
        bestSellerUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _bestSellerList.value =
                        ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }

                is Resource.Success -> {
                    _bestSellerList.value =
                        ListDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _bestSellerList.value =
                            ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getNewProductList() {
        newProductUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _newProductList.value =
                        ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }

                is Resource.Success -> {
                    _newProductList.value =
                        ListDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _newProductList.value =
                            ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProductList() {
        productUseCase.invoke().onEach {
            when (it) {
                is Resource.Loading -> {
                    _productList.value =
                        ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }

                is Resource.Success -> {
                    _productList.value =
                        ListDataState(isLoading = false, data = it.data, status = it.status)
                }

                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _productList.value =
                            ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

