package com.phone.phonelcdparts.ui.screen.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.domain.model.NewProductItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Cart
import com.phone.phonelcdparts.resource.myiconpack.Logosmall
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.resource.myiconpack.Minus
import com.phone.phonelcdparts.resource.myiconpack.Plus
import com.phone.phonelcdparts.ui.component.shimmerEffect
import com.phone.phonelcdparts.ui.screen.maincontent.MainScreenViewModel
import com.phone.phonelcdparts.util.toDp
import com.seiko.imageloader.rememberAsyncImagePainter
import kotlinx.coroutines.delay

@Composable
fun NewProductItemView(
    newProduct: NewProductItem,
    function: (product: NewProductItem) -> Unit,
    isQuickView: (url: String) -> Unit,
    isNotQuickView: () -> Unit
) {


    var isLongPress = remember { mutableStateOf(false) }
    val painter =
        rememberAsyncImagePainter(newProduct.image.replace("\\", "/"), filterQuality = FilterQuality.None)

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
                            isLongPress.value = false
                            delay(500)
                            isLongPress.value = true
                            isQuickView(newProduct.image.replace("\\", "/"))
                            // Start recording here
                            awaitRelease()
                        } finally {

                            isNotQuickView()
                            isLongPress.value = false


                            // Stop recording here
                        }
                    },
                    onTap = {
                        if (!isLongPress.value) {
                            function(newProduct)
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

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentDescription = null
                )
            }

            Spacer(Modifier.height(5.dp))

            Text(
                newProduct.name.replace("\"", ""),
                color = Colors.text_theme,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                minLines = 2
            )

            Spacer(Modifier.height(5.dp))


            Text(
                text = "$${newProduct.price}",
                fontSize = 14.sp,
                color = Colors.theme,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            if (newProduct.isinstock) {
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
                                if (it.isNotBlank()) {
                                    count = it.toInt()
                                } else {
                                    count = 0
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
                        if (newProduct.isinstock) "Add to Cart" else "Notify Me",
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Enter Email"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val enabled = true
    val singleLine = true

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine,
        textStyle = TextStyle(fontSize = 12.sp, color = Colors.text_theme),
        modifier = modifier.fillMaxWidth().height(35.dp),
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = value,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = enabled,
            interactionSource = interactionSource,
            // keep vertical paddings but change the horizontal
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 8.dp, end = 8.dp, top = 5.dp, bottom = 5.dp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            label = {
                Text(text = hint, fontSize = 12.sp, color = Colors.text_hint)
            }
        )
    }
}

@Composable
fun NewProductItemShimmer(isError: Boolean) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(start = 16.dp)
    ) {
        items(5) {

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
                        .shimmerEffect(),
                        contentAlignment = Alignment.Center
                    ){
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

@Composable
fun Int.getDp(): Dp {
    val px = this
    with(LocalDensity.current) {
        return px.toDp()
    }
}