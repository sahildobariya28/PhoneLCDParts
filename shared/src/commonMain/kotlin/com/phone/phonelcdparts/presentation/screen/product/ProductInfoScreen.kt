package com.phone.phonelcdparts.presentation.screen.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.core.HttpRoutes.PRODUCT_MEDIA_URL
import com.phone.phonelcdparts.domain.model.ProductDetailModel
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmall
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.resource.myiconpack.Minus
import com.phone.phonelcdparts.resource.myiconpack.Plus
import com.phone.phonelcdparts.presentation.component.shimmerEffect
import com.phone.phonelcdparts.util.toDp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job

@Composable
fun ProductInfoScreen(sku: String) {

    val productInfoScreenViewModel =
        getViewModel(key = "product_info", factory = viewModelFactory { ProductInfoScreenViewModel(sku) })

    val productInfoState by productInfoScreenViewModel.productInfo.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color.White).padding(20.dp)) {

        Column(Modifier.verticalScroll(rememberScrollState())) {

            if (productInfoState.isLoading){
                ProductInfoShimmer(false)
            }else if (productInfoState.status.isSuccess()) {

                productInfoState.data?.let { info ->
                    ProductInfo(info as ProductDetailModel)
                }

            }else if (!productInfoState.status.isSuccess()){
                ProductInfoShimmer(true)
            }
        }
    }
}

