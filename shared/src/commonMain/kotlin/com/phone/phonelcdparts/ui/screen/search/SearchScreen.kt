package com.phone.phonelcdparts.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.domain.model.ProductItem
import com.phone.phonelcdparts.ui.screen.home.NewProductItemShimmer
import com.phone.phonelcdparts.ui.screen.home.StorySummaryView
import io.ktor.http.isSuccess

@Composable
fun SearchScreen(searchScreenViewModel: SearchScreenViewModel) {


    val productState by searchScreenViewModel.productList.collectAsState()

//    searchScreenViewModel.getProductList(searchText)


    Box(Modifier.fillMaxSize().clickable(enabled = false) { }.background(Color.White)) {


        if (productState.isLoading) {

            NewProductItemShimmer(false)
        } else if (productState.status.isSuccess()) {
            searchScreenViewModel.productList.value.data?.let {
                val list = it.filterIsInstance<ProductItem>()
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 2 columns
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(list.size) { position ->
                        StorySummaryView(list[position],
                            { productItem ->
                                // Call your function with productItem.sku
                            }, { imgUrl ->
                                // Handle image URL
                            }, {
                                // Handle quick view
                            }
                        )
                    }
                }
            }
        } else if (!productState.status.isSuccess()) {
            NewProductItemShimmer(true)
        }

    }
}


//just for test pagination is work or not


//class NewsManuelPagingRepository @Inject constructor(
//    private val newsApiService: NewsApiService
//) {
//    suspend fun getNews(page: Int): Flow<NewsResponse> = flow {
//        try {
//            emit(newsApiService.getNews(page))
//        } catch (error: Exception) {
//            emit(NewsResponse(emptyList(), error.message ?: "", 0))
//        }
//    }.flowOn(Dispatchers.IO)
//}
//
//enum class ListState {
//    IDLE,
//    LOADING,
//    PAGINATING,
//    ERROR,
//    PAGINATION_EXHAUST,
//}
//
//
//@HiltViewModel
//class NewsManuelPagingViewModel @Inject constructor(
//    private val repository: NewsManuelPagingRepository,
//): ViewModel() {
//    val newsList = mutableStateListOf<Article>()
//
//    private var page by mutableStateOf(1)
//    var canPaginate by mutableStateOf(false)
//    var listState by mutableStateOf(ListState.IDLE)
//
//    init {
//        getNews()
//    }
//
//    fun getNews() = viewModelScope.launch {
//        if (page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE) {
//            listState = if (page == 1) ListState.LOADING else ListState.PAGINATING
//
//            repository.getNews(page).collect() {
//                if (it.status == "ok") {
//                    canPaginate = it.articles.size == 20
//
//                    if (page == 1) {
//                        newsList.clear()
//                        newsList.addAll(it.articles)
//                    } else {
//                        newsList.addAll(it.articles)
//                    }
//
//                    listState = ListState.IDLE
//
//                    if (canPaginate)
//                        page++
//                } else {
//                    listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
//                }
//            }
//        }
//    }
//
//    override fun onCleared() {
//        page = 1
//        listState = ListState.IDLE
//        canPaginate = false
//        super.onCleared()
//    }
//}
//
//val viewModel = hiltViewModel<NewsManuelPagingViewModel>()
//val lazyColumnListState = rememberLazyListState()
//val coroutineScope = rememberCoroutineScope()
//
//val shouldStartPaginate = remember {
//    derivedStateOf {
//        viewModel.canPaginate && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
//    }
//}
//
//val articles = viewModel.newsList
//
//LaunchedEffect(key1 = shouldStartPaginate.value) {
//    if (shouldStartPaginate.value && viewModel.listState == ListState.IDLE)
//        viewModel.getNews()
//}
//
//
//LazyColumn(state = lazyColumnListState) {
//    items(
//        items = articles,
//        key = { it.url },
//    ) { article ->
//        Text(
//            modifier = Modifier
//                .height(75.dp),
//            text = article.title,
//        )
//
//        Divider()
//    }
//
//    item (
//        key = viewModel.listState,
//    ) {
//        when(viewModel.listState) {
//            ListState.LOADING -> {
//                Loading()
//            }
//            ListState.PAGINATING -> {
//                PaginationLoading()
//            }
//            ListState.PAGINATION_EXHAUST -> {
//                PaginationExhaust()
//            }
//            else -> {}
//        }
//    }
//}