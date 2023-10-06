package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.service.NewProductService
import com.phone.phonelcdparts.domain.model.NewProductModel
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

class NewProductRepository: NewProductService {

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

    override suspend fun getNewProduct(url: String): Pair<List<NewProductModel>, HttpStatusCode?> {
        return try {
            val response = client.get {
                url(url)
            }

            if(response.status.isSuccess()){
                val newProducts: List<NewProductModel> = response.body()
                Pair(newProducts, response.status)
            }else{
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {
            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun getBestSeller(url: String): Pair<List<NewProductModel>, HttpStatusCode?> {
        return try {
            val response = client.get {
                url(url)
            }

            if(response.status.isSuccess()){
                val newProducts: List<NewProductModel> = response.body()
                Pair(newProducts, response.status)
            }else{
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {
            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }

}