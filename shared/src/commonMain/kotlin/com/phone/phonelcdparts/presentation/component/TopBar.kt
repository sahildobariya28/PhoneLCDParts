package com.phone.phonelcdparts.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.router.stack.bringToFront
import com.phone.phonelcdparts.domain.model.CartItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logobiglight
import com.phone.phonelcdparts.topSafeArea
import com.phone.phonelcdparts.presentation.screen.cart.CartScreenViewModel
import com.phone.phonelcdparts.presentation.screen.maincontent.MainScreenState
import com.phone.phonelcdparts.presentation.screen.search.SearchScreenViewModel
import io.github.xxfast.decompose.router.Router

@Composable
inline fun TopBarUi(
    searchScreenViewModel: SearchScreenViewModel,
    cartScreenViewModel: CartScreenViewModel,
    router: Router<MainScreenState>,
    crossinline onMenuClick: () -> Unit,
    crossinline onSearchStateChange: () -> Unit
) {

//    val isSearch = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (searchScreenViewModel.isSearch) 1f else 0f)
    val cartItemCount = remember { mutableStateOf(0) }

    var totalCount = 0

    val cartState by cartScreenViewModel.cartList.collectAsState()

    cartState.data?.forEach {item ->
            if (item is CartItem) {
                totalCount += item.qty
            }
    }
    cartItemCount.value = totalCount

    Box(
        Modifier.fillMaxWidth().background(Colors.theme).padding(top = topSafeArea.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth()) {

                // Menu icon button
                Icon(
                    modifier = Modifier.padding(vertical = 10.dp).size(50.dp)
                        .clickable {onMenuClick()}.padding(vertical = 12.dp, horizontal = 5.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))

                // Search icon button
                Icon(
                    modifier = Modifier.padding(vertical = 10.dp).size(50.dp)
                        .clickable { onSearchStateChange()}
                        .padding(vertical = 12.dp, horizontal = 5.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )

                Box(Modifier.padding(vertical = 10.dp).size(50.dp)
                    .clickable {router.bringToFront(MainScreenState.Cart)}.padding(vertical = 12.dp, horizontal = 5.dp)){
                    BadgedBox(modifier = Modifier.padding(end = 10.dp), badge = { Badge { Text(cartItemCount.value.toString()) } }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color.White
                        )
                    }
                }

                // Card icon button

//                    Icon(
//                        modifier = Modifier.padding(vertical = 10.dp).size(50.dp)
//                            .clickable {router.bringToFront(MainScreenState.Cart)}.padding(vertical = 12.dp, horizontal = 5.dp),
//                        imageVector = Icons.Default.ShoppingCart,
//                        contentDescription = "Cart",
//                        tint = Color.White
//                    )

            }


            // Apply scaling animation to the search view
            AnimatedVisibility(
                visible = searchScreenViewModel.isSearch,
                enter = slideInVertically(initialOffsetY = { -it }),
                exit = slideOutVertically(targetOffsetY = { -it }),
                modifier = Modifier.scale(scale.value)
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .height(55.dp)
                        .padding(horizontal = 20.dp).background(Colors.theme),
                    value = searchScreenViewModel.searchText,
                    singleLine = true,
                    onValueChange = {
                        searchScreenViewModel.searchText = it
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp))
                                .background(Color.Red).clickable {
                                    searchScreenViewModel.resetAllData()
                                    if (searchScreenViewModel.searchText.isNotEmpty()){
                                        searchScreenViewModel.getProductList(searchScreenViewModel.searchText, 1)
                                    }

                                }.padding(8.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    textStyle = TextStyle(fontSize = 14.sp),
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Search Product Or Brand", fontSize = 12.sp, color = Colors.text_hint) },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Colors.theme,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Colors.theme,
                        backgroundColor = Colors.text_field_bg_color,
                        focusedLabelColor = Colors.text_hint.copy(alpha = .5f),
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth().height(70.dp), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.width(150.dp),
                imageVector = MyIconPack.Logobiglight,
                contentDescription = "Person",
            )
            
//            Text(text = "PhoneLCDPart", textAlign = TextAlign.Center, color = Color.White, fontSize = 18.sp)

        }
    }
}