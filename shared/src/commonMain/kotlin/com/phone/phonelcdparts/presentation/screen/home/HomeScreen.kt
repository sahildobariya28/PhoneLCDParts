package com.phone.phonelcdparts.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.browserWrapper
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.domain.model.BannerModel
import com.phone.phonelcdparts.domain.model.BrandModel
import com.phone.phonelcdparts.domain.model.NewProductModel
import com.phone.phonelcdparts.domain.model.ProductModel
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Cart
import com.phone.phonelcdparts.resource.myiconpack.Minus
import com.phone.phonelcdparts.resource.myiconpack.Plus
import com.phone.phonelcdparts.presentation.component.CustomMultipleCarousel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@Composable
inline fun HomeScreen(
    homeScreenViewModel: NewHomeScreenViewModel,
    crossinline openProductInfoScreen: (product: String) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Home(
            homeScreenViewModel,
        ) { productSKU ->
            openProductInfoScreen(productSKU)
        }
    }
}

@Composable
inline fun Home(
    homeScreenViewModel: NewHomeScreenViewModel,
    crossinline function: (product: String) -> Unit
) {
    val url = remember { mutableStateOf("") }

    val bannerState by homeScreenViewModel.bannerList.collectAsState()
    val brandState by homeScreenViewModel.brandList.collectAsState()
    val bestSellerState by homeScreenViewModel.bestSellerList.collectAsState()
    val newProductState by homeScreenViewModel.newProductList.collectAsState()

    Surface(color = Color.White) {

        var screenWidth by remember { mutableStateOf(0) }

        Box(Modifier.fillMaxSize().onSizeChanged {
            screenWidth = it.width
        }, contentAlignment = Alignment.BottomCenter) {
            Column(
                Modifier.fillMaxSize().background(Colors.white)
                    .verticalScroll(rememberScrollState()),
            ) {
                if (bannerState.isLoading) {
                    BannerCarouselShimmer(false)
                } else if (bannerState.status.isSuccess()) {
                    bannerState.data?.let { bannerData ->
                        CustomMultipleCarousel(
                            modifier = Modifier.padding(15.dp).fillMaxWidth().aspectRatio(3.1f)
                                .clip(RoundedCornerShape(15.dp)),
                            carouselItems = bannerData.filterIsInstance<BannerModel>(),
                            delayMillis = 5000L,
                            cellCount = 1.0f,
                            preLoadItemCount = 2
                        ) { card ->
                            KamelImage(
                                modifier = Modifier.fillMaxWidth().clickable {
                                    (card as BannerModel).custom_link?.let { customLink ->
                                        browserWrapper.openBrowser(customLink)
                                    }
                                },
                                resource = asyncPainterResource(data = "${HttpRoutes.BANNER_MEDIA_URL}${(card as BannerModel).image}") {
                                    coroutineContext = Job() + Dispatchers.IO
                                },
                                contentDescription = "Profile",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
//                    bannerState.data?.let { BannerCarousel(it.filterIsInstance<BannerItem>()) }
//                    bannerState.data?.let { CustomSingleCarousel(it.filterIsInstance<BannerItem>()) }

                } else if (!bannerState.status.isSuccess()) {
                    BannerCarouselShimmer(true)
                }


                val brandText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Colors.theme,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Shop By ")
                    }
                    withStyle(style = SpanStyle(color = Colors.red, fontWeight = FontWeight.Bold)) {
                        append("Brands")
                    }
                }
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = brandText,
                    style = TextStyle(fontSize = 22.sp)
                )
                Spacer(Modifier.height(15.dp))
                if (brandState.isLoading) {
                    BrandCarouselShimmer(false)
                } else if (brandState.status.isSuccess()) {

                    brandState.data?.let {

                        CustomMultipleCarousel(
                            modifier = Modifier.padding(start = 10.dp).fillMaxWidth().height(35.dp),
                            carouselItems = it.filterIsInstance<BrandModel>(),
                            delayMillis = 5000L,
                            cellCount = 4.5f,
                            preLoadItemCount = 2
                        ) { card ->
                            Box(
                                Modifier.fillMaxWidth().height(35.dp).clip(RoundedCornerShape(7.dp))
                                    .border(1.dp, Color.LightGray, RoundedCornerShape(7.dp))
                                    .clickable {
                                        (card as BannerModel).custom_link?.let { customLink ->
                                            browserWrapper.openBrowser(
                                                customLink
                                            )
                                        }
                                    }
                                    .padding(5.dp)
                            ) {
                                KamelImage(
                                    modifier = Modifier.fillMaxSize(),
                                    resource = asyncPainterResource(data = "${HttpRoutes.BANNER_MEDIA_URL}${(card as BrandModel).image}") {
                                        coroutineContext = Job() + Dispatchers.IO
                                    },
                                    contentDescription = "Profile",
                                )
                            }
                        }
                    }

//                    brandState.data?.let { BrandCarousel(it.filterIsInstance<BrandItem>()) }
                } else if (!brandState.status.isSuccess()) {
                    BrandCarouselShimmer(true)
                }

                val bestSellerText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Colors.theme,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Best ")
                    }
                    withStyle(style = SpanStyle(color = Colors.red, fontWeight = FontWeight.Bold)) {
                        append("Seller")
                    }
                }
                Spacer(Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = bestSellerText,
                    style = TextStyle(fontSize = 22.sp)
                )

                Spacer(Modifier.height(16.dp))

                if (bestSellerState.isLoading) {
                    NewProductItemShimmer(false)
                } else if (bestSellerState.status.isSuccess()) {
                    bestSellerState.data?.let {
                        val list = it.filterIsInstance<NewProductModel>()
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(start = 16.dp),
                            contentPadding = PaddingValues(end = 16.dp)
                        ) {
                            items(list.size) { position ->
                                NewProductItemView(
                                    homeScreenViewModel,
                                    list[position],
                                    {newProductItem ->
                                        function(newProductItem.sku)
                                    },
                                    {imgUrl ->
                                        homeScreenViewModel.isQuickView = true
                                        url.value = imgUrl
                                    },
                                    {
                                        homeScreenViewModel.isQuickView = false
                                    })
                            }
                        }
                    }
                } else if (!newProductState.status.isSuccess()) {
                    NewProductItemShimmer(true)
                }


                val newProductText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Colors.theme,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("New ")
                    }
                    withStyle(style = SpanStyle(color = Colors.red, fontWeight = FontWeight.Bold)) {
                        append("Product")
                    }
                }
                Spacer(Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = newProductText,
                    style = TextStyle(fontSize = 22.sp)
                )

                Spacer(Modifier.height(16.dp))

                if (newProductState.isLoading) {
                    NewProductItemShimmer(false)
                } else if (newProductState.status.isSuccess()) {
                    newProductState.data?.let { newProductList ->
                        val list = newProductList.filterIsInstance<NewProductModel>()
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(start = 16.dp),
                            contentPadding = PaddingValues(end = 16.dp)
                        ) {
                            items(list.size) { position ->
                                NewProductItemView(
                                    homeScreenViewModel,
                                    list[position],
                                    { newProductItem ->
                                        function(newProductItem.sku)
                                    },
                                    {
                                        homeScreenViewModel.isQuickView = true
                                        url.value = it
                                    },
                                    {
                                        homeScreenViewModel.isQuickView = false
                                    })
                            }
                        }
                    }
                } else if (!newProductState.status.isSuccess()) {
                    NewProductItemShimmer(true)
                }


                Spacer(Modifier.height(16.dp))
                val productText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Colors.theme,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("All ")
                    }
                    withStyle(style = SpanStyle(color = Colors.red, fontWeight = FontWeight.Bold)) {
                        append("Product")
                    }
                }
                Spacer(Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = productText,
                    style = TextStyle(fontSize = 22.sp)
                )

                Spacer(Modifier.height(16.dp))

                if (newProductState.isLoading) {
                    NewProductItemShimmer(false)
                } else if (newProductState.status.isSuccess()) {
                    homeScreenViewModel.productList.value.data?.let {
                        val list = it.filterIsInstance<ProductModel>()
                        Column(Modifier.fillMaxWidth()) {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(start = 16.dp),
                                contentPadding = PaddingValues(end = 16.dp)
                            ) {
                                items(list.size) { position ->
                                    StorySummaryView(list[position], { productItem ->
                                        function(productItem.sku)
                                    }, { imgUrl ->
                                        homeScreenViewModel.isQuickView = true
                                        url.value = imgUrl
                                    }, {
                                        homeScreenViewModel.isQuickView = false
                                    })
                                }
                            }
                        }
                    }
                } else if (!newProductState.status.isSuccess()) {
                    NewProductItemShimmer(true)
                }

                Spacer(Modifier.height(16.dp))

            }
            if (homeScreenViewModel.isQuickView) {
                ShowQuickView(url.value)
            }
        }
    }
}

