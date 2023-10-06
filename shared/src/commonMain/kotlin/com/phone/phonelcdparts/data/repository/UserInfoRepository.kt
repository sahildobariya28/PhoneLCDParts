package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.applicationContext
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.service.UserInfoService
import com.phone.phonelcdparts.domain.model.UserDetailModel
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

class UserInfoRepository : UserInfoService {

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

    override suspend fun getUserInfo(): Pair<UserDetailModel?, HttpStatusCode?> {
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

    private fun parseUserInfo(json: String): UserDetailModel {
        val jsonObject = Json.parseToJsonElement(json).jsonObject

        val id = jsonObject["id"].toString().toInt()
        val email = jsonObject["email"].toString().replace("\"", "")
        val firstname = jsonObject["firstname"].toString().replace("\"", "")
        val lastname = jsonObject["lastname"].toString().replace("\"", "")

        var region = ""
        var regionId = 0
        var countryId = ""
        var street = ""
        var company = ""
        var telephone = ""
        var postcode = ""
        var city = ""

        jsonObject["addresses"]?.jsonArray?.forEach {
            region = it.jsonObject["region"].toString().replace("\"", "")
            regionId = it.jsonObject["region_id"].toString().toInt()
            countryId = it.jsonObject["country_id"].toString().replace("\"", "")
            street = it.jsonObject["street"].toString().replace("\"", "")
            company = it.jsonObject["company"].toString().replace("\"", "")
            telephone = it.jsonObject["telephone"].toString().replace("\"", "")
            postcode = it.jsonObject["postcode"].toString().replace("\"", "")
            city = it.jsonObject["city"].toString().replace("\"", "")
        }

        return UserDetailModel(
            id = id,
            email = email,
            firstname = firstname,
            lastname = lastname,
            region = region,
            region_id = regionId,
            country_id = countryId,
            street = street,
            company = company,
            telephone = telephone,
            postcode = postcode,
            city = city,
        )
    }

}
