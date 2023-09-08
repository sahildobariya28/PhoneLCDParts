package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.applicationContext
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.model.BannerDTO
import com.phone.phonelcdparts.data.model.UserInfoDTO
import com.phone.phonelcdparts.data.remote.UserInfoService
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
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class UserInfoServiceImpl() : UserInfoService {

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
            headers.append("Authorization", "Bearer ${
                applicationContext?.getString("login_token")
                    ?.replace("\"", "")}")
        }
    }

    override suspend fun getUserInfo(): Pair<UserInfoDTO?, HttpStatusCode?> {
        val response = client.get {
            url(HttpRoutes.USER_INFO)
        }
        return try {
            if (response.status.isSuccess()) {
                val banners = parseUserInfo(response.body())

                Pair(banners, response.status)
            } else {
                Pair(null, response.status)
            }
        } catch (e: Exception) {

            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    private fun parseUserInfo(json: String): UserInfoDTO? {
        val jsonObject = Json.parseToJsonElement(json).jsonObject

        val id = jsonObject["id"].toString().toInt()
        val email = jsonObject["email"].toString().replace("\"", "")
        val firstname = jsonObject["firstname"].toString().replace("\"", "")
        val lastname = jsonObject["lastname"].toString().replace("\"", "")

        var region: String = ""
        var region_id: Int = 0
        var country_id: String = ""
        var street: String = ""
        var company: String = ""
        var telephone: String = ""
        var postcode: String = ""
        var city: String = ""

        jsonObject["addresses"]?.jsonArray?.forEach {
            region = it.jsonObject["region"].toString().replace("\"", "")
            region_id = it.jsonObject["region_id"].toString().toInt()
            country_id = it.jsonObject["country_id"].toString().replace("\"", "")
            street = it.jsonObject["street"].toString().replace("\"", "")
            company = it.jsonObject["company"].toString().replace("\"", "")
            telephone = it.jsonObject["telephone"].toString().replace("\"", "")
            postcode = it.jsonObject["postcode"].toString().replace("\"", "")
            city = it.jsonObject["city"].toString().replace("\"", "")
        }

        return UserInfoDTO(
            id = id,
            email = email,
            firstname = firstname,
            lastname = lastname,
            region = region,
            region_id = region_id,
            country_id = country_id,
            street = street,
            company = company,
            telephone = telephone,
            postcode = postcode,
            city = city,
        )
    }

}
