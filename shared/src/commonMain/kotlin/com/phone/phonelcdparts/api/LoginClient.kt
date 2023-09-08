package com.phone.phonelcdparts.api

import com.phone.phonelcdparts.model.login.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


val client = HttpClient(){
    install(HttpTimeout) {
        requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
    }
    headers {
        append("Authorization", "Bearer pm46kzmjn6wp67e5pk79h652m22zd8v9")
    }


}
@OptIn(InternalAPI::class)
suspend fun login(username: String, password: String): Result<String> {

    val request = LoginRequest(username, password)
    val json = Json.encodeToString(request)

    return try {
        val response: HttpResponse = client.post {
            url("http://192.168.29.236/phonelcdparts/pub/rest/V1/integration/customer/token")
            contentType(ContentType.Application.Json)
            body = json
        }
        Result.success(response.body())
    } catch (e: Exception) {
        Result.failure(e)
    } finally {
        client.close()
    }
}

