package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.data.model.MediaGalleryEntry
import com.phone.phonelcdparts.data.model.ProductInfoDTO
import io.ktor.http.HttpStatusCode

interface ProductInfoService {

    suspend fun getProductInfo(sku: String): Pair<ProductInfoDTO?, HttpStatusCode?>

    suspend fun getProductMedia(sku: String): Pair<List<MediaGalleryEntry>?, HttpStatusCode?>

}