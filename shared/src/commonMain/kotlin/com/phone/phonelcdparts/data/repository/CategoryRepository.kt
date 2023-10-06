package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.service.CategoryService
import com.phone.phonelcdparts.domain.model.CategoryItem
import com.phone.phonelcdparts.domain.model.CustomAttribute
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

class CategoryRepository: CategoryService {

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

    override suspend fun getCategories(): Pair<List<CategoryItem>, HttpStatusCode?> {

        return try {
            val response = client.get {
                url(HttpRoutes.CATEGORY_BY_PARENT_ID + "2")
            }
            if(response.status.isSuccess()){
                val banners = parseCategory(response.body())

                Pair(banners, response.status)
            }else{
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {

            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }

    private fun parseCategory(json: String): List<CategoryItem> {

        val listTest = mutableListOf<CategoryItem>()
        val jsonObject = Json.parseToJsonElement(json).jsonObject
        jsonObject["items"]?.jsonArray?.forEach {

            val test = CategoryItem(
                id = it.jsonObject["id"].toString().toInt(),
                parentId = it.jsonObject["parent_id"].toString().toInt(),
                name = it.jsonObject["name"].toString().replace("\"", ""),
                isActive = it.jsonObject["is_active"].toString().toBoolean(),
                position = it.jsonObject["position"].toString().toInt(),
                level = it.jsonObject["level"].toString().toInt(),
                children = it.jsonObject["children"].toString().replace("\"", ""),
                includeInMenu = it.jsonObject["include_in_menu"].toString().toBoolean(),
                path = it.jsonObject["path"].toString().replace("\"", ""),
                customAttributes = parseAttributeValue(it.jsonObject["custom_attributes"]?.jsonArray)
            )
            listTest.add(test)

        }

        return listTest

    }

    private fun parseAttributeValue(json: JsonArray?): List<CustomAttribute> {
        val listTest = mutableListOf<CustomAttribute>()
        json?.forEach {
            val test = CustomAttribute(
                attributeCode = it.jsonObject["attribute_code"].toString(),
                value = it.jsonObject["value"].toString()
            )
            listTest.add(test)
        }
        return listTest
    }

}