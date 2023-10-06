package com.phone.phonelcdparts.presentation.screen.order_list

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
import com.phone.phonelcdparts.domain.model.OrderItem
import io.ktor.http.isSuccess

@Composable
inline fun OrderScreen(ordersViewModel: OrdersViewModel, crossinline onViewMoreClicked: (incrementId: OrderItem) -> Unit) {

    val orderState by ordersViewModel.orderList.collectAsState()

    Box(Modifier.fillMaxSize()) {

        if (orderState.isLoading) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(15.dp)
            ) {
                items(10) {
                    ShimmerOrderItemView(false)
                }
            }
        } else if (orderState.status.isSuccess()) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(15.dp)
            ) {
                orderState.data?.let { orderList ->
                    items(orderList.size) {
                        val orderHistoryItem = orderList[it]
                        OrderItemView(orderHistoryItem as OrderItem) {item ->
                            onViewMoreClicked(item)
                        }

                    }
                }
            }
        } else if (!orderState.status.isSuccess()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(15.dp)
            ) {
                items(10) {
                    ShimmerOrderItemView(true)
                }
            }
        }
    }
}