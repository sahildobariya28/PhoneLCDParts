package com.phone.phonelcdparts.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.domain.model.ProductItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.ui.component.shimmerEffect
import com.phone.phonelcdparts.ui.screen.home.NewProductItemShimmer
import com.phone.phonelcdparts.ui.screen.home.StorySummaryView
import com.phone.phonelcdparts.util.toDp
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess


@Composable
fun TestingSearchScreen(searchScreenViewModel: SearchScreenViewModel) {

    val productState by searchScreenViewModel.productList.collectAsState()

    Box(Modifier.fillMaxSize().clickable(enabled = false) { }.background(Color.White)) {

        if (productState.status.isSuccess()) {
            productState.data?.let { newData ->
                if(searchScreenViewModel.list.size != 0 && newData.isEmpty()) {
                    searchScreenViewModel.itemFinish = true
                }else if (searchScreenViewModel.list.size != 0 && newData.size < 100){
                    searchScreenViewModel.itemFinish = true
                }else if (searchScreenViewModel.list.size == 0 && newData.isEmpty()) {
                    Box(Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center){
                        Text("No Result Found", fontSize = 20.sp, color = Colors.text_hint, textAlign = TextAlign.Center, lineHeight = 25.sp)
                    }
                }else{
                    if (searchScreenViewModel.isMoreDataRequest) {
                        searchScreenViewModel.list.addAll(newData.filterIsInstance<ProductItem>())
                        searchScreenViewModel.isMoreDataRequest = false
                    }
                }

            }
        }else if (searchScreenViewModel.list.size == 0 && !productState.isLoading){
            Box(Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center){
                Text("No Result Found", fontSize = 20.sp, color = Colors.text_hint, textAlign = TextAlign.Center, lineHeight = 25.sp)
            }
        }else if (searchScreenViewModel.list.size == 0 && productState.isLoading){
            ProductItemGridShimmer(isError = false, enableAnimation = true)
        }else if (!productState.status.isSuccess()) {
            ProductItemGridShimmer(isError = true, enableAnimation = false)
        }


        if (searchScreenViewModel.list.isNotEmpty()) {
            val lazyListState = rememberLazyGridState()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                state = lazyListState
            ) {


                items(searchScreenViewModel.list.size) { position ->
                    StorySummaryView(searchScreenViewModel.list[position],
                        { productItem ->
                            // Call your function with productItem.sku
                        }, { imgUrl ->
                            // Handle image URL
                        }, {
                            // Handle quick view
                        }
                    )


                    if (position == searchScreenViewModel.list.size - 1 && searchScreenViewModel.list.isNotEmpty()) {
                        if (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == searchScreenViewModel.list.size - 1 && !searchScreenViewModel.isMoreDataRequest) {
                            if (!searchScreenViewModel.itemFinish) {
                                searchScreenViewModel.loadMoreProducts(!searchScreenViewModel.isMoreDataRequest)
                            }
                        }
                    }
                }
                if (searchScreenViewModel.isMoreDataRequest) {

                    item(span = { GridItemSpan(2) }) {
                        GridItemSpan(2)
                        if (searchScreenViewModel.itemFinish){
                            PaginationExhaust()
                        }else{
                            PaginationLoading()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ProductItemGridShimmer(isError: Boolean, enableAnimation: Boolean) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(500) {

            var width by remember { mutableStateOf(0) }

            Box(
                modifier = Modifier
                    .width(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Colors.text_field_bg_color)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .onSizeChanged {
                            width = it.width
                        }
                        .height(toDp(width))
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect(enableAnimation = enableAnimation),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isError) {
                            Image(
                                modifier = Modifier.fillMaxHeight(.5f),
                                imageVector = MyIconPack.Logosmallbw,
                                contentDescription = null
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().height(15.dp).clip(CircleShape)
                            .shimmerEffect(enableAnimation = enableAnimation)
                    )
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.width(40.dp).height(15.dp).clip(CircleShape).shimmerEffect(enableAnimation = enableAnimation))
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.width(80.dp).height(15.dp).clip(CircleShape).shimmerEffect(enableAnimation = enableAnimation))
                    Spacer(Modifier.height(8.dp))

                }
            }
        }
    }
}

@Composable
fun PaginationLoading() {

    Box(Modifier.fillMaxWidth().height(80.dp).background(Color.White), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp))
    }
}


@Composable
fun PaginationExhaust() {
    Box(Modifier.fillMaxWidth().height(80.dp).background(Color.White), contentAlignment = Alignment.Center) {
        Text("Thank You")
    }
}