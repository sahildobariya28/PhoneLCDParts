package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.BannerModel
import io.ktor.http.HttpStatusCode

interface BannerService {
    suspend fun getBanners(): Pair<List<BannerModel>, HttpStatusCode?>

}