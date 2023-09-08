package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.data.model.BannerDTO
import io.ktor.http.HttpStatusCode

interface BannerService {

    suspend fun getBanners(): Pair<List<BannerDTO>, HttpStatusCode?>

}