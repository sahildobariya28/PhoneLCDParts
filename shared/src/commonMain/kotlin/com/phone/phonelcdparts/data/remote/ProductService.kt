package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.model.BannerDTO
import com.phone.phonelcdparts.data.repository.ProductServiceImpl
import com.phone.phonelcdparts.data.model.ProductDTO
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ProductService {

    suspend fun getProducts(): Pair<List<ProductDTO>, HttpStatusCode?>
    suspend fun getProductsByName(searchText: String, page: Int): Pair<List<ProductDTO>, HttpStatusCode?>

}