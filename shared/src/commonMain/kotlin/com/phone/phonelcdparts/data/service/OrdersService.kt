package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.OrderItem
import io.ktor.http.HttpStatusCode

interface OrdersService {

    suspend fun getOrders(customer_id: String): Pair<List<OrderItem>, HttpStatusCode?>

}