@Composable
fun ShowQuickView(url: String) {

    Box(Modifier.fillMaxSize().background(Color(0x30000000)), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Colors.white)
        ) {
            KamelImage(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                resource = asyncPainterResource(data = url) {
                    coroutineContext = Job() + Dispatchers.IO
                },
                contentScale = ContentScale.FillWidth,
                contentDescription = "Profile",
            )
        }
    }
}

@Composable
inline fun StorySummaryView(
    product: ProductModel,
    crossinline function: (product: ProductModel) -> Unit,
    crossinline isQuickView: (url: String) -> Unit,
    crossinline isNotQuickView: () -> Unit
) {
    var isLongPress by remember { mutableStateOf(false) }



    var count by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .width(160.dp)
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
                    modifier = Modifier.fillMaxSize(),
                    resource = asyncPainterResource(data = "http://192.168.29.236/phonelcdparts/pub/media/catalog/product${product.media_gallery_entries[0].file}") {
                        coroutineContext = Job() + Dispatchers.IO
                    },
                    contentDescription = "Profile",
                )
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

//    var width by remember { mutableStateOf(0) }
//    var count by remember { mutableStateOf(0) }
//
//    val scaleAnimation: Float by animateFloatAsState(targetValue = if (count > 0) 1.2f else 1.0f)
//
//    Box(
//        modifier = Modifier
//            .width(160.dp)
//            .clip(RoundedCornerShape(10.dp))
//            .scale(scaleAnimation)
//            .background(Colors.text_field_bg_color)
//            .padding(10.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier,
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            val painter = rememberAsyncImagePainter(
//                "http://192.168.29.236/phonelcdparts/pub/media/catalog/product${
//                    product.media_gallery_entries[0].file.replace("\"", "")
//                } "
//            )
//
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .onSizeChanged {
//                    width = it.width
//                }
//                .height(toDp(width))
//                .padding(5.dp)
//                .clip(RoundedCornerShape(10.dp))
//                .background(Colors.white)
//            ) {
//                Image(
//                    modifier = Modifier.fillMaxSize(),
//                    painter = painter,
//                    contentDescription = null
//                )
//            }
//
//            Spacer(Modifier.height(5.dp))
//
//            Text(
//                product.name.replace("\"", ""),
//                color = Colors.text_theme,
//                fontSize = 12.sp,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 2,
//                minLines = 1
//            )
//
//            Spacer(Modifier.height(5.dp))
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//
//                Row(Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
//                    Text(
//                        text = "$${product.price}",
//                        fontSize = 14.sp,
//                        color = Colors.theme,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Spacer(Modifier.width(5.dp))
//                    Text(
//                        text = "$${product.price}",
//                        fontSize = 12.sp,
//                        color = Colors.red,
//                        fontWeight = FontWeight.SemiBold,
//                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
//                    )
//                }
//
//                Box(
//                    Modifier.size(20.dp).clip(RoundedCornerShape(10.dp))
//                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
//                        .clickable { count++ },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(text = "+", fontSize = 18.sp, color = Color.LightGray)
//                }
//                Spacer(Modifier.width(5.dp))
//
//                Text(
//                    text = "$count",
//                    fontSize = 14.sp,
//                    color = Colors.theme,
//                    fontWeight = FontWeight.Bold
//                )
//
//                Spacer(Modifier.width(5.dp))
//                Box(
//                    Modifier.size(20.dp).clip(RoundedCornerShape(10.dp))
//                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
//                        .clickable {
//                            if (count > 0) {
//                                count--
//                            }
//                        },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(text = "-", fontSize = 18.sp, color = Color.LightGray)
//                }
//            }
//        }
//    }
}
