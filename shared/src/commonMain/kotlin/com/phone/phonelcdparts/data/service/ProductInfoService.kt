package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.ProductDetailModel
import io.ktor.http.HttpStatusCode

interface ProductInfoService {

    suspend fun getProductInfo(sku: String): Pair<ProductDetailModel?, HttpStatusCode?>

}