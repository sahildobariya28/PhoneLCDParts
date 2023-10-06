package com.phone.phonelcdparts.data.repository


import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.domain.model.MediaGalleryModel
import com.phone.phonelcdparts.data.service.ProductService
import com.phone.phonelcdparts.domain.model.ProductModel
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

class ProductRepository : ProductService {

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

    override suspend fun getProducts(): Pair<List<ProductModel>, HttpStatusCode?> {

        return try {
            val response = client.get {
                url(HttpRoutes.PRODUCT)
            }
            if (response.status.isSuccess()) {
                val product = parseProduct(response.body())

                Pair(product, response.status)
            } else {
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {

            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun getProductsByName(searchText: String, page: Int): Pair<List<ProductModel>, HttpStatusCode?> {
        return try {
            val response = client.get {
                url("${HttpRoutes.PRODUCT}&searchCriteria[filterGroups][0][filters][0][field]=name&searchCriteria[filterGroups][0][filters][0][condition_type]=like&searchCriteria[filterGroups][0][filters][0][value]=%25${searchText}%25&searchCriteria[current_page]=$page")
            }
            if (response.status.isSuccess()) {
                val product = parseProduct(response.body())

                Pair(product, response.status)
            } else {
                Pair(emptyList(), response.status)
            }
        } catch (e: Exception) {
            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }

    private fun parseProduct(json: String): List<ProductModel> {

        val listTest = mutableListOf<ProductModel>()
        val jsonObject = Json.parseToJsonElement(json).jsonObject

        jsonObject["items"]?.jsonArray?.forEach {

            val mediaGalleryJsonArray = it.jsonObject["media_gallery_entries"]?.jsonArray
            val mediaGallery = if (mediaGalleryJsonArray != null) {
                parseMediaGallery(mediaGalleryJsonArray)
            } else {
                emptyList()
            }

            val test = ProductModel(
                id = it.jsonObject["id"].toString().toInt(),
                sku = it.jsonObject["sku"].toString().replace("\"", ""),
                name = it.jsonObject["name"].toString().replace("\"", ""),
                attribute_set_id = it.jsonObject["attribute_set_id"].toString().toInt(),
                price = it.jsonObject["price"].toString().toDouble(),
                status = it.jsonObject["status"].toString().toInt(),
                visibility = it.jsonObject["visibility"].toString().toInt(),
                type_id = it.jsonObject["type_id"].toString().replace("\"", ""),
                created_at = it.jsonObject["created_at"].toString().replace("\"", ""),
                updated_at = it.jsonObject["updated_at"].toString().replace("\"", ""),
                weight = it.jsonObject["weight"].toString().toDouble(),
                media_gallery_entries = mediaGallery
            )
            listTest.add(test)
        }
        return listTest
    }

    private fun parseMediaGallery(json: JsonArray?): List<MediaGalleryModel> {
        if (json == null) {
            return emptyList()
        }

        val listTest = mutableListOf<MediaGalleryModel>()
        json.forEach {
            val test = MediaGalleryModel(
                id = it.jsonObject["id"].toString().toInt(),
                mediaType = it.jsonObject["media_type"].toString().replace("\"", ""),
                label = it.jsonObject["label"].toString().replace("\"", ""),
                position = it.jsonObject["position"].toString().toInt(),
                disabled = it.jsonObject["disabled"].toString().toBoolean(),
                types = parseTypes(it.jsonObject["types"]?.jsonArray),
                file = it.jsonObject["file"].toString().replace("\"", "")
            )
            listTest.add(test)
        }
        return listTest
    }

    private fun parseTypes(json: JsonArray?): List<String> {
        val listTest = mutableListOf<String>()
        json?.forEach {
            listTest.add(it.toString().replace("\"", ""))
        }
        return listTest
    }
}
