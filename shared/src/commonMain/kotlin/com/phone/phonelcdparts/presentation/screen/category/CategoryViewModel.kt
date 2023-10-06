package com.phone.phonelcdparts.presentation.screen.category

import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.domain.use_case.CategoryUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoryViewModel: ViewModel(), KoinComponent {

    private val categoryUseCase: CategoryUseCase by inject()
    private val _categoryList = MutableStateFlow(ListDataState())
    val categoryList: StateFlow<ListDataState> = _categoryList

    init {
        getBannerList()
    }

    fun getBannerList(){
        categoryUseCase.invoke().onEach {
            when(it){
                is Resource.Loading ->{
                    _categoryList.value = ListDataState(isLoading = true, data = emptyList(), status = it.status)
                }
                is Resource.Success -> {
                    _categoryList.value = ListDataState(isLoading = false, data = it.data, status = it.status)
                }
                is Resource.Error -> {
                    if (!it.status.isSuccess()) {
                        _categoryList.value = ListDataState(isLoading = false, data = emptyList(), status = it.status)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


//    var category by mutableStateOf(emptyList<CategoryDTO>())
//
//    init {
//        GlobalScope.launch(Dispatchers.Default) {
//            val fetchProduct =  CategoryService.create().getCategories()
//            category = fetchProduct
//        }
//    }
}