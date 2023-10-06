package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.service.ProductInfoService
import com.phone.phonelcdparts.domain.model.ProductDetailModel
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
import kotlinx.serialization.json.jsonObject

class ProductInfoRepository : ProductInfoService {

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
        install(DefaultRequest) {
            headers.append("Authorization", "Bearer ${HttpRoutes.BAREAR_TOKEN}")
        }
    }

    override suspend fun getProductInfo(sku: String): Pair<ProductDetailModel?, HttpStatusCode?> {

        return try {

            val response = client.get {
                url("${HttpRoutes.PRODUCT_INFO}$sku")
            }
            if (response.status.isSuccess()) {
                val banners = parseProductInfo(response.body())

                Pair(banners, response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {

            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    private fun parseProductInfo(json: String): ProductDetailModel {
        val jsonObject = Json.parseToJsonElement(json).jsonObject

        val sku = jsonObject["sku"].toString().replace("\"", "")
        val name = jsonObject["name"].toString().replace("\"", "")
        val priceString = jsonObject["price"]?.toString()

        val price: Double = if (priceString != null && priceString != "null") {
            priceString.toDouble()
        } else {
            0.0
        }

        val imageUrl = jsonObject["media_gallery_entries"]?.jsonObject?.get("file").toString().replace("\"", "")

        return ProductDetailModel(
                sku = sku,
                name = name,
                price = price,
                imagePath = imageUrl,
            )

    }
}