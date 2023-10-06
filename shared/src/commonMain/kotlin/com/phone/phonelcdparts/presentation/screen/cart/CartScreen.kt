package com.phone.phonelcdparts.presentation.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.domain.model.CartItem
import io.ktor.http.isSuccess


@Composable
inline fun CartScreen(
    cartScreenViewModel: CartScreenViewModel,
    crossinline openProductInfoScreen: (product: String) -> Unit
) {

    val cartState by cartScreenViewModel.cartList.collectAsState()



    Box(Modifier.fillMaxSize()) {
        if (cartState.isLoading) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(5) {
                    CartItemShimmer(false)
                }
            }
        } else if (cartState.status.isSuccess()) {
//            cartState.data?.let { newData ->
//                cartScreenViewModel.cartItemList.addAll(newData.filterIsInstance<CartItem>())
//                for (i in 0 until cartScreenViewModel.cartItemList.size){
//                    val imgPathUrl = remember { mutableStateOf("") }
//                    rememberCoroutineScope().launch {
//                        val list = cartScreenViewModel.getImageUriFromSKU(cartScreenViewModel.cartItemList[i].sku)
//                        if (list.isNotEmpty()){
//                            cartScreenViewModel.cartItemList[i].imgLink = list[0]
////                            imgPathUrl.value = list[0]
//                        }
//
//                    }
//                }
//            }


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                cartState.data?.let {
                    items(it.size) { pos ->
                        CartItem(cartScreenViewModel, it[pos] as CartItem, {
                            openProductInfoScreen(it.sku)
                        }, {})
                    }
                }
            }

        } else if (!cartState.status.isSuccess()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(5) {
                    CartItemShimmer(true)
                }
            }
        }

    }
}