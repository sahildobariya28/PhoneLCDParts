package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.applicationContext
import com.phone.phonelcdparts.data.model.BannerDTO
import com.phone.phonelcdparts.data.model.UserInfoDTO
import com.phone.phonelcdparts.data.repository.UserInfoServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface UserInfoService {

    suspend fun getUserInfo(): Pair<UserInfoDTO?, HttpStatusCode?>

}