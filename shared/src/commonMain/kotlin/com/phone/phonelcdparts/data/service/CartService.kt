package com.phone.phonelcdparts.data.service


import com.phone.phonelcdparts.domain.model.CartItem
import io.ktor.http.HttpStatusCode

interface CartService {

    suspend fun getCartItem(): Pair<List<CartItem>, HttpStatusCode?>
    suspend fun insertCartItem(sku: String, count: Int, quote_id:String): Pair<Int, HttpStatusCode?>
    suspend fun getQuoteId(): Pair<Int, HttpStatusCode?>
}