@Composable
fun ProductInfo(info: ProductDetailModel) {
    var width by remember { mutableStateOf(0) }
    var count by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .onSizeChanged {
            width = it.width
        }
        .height(toDp(width))
        .clip(RoundedCornerShape(15.dp))
        .background(Colors.text_field_bg_color)
    ) {
        Box(
            modifier = Modifier.padding(20.dp).fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(15.dp)).padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                if (info.imagePath.isNotEmpty()) {
                    KamelImage(
                        modifier = Modifier.fillMaxSize(),
                        resource = asyncPainterResource(data = "$PRODUCT_MEDIA_URL${
                            info.imagePath
                        }") {
                            coroutineContext = Job() + Dispatchers.IO
                        },
                        contentDescription = "Profile",
                        contentScale = ContentScale.Fit
                    )
                }else{
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = MyIconPack.Logosmall,
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }

            }
        }
    }
    Spacer(Modifier.height(16.dp))

    Text(
        text = info.name,
        maxLines = 3,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Colors.text_theme
    )

    Spacer(Modifier.height(16.dp))

    Row(Modifier.fillMaxWidth()) {
        val skuText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Colors.black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            ) {
                append("SKU ")
            }
            withStyle(
                style = SpanStyle(
                    color = Colors.text_hint,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            ) {
                append(info.sku)
            }
        }

        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = skuText,
            maxLines = 1
        )

        Spacer(Modifier.width(8.dp))
        Divider(Modifier.fillMaxHeight().width(1.dp).background(Colors.text_hint))
        Spacer(Modifier.width(8.dp))

        val binPackingNumberText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Colors.black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            ) {
                append("Bin Picking Number ")
            }
            withStyle(
                style = SpanStyle(
                    color = Colors.text_hint,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            ) {
                append("")
            }
        }

        Text(
            modifier = Modifier.weight(1f),
            text = binPackingNumberText,
            maxLines = 1
        )

    }
    Spacer(Modifier.height(16.dp))

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {

        Text(
            text = "$${info.price}",
            fontSize = 22.sp,
            color = Colors.green,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.width(10.dp))

        Text(
            text = "$${info.price}",
            fontSize = 18.sp,
            color = Colors.red,
            fontWeight = FontWeight.Bold,
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )

    }
    Spacer(Modifier.height(16.dp))

    Row(Modifier.fillMaxWidth()) {

        Row(
            Modifier.padding(horizontal = 16.dp).fillMaxWidth().height(50.dp).weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                Modifier.width(40.dp).height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    modifier = Modifier.width(40.dp).height(50.dp)
                        .padding(vertical = 10.dp, horizontal = 5.dp),
                    onClick = { count++ }) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        imageVector = MyIconPack.Plus,
                        contentDescription = ""
                    )
                }
            }
            Box(
                Modifier.weight(1f)
                    .height(50.dp),
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
//                            Text(
//                                text = "$count",
//                                fontSize = 18.sp,
//                                color = Colors.theme,
//                                fontWeight = FontWeight.Bold
//                            )
            }
            Box(
                Modifier.width(40.dp).height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    modifier = Modifier.width(40.dp).height(50.dp)
                        .padding(vertical = 10.dp, horizontal = 5.dp), onClick = {
                        if (count > 0) {
                            count--
                        }
                    }) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        imageVector = MyIconPack.Minus,
                        contentDescription = ""
                    )
                }
            }
        }

        Box(
            Modifier.padding(horizontal = 16.dp).fillMaxWidth().height(50.dp).weight(1f)
                .clip(RoundedCornerShape(10.dp)).background(Colors.theme),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Add to Cart",
                fontSize = 16.sp,
                color = Colors.white,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProductInfoShimmer(isError: Boolean) {
    var width by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .onSizeChanged {
            width = it.width
        }
        .height(toDp(width))
        .clip(RoundedCornerShape(15.dp))
        .shimmerEffect(),
        contentAlignment = Alignment.Center
    ){
        if (isError){
            Image(
                modifier = Modifier.fillMaxHeight(.5f),
                imageVector = MyIconPack.Logosmallbw,
                contentDescription = null
            )
        }
    }

    Spacer(Modifier.height(16.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clip(CircleShape)
            .shimmerEffect()
    )
    Spacer(Modifier.height(8.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth(.2f)
            .height(15.dp)
            .clip(CircleShape)
            .shimmerEffect()
    )
    Spacer(Modifier.height(24.dp))

    Box(modifier = Modifier.fillMaxWidth(.7f).height(15.dp).clip(CircleShape).shimmerEffect())

    Spacer(Modifier.height(16.dp))

    Row(Modifier.fillMaxWidth().height(15.dp), verticalAlignment = Alignment.Bottom) {
        Box(
            modifier = Modifier.width(15.dp).height(20.dp).clip(RoundedCornerShape(10.dp))
                .shimmerEffect()
        )
        Spacer(Modifier.width(10.dp))
        Box(
            modifier = Modifier.width(100.dp).height(20.dp).clip(RoundedCornerShape(10.dp))
                .shimmerEffect()
        )
    }
    Spacer(Modifier.height(16.dp))

    Row(Modifier.fillMaxWidth()) {
        Box(
            Modifier.padding(end = 8.dp).fillMaxWidth().height(45.dp).weight(1f).clip(
                RoundedCornerShape(10.dp)
            ).shimmerEffect()
        )
        Box(
            Modifier.padding(end = 8.dp).fillMaxWidth().height(45.dp).weight(1f).clip(
                RoundedCornerShape(10.dp)
            ).shimmerEffect()
        )
    }
    Column {
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Description",
            style = TextStyle(color = Colors.text_theme),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(16.dp))
        for (i in 0..6) {
            Row(Modifier.fillMaxWidth()) {
                Box(Modifier.size(15.dp).clip(CircleShape).shimmerEffect())
                Spacer(Modifier.width(16.dp))
                Box(Modifier.fillMaxWidth().height(15.dp).clip(CircleShape).shimmerEffect())
            }
            Spacer(Modifier.height(8.dp))
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Warranty & Shipping",
            style = TextStyle(color = Colors.text_theme),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(16.dp))
        for (i in 0..6) {
            Row(Modifier.fillMaxWidth()) {
                Box(Modifier.size(15.dp).clip(CircleShape).shimmerEffect())
                Spacer(Modifier.width(16.dp))
                Box(Modifier.fillMaxWidth().height(15.dp).clip(CircleShape).shimmerEffect())
            }
            Spacer(Modifier.height(8.dp))
        }
    }

    val bestSellerText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Colors.theme,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Featured ")
        }
        withStyle(style = SpanStyle(color = Colors.red, fontWeight = FontWeight.Bold)) {
            append("Product")
        }
    }
    Spacer(Modifier.height(16.dp))

    Text(
        text = bestSellerText,
        style = TextStyle(fontSize = 22.sp)
    )

    Spacer(Modifier.height(16.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(5) {

            var boxWidth by remember { mutableStateOf(0) }

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
                            boxWidth = it.width
                        }
                        .height(toDp(boxWidth))
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect(),
                        contentAlignment = Alignment.Center
                    ){
                        if (isError){
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
                            .shimmerEffect()
                    )
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.width(40.dp).height(15.dp).clip(CircleShape).shimmerEffect())
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.width(80.dp).height(15.dp).clip(CircleShape).shimmerEffect())
                    Spacer(Modifier.height(8.dp))

                }
            }
        }
    }
}