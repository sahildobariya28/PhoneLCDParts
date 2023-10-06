package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.applicationContext
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.service.CartService
import com.phone.phonelcdparts.domain.model.CartItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class CartRepository : CartService {

    private val client = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest)
    }

    override suspend fun getQuoteId(): Pair<Int, HttpStatusCode?> {
        return try {
            val response = client.get {
                url(HttpRoutes.QUOTE_ID)
                headers.append(
                    "Authorization",
                    "Bearer ${applicationContext?.getString("login_token")?.replace("\"", "")}"
                )
            }

            if (response.status.isSuccess()) {
                val cartList: Int = response.body()
                Pair(cartList, response.status)
            } else {
                Pair(0, response.status)
            }

        } catch (e: Exception) {

            Pair(0, HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun insertCartItem(sku: String, count: Int, quote_id:String): Pair<Int, HttpStatusCode?> {
        return try {
            val response = client.post {
                url(HttpRoutes.INSERT_CART_ITEMS)
                headers.append(
                    "Authorization",
                    "Bearer ${applicationContext?.getString("login_token")?.replace("\"", "")}"
                )
                println("jfkdlsfkw22  $sku, $count, $quote_id" )
                setBody("""
                    {
                      "cartItem": {
                        "sku": "$sku",
                        "qty": $count,
                        "quote_id": "$quote_id"
                      }
                    }
                """.trimIndent())
            }
            if (response.status.isSuccess()) {
                val jsonObject = Json.parseToJsonElement(response.body()).jsonObject
                val qty:Int = jsonObject["qty"].toString().toInt()
                Pair(qty, response.status)
            } else {
                Pair(-1, response.status)
            }

        } catch (e: Exception) {

            Pair(-1, HttpStatusCode.InternalServerError)
        }
    }
    override suspend fun getCartItem(): Pair<List<CartItem>, HttpStatusCode?> {
        return try {
            val response = client.get {
                url(HttpRoutes.CART_ITEMS)
                headers.append(
                    "Authorization",
                    "Bearer ${applicationContext?.getString("login_token")?.replace("\"", "")}"
                )
            }

            if (response.status.isSuccess()) {
                val cartList: List<CartItem> = response.body()
                Pair(cartList, response.status)
            } else {
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {

            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }

}