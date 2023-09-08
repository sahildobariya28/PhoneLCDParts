package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.model.PostItemDTO
import com.phone.phonelcdparts.data.remote.PostService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PostRepositoryImpl : PostService {
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
    }
    override suspend fun getPostList(): Pair<List<PostItemDTO>, HttpStatusCode?> {

        return try {
            val response = client.get {
                url(HttpRoutes.POSTS)
            }

            if(response.status.isSuccess()){
                val banners: List<PostItemDTO> = response.body()

                Pair(banners, response.status)
            }else{
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {

            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }
}