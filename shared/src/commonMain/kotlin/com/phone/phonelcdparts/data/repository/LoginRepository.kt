package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.data.service.LoginService
import com.phone.phonelcdparts.domain.model.LoginModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginRepository: LoginService {

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
            headers.append("Authorization", "Bearer pm46kzmjn6wp67e5pk79h652m22zd8v9")
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun getLogin(
        username: String,
        password: String
    ): Pair<String, HttpStatusCode?> {
        val request = LoginModel(username, password)
        val json = Json.encodeToString(request)
        return try {
            val response: HttpResponse = client.post {
                url("http://192.168.29.236/phonelcdparts/pub/rest/V1/integration/customer/token")
                contentType(ContentType.Application.Json)
                body = json
            }
            if(response.status.isSuccess()){
                val banners: String = response.body()

                Pair(banners, response.status)
            }else{
                Pair("", response.status)
            }

        } catch (e: Exception) {
            Pair("", HttpStatusCode.InternalServerError)
        } finally {
            client.close()
        }
    }

}