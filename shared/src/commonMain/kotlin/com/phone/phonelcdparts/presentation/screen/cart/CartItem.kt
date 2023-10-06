package com.phone.phonelcdparts.presentation.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.domain.model.CartItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmall
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.resource.myiconpack.Minus
import com.phone.phonelcdparts.resource.myiconpack.Plus
import com.phone.phonelcdparts.presentation.component.shimmerEffect
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@Composable
inline fun CartItem(
    cartScreenViewModel: CartScreenViewModel,
    cart: CartItem,
    crossinline openProductInfoScreen: (cart: CartItem) -> Unit,
    isQuickView: (url: String) -> Unit,
) {
    val isLongPress = remember { mutableStateOf(false) }
//    val painter =
//        rememberAsyncImagePainter(cart.image.replace("\\", "/"))

    var count by remember { mutableStateOf(cart.qty) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Colors.text_field_bg_color)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        try {
                            isLongPress.value = false
                            delay(500)
                            isLongPress.value = true
                            awaitRelease()
                        } finally {
                            isLongPress.value = false


                            // Stop recording here
                        }
                    },
                    onTap = {
                        if (!isLongPress.value) {
                            openProductInfoScreen(cart)
                        }
                    }

                )

            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Colors.white),
                contentAlignment = Alignment.Center
            ) {

//                if (cart.imgLink.isNotEmpty()){
//                    val painter =
//                        rememberAsyncImagePainter("${HttpRoutes.PRODUCT_MEDIA_URL}${cart.imgLink}")
//                    Image(
//                        modifier = Modifier.fillMaxSize(),
//                        painter = painter,
//                        contentDescription = null
//                    )
//                }else{
                KamelImage(
                    modifier = Modifier.size(100.dp).padding(20.dp),
                    resource = asyncPainterResource(data = cart.cartAttributes.image_url) {
                        coroutineContext = Job() + Dispatchers.IO
                    },
                    contentDescription = "Profile",
                    contentScale = ContentScale.Fit
                )

//                Image(
//                    modifier = Modifier.fillMaxSize(.5f),
//                    imageVector = MyIconPack.Logosmall,
//                    contentDescription = null
//                )
//                }


//                val imgPathUrl = remember { mutableStateOf("") }
//                rememberCoroutineScope().launch {
//                    val list = cartScreenViewModel.getImageUriFromSKU(cart.sku)
//                    if (list.isNotEmpty()){
//                        imgPathUrl.value = list[0]
//                    }
//
//                }
//                if (imgPathUrl.value.isNotEmpty()){
//                    val painter =
//                        rememberAsyncImagePainter("${HttpRoutes.PRODUCT_MEDIA_URL}${imgPathUrl.value}")
//                    Image(
//                        modifier = Modifier.fillMaxSize(),
//                        painter = painter,
//                        contentDescription = null
//                    )
//                }else{
//                    Image(
//                        modifier = Modifier.fillMaxSize(.5f),
//                        imageVector = MyIconPack.Logosmall,
//                        contentDescription = null
//                    )
//                }

            }

            Spacer(Modifier.width(5.dp))

            Column(
                Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = cart.name.replace("\"", ""),
                        color = Colors.text_theme,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        minLines = 2
                    )

                    IconButton(
                        onClick = {

                        }) {
                        Image(
                            modifier = Modifier.padding().size(25.dp),
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.Red)
                        )
                    }
                }
                Spacer(Modifier.height(5.dp))

                Text(
                    text = "$${cart.sku}",
                    fontSize = 12.sp,
                    color = Colors.theme,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(10.dp))

                Row {
                    Row(
                        Modifier.weight(50f).height(35.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
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

                    Box(Modifier.weight(50f), contentAlignment = Alignment.CenterEnd) {
                        Text(
                            text = "$${cart.price}",
                            fontSize = 16.sp,
                            color = Colors.green,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

            }


        }
    }
}

@Composable
fun CartItemShimmer(isError: Boolean) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = Colors.text_field_bg_color)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Colors.white)
                    .shimmerEffect(),
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

            Spacer(Modifier.width(5.dp))

            Column(
                Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row {
                    Column(Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(15.dp).clip(CircleShape)
                                .shimmerEffect()
                        )
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(.8f).height(15.dp).clip(CircleShape)
                                .shimmerEffect()
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.size(20.dp).clip(CircleShape).shimmerEffect(),
                    )
                }
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(.6f).height(15.dp).clip(CircleShape)
                        .shimmerEffect()
                )
                Spacer(Modifier.height(10.dp))

                Row {
                    Box(
                        Modifier.weight(1f).height(35.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .shimmerEffect()
                    )
                    Spacer(Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(.4f).height(25.dp).clip(CircleShape)
                            .shimmerEffect()
                    )
                }

            }
        }
    }
}