package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.BrandModel
import io.ktor.http.HttpStatusCode

interface BrandService {

    suspend fun getBrands(): Pair<List<BrandModel>, HttpStatusCode?>

}