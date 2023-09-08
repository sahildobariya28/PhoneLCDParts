package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.model.BrandDTO
import com.phone.phonelcdparts.data.repository.BrandServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface BrandService {

    suspend fun getBrands(): Pair<List<BrandDTO>, HttpStatusCode?>

}