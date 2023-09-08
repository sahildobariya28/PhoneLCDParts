package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.data.model.OrderDTO
import io.ktor.http.HttpStatusCode

interface OrdersService {

    suspend fun getOrders(customer_id: String): Pair<List<OrderDTO>, HttpStatusCode?>

}