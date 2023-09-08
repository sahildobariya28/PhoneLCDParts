package com.phone.phonelcdparts.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.domain.model.ProductItem
import com.phone.phonelcdparts.domain.use_case.ProductUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchScreenViewModel: ViewModel(), KoinComponent {

    var isMoreDataRequest by mutableStateOf(true)
    var itemFinish by mutableStateOf(false)
    var isSearch by mutableStateOf(false)
    var searchText by mutableStateOf("")
    private var currentPage = 1

    private val productUseCase: ProductUseCase by inject()
    private val _productList = MutableStateFlow(ListDataState())
    val productList: StateFlow<ListDataState> = _productList
    val list = mutableStateListOf<ProductItem>()



//    init {
//        getProductList(searchText.value, currentPage) // Initialize with default values
//    }

    fun getProductList(searchText: String, page: Int) {
        viewModelScope.launch { // Use viewModelScope here
            productUseCase.invoke(searchText, page).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _productList.value = ListDataState(isLoading = true, data = emptyList(), status = result.status)
                    }
                    is Resource.Success -> {
                        _productList.value = ListDataState(isLoading = false, data = result.data, status = result.status)
                    }
                    is Resource.Error -> {
                        if (!result.status.isSuccess()) {
                            _productList.value = ListDataState(isLoading = false, data = emptyList(), status = result.status)
                        }
                    }
                }
            }
        }
    }

    fun loadMoreProducts(isSuccess: Boolean) {
        if (isSuccess) {
            currentPage++
            getProductList(searchText, currentPage)
            isMoreDataRequest = true
        }
    }

    fun resetAllData(){
        itemFinish = false
//        searchText = ""
        list.clear()
        currentPage = 1
        isMoreDataRequest = true
    }
}


