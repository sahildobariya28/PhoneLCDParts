package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.domain.model.NewProductModel
import io.ktor.http.HttpStatusCode

interface NewProductService {

    suspend fun getNewProduct(url: String = HttpRoutes.NEW_PRODUCT): Pair<List<NewProductModel>, HttpStatusCode?>
    suspend fun getBestSeller(url: String = HttpRoutes.BEST_SELLER): Pair<List<NewProductModel>, HttpStatusCode?>

}