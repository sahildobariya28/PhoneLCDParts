package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.model.NewProductDTO
import com.phone.phonelcdparts.data.repository.NewProductServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface NewProductService {

    suspend fun getNewProduct(url: String = HttpRoutes.NEW_PRODUCT): Pair<List<NewProductDTO>, HttpStatusCode?>
    suspend fun getBestSeller(url: String = HttpRoutes.BEST_SELLER): Pair<List<NewProductDTO>, HttpStatusCode?>

}