package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.service.OrdersService
import com.phone.phonelcdparts.domain.model.ItemDomain
import com.phone.phonelcdparts.domain.model.OrderItem
import com.phone.phonelcdparts.domain.model.PaymentDomain
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class OrdersRepository : OrdersService {

    private val client = HttpClient{
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
        install(DefaultRequest) {
            headers.append("Authorization", "Bearer ${HttpRoutes.BAREAR_TOKEN}")
        }
    }

    override suspend fun getOrders(customer_id: String): Pair<List<OrderItem>, HttpStatusCode?> {
        return try {
            val response = client.get {
                url("${HttpRoutes.ORDER_HISTORY}?searchCriteria[filter_groups][0][filters][0][field]=customer_id&searchCriteria[filter_groups][0][filters][0][value]=${customer_id}&searchCriteria[filter_groups][0][filters][0][condition_type]=eq")
            }
            if(response.status.isSuccess()){
                val jsonObject = Json.parseToJsonElement(response.body()).jsonObject
                val orderHistory: List<OrderItem> = parseOrderHistory(jsonObject["items"]?.jsonArray)
                Pair(orderHistory, response.status)
            }else{
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {

            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }
}

private fun parseOrderHistory(json: JsonArray?): List<OrderItem> {
    val listTest = mutableListOf<OrderItem>()
    json?.forEach {

        val billingAddressId = it.jsonObject["billing_address_id"].toString().toInt()
        val entityId = it.jsonObject["entity_id"].toString().toInt()
        val createdAt = it.jsonObject["created_at"].toString().replace("\"", "")
        val customerEmail = it.jsonObject["customer_email"].toString().replace("\"", "")
        val customerFirstname = it.jsonObject["customer_firstname"].toString().replace("\"", "")
        val customerId = it.jsonObject["customer_id"].toString().toInt()
        val customerIsQuest = it.jsonObject["customer_is_guest"].toString().toInt()
        val customerLastname = it.jsonObject["customer_lastname"].toString().replace("\"", "")
        val grandTotal = it.jsonObject["grand_total"].toString().toDouble()
        val incrementId = it.jsonObject["increment_id"].toString().replace("\"", "")
        val status = it.jsonObject["status"].toString().replace("\"", "")


        val paymentMethod = parsePaymentInfo(it.jsonObject["payment"].toString())
        val items = parseMediaGallery(it.jsonObject["items"]?.jsonArray)

//        var imageUrl = it.jsonObject["extension_attributes"]?.jsonObject?.get("image_url")?.toString()
//            ?.replace("\\\\", "/")
//            ?.replace("\\/", "/") ?: ""


//        println("djfsdjfjw342 " + imageUrl)

        val orderHistory = OrderItem(
            entity_id = entityId,
            billingAddressId = billingAddressId,
            createdAt = createdAt,
            customerEmail = customerEmail,
            customerFirstname = customerFirstname,
            customerId = customerId,
            customerIsGuest = customerIsQuest,
            customerLastname = customerLastname,
            grandTotal = grandTotal,
            incrementId = incrementId,
            items = items,
            status = status,
            payment = paymentMethod,
//            image_url = imageUrl
        )
        listTest.add(orderHistory)
    }

    return listTest
}

private fun parsePaymentInfo(json: String): PaymentDomain {
    val jsonObject = Json.parseToJsonElement(json).jsonObject

    val paymentMethod = jsonObject["method"].toString().replace("\"", "")

    return PaymentDomain(paymentMethod = paymentMethod)
}

private fun parseMediaGallery(json: JsonArray?): List<ItemDomain> {
    val listTest = mutableListOf<ItemDomain>()
    json?.forEach {
        val itemId = it.jsonObject["item_id"].toString().toInt()
        val name = it.jsonObject["name"].toString().replace("\"", "")
        val priceIncludeTax = it.jsonObject["price_incl_tax"].toString().toDouble()
        val sku = it.jsonObject["sku"].toString().replace("\"", "")

        val itemXX = ItemDomain(
            itemId = itemId,
            name = name,
            price_incl_tax = priceIncludeTax,
            sku = sku,
        )
        listTest.add(itemXX)
    }
    return listTest
}