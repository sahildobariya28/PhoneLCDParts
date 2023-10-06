package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.service.BannerService
import com.phone.phonelcdparts.domain.model.BannerModel
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

class BannerRepository: BannerService {

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

    override suspend fun getBanners(): Pair<List<BannerModel>, HttpStatusCode?> {

        return try {
            val response = client.get {
                url(HttpRoutes.BANNER)
            }
            if(response.status.isSuccess()){
                val banners = extractBannerContents(response.body())

                Pair(banners, response.status)
            }else{
                Pair(emptyList(), response.status)
            }

        } catch (e: Exception) {

            Pair(emptyList(), HttpStatusCode.InternalServerError)
        }
    }

    private fun extractBannerContents(jsonString: String): List<BannerModel> {
        val imageRegex = """\\"image\\":\\"(.*?)\\"""".toRegex()
        val urlRegex = """url=\\\\\\"(.*?)\\\\\\"""".toRegex()

        val imageMatches = imageRegex.findAll(jsonString)
        val urlMatches = urlRegex.findAll(jsonString)

        val bannerContents = mutableListOf<BannerModel>()

        for (match in imageMatches) {
            val imageUrl = match.groupValues[1]
            val urlMatch = urlMatches.find { it.range.first > match.range.last }
            val url = urlMatch?.groupValues?.get(1) ?: ""

            bannerContents.add(
                BannerModel(
                    imageUrl.replace(
                        """\/""",
                        "/"
                    ), url.replace("""\/""", "/")
                )
            )
        }

        return bannerContents
    }
}