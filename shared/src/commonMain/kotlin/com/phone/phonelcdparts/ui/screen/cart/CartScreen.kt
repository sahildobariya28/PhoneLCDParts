package com.phone.phonelcdparts.ui.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.domain.model.CartItem
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmall
import com.seiko.imageloader.rememberAsyncImagePainter
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun CartScreen(
    cartScreenViewModel: CartScreenViewModel,
    openProductInfoScreen: (product: String) -> Unit
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