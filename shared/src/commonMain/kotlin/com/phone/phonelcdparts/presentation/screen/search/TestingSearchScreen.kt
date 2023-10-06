package com.phone.phonelcdparts.presentation.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.domain.model.ProductModel
import com.phone.phonelcdparts.presentation.component.shimmerEffect
import com.phone.phonelcdparts.presentation.screen.home.CustomOutlinedTextField
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Cart
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.resource.myiconpack.Minus
import com.phone.phonelcdparts.resource.myiconpack.Plus
import com.phone.phonelcdparts.util.toDp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


@Composable
fun TestingSearchScreen(searchScreenViewModel: SearchScreenViewModel) {

    val productState by searchScreenViewModel.productList.collectAsState()

    Box(Modifier.fillMaxSize().clickable(enabled = false) { }.background(Color.White)) {

        if (productState.status.isSuccess()) {
            productState.data?.let { newData ->
                if (searchScreenViewModel.list.size != 0 && newData.isEmpty()) {
                    searchScreenViewModel.itemFinish = true
                } else if (searchScreenViewModel.list.size != 0 && newData.size < 100) {
                    searchScreenViewModel.itemFinish = true
                } else if (searchScreenViewModel.list.size == 0 && newData.isEmpty()) {
                    Box(
                        Modifier.fillMaxSize().background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No Result Found",
                            fontSize = 20.sp,
                            color = Colors.text_hint,
                            textAlign = TextAlign.Center,
                            lineHeight = 25.sp
                        )
                    }
                } else {
                    if (searchScreenViewModel.isMoreDataRequest) {
                        searchScreenViewModel.list.addAll(newData.filterIsInstance<ProductModel>())
                        searchScreenViewModel.isMoreDataRequest = false
                    }
                }

            }
        } else if (searchScreenViewModel.list.size == 0 && !productState.isLoading) {
            Box(
                Modifier.fillMaxSize().background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No Result Found",
                    fontSize = 20.sp,
                    color = Colors.text_hint,
                    textAlign = TextAlign.Center,
                    lineHeight = 25.sp
                )
            }
        } else if (searchScreenViewModel.list.size == 0 && productState.isLoading) {
            ProductItemGridShimmer(isError = false, enableAnimation = true)
        } else if (!productState.status.isSuccess()) {
            ProductItemGridShimmer(isError = true, enableAnimation = false)
        }


        if (searchScreenViewModel.list.isNotEmpty()) {
            val lazyListState = rememberLazyGridState()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                modifier = Modifier.fillMaxWidth().background(Color.White),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                state = lazyListState
            ) {

                items(searchScreenViewModel.list.size) { position ->
                    if(position == 0){
                        println("sfsdfqw324hhjk   : yes i am call")
                    }
                    ProductItemView(searchScreenViewModel.list[position],
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
                    Box(
                        Modifier.width(40.dp).height(15.dp).clip(CircleShape)
                            .shimmerEffect(enableAnimation = enableAnimation)
                    )
                    Spacer(Modifier.height(8.dp))
                    Box(
                        Modifier.width(80.dp).height(15.dp).clip(CircleShape)
                            .shimmerEffect(enableAnimation = enableAnimation)
                    )
                    Spacer(Modifier.height(8.dp))

                }
            }
        }
    }
}

@Composable
fun PaginationLoading() {

    Box(
        Modifier.fillMaxWidth().height(80.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp))
    }
}


@Composable
fun PaginationExhaust() {
    Box(
        Modifier.fillMaxWidth().height(80.dp).background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("Thank You")
    }
}

@Composable
inline fun ProductItemView(
    product: ProductModel,
    crossinline function: (product: ProductModel) -> Unit,
    crossinline isQuickView: (url: String) -> Unit,
    crossinline isNotQuickView: () -> Unit
) {

    var isLongPress by remember { mutableStateOf(false) }
    var count by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Colors.text_field_bg_color)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        try {
                            isLongPress = false
                            delay(500)
                            isLongPress = true
                            isQuickView("http://192.168.29.236/phonelcdparts/pub/media/catalog/product${product.media_gallery_entries[0].file}")
                            // Start recording here
                            awaitRelease()
                        } finally {

                            isNotQuickView()
                            isLongPress = false


                            // Stop recording here
                        }
                    },
                    onTap = {
                        if (!isLongPress) {
                            function(product)
                        }
                    }
                )
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Colors.white)
            ) {

                KamelImage(
                    resource = asyncPainterResource(data = "http://192.168.29.236/phonelcdparts/pub/media/catalog/product${product.media_gallery_entries[0].file}") {
                        coroutineContext = Job() + Dispatchers.IO
                    },
                    contentDescription = "Profile",
                )


//                Image(
//                    modifier = Modifier.fillMaxSize(),
//                    painter = rememberAsyncImagePainter(
//                        "http://192.168.29.236/phonelcdparts/pub/media/catalog/product${product.media_gallery_entries[0].file}"
//                    ),
//                    contentDescription = null
//                )
            }

            Spacer(Modifier.height(5.dp))

            Text(
                text = product.name,
                color = Colors.text_theme,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                minLines = 2
            )

            Spacer(Modifier.height(5.dp))


            Text(
                text = "$${product.price}",
                fontSize = 14.sp,
                color = Colors.theme,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            if (product.visibility == 4) {
                Row(
                    Modifier.padding(horizontal = 8.dp).fillMaxWidth().height(35.dp)
                        .clip(RoundedCornerShape(10.dp))
//                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                        .padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        Modifier.weight(1f)
                            .height(35.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = { count++ }) {
                            Image(
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
                                    .size(25.dp),
                                imageVector = MyIconPack.Plus,
                                contentDescription = ""
                            )
                        }
                    }
                    Box(
                        Modifier.weight(1f)
                            .height(35.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = "$count",
                            onValueChange = {
                                count = if (it.isNotBlank()) {
                                    it.toInt()
                                } else {
                                    0
                                }
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            textStyle = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                color = Colors.theme,
                                fontWeight = FontWeight.Bold
                            ),
                            singleLine = true,
                            maxLines = 1
                        )
                    }
                    Box(
                        Modifier.weight(1f)
                            .height(35.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {
                                if (count > 0) {
                                    count--
                                }
                            }) {
                            Image(
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
                                    .size(25.dp),
                                imageVector = MyIconPack.Minus,
                                contentDescription = ""
                            )
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            } else {
                var email by remember { mutableStateOf("") }

                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    hint = "Enter Email",
                    // Add any additional modifiers here if needed
                )
                Spacer(Modifier.height(8.dp))
            }

            Box(
                Modifier.clip(RoundedCornerShape(8.dp)).background(Colors.theme)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .clickable {
//                        if (!mainScreenViewModel.cartList.contains(newProduct)) {
//                            mainScreenViewModel.cartList.add(newProduct)
//                        }
                    },
                contentAlignment = Alignment.Center
            ) {

                Row {
                    Text(
                        if (product.visibility == 4) "Add to Cart" else "Notify Me",
                        color = Colors.white,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.width(10.dp))
                    Image(
                        modifier = Modifier.size(15.dp),
                        imageVector = MyIconPack.Cart,
                        contentDescription = null
                    )

                }
            }
        }
    }
}