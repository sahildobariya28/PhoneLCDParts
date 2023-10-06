package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.ProductModel
import io.ktor.http.HttpStatusCode

interface ProductService {

    suspend fun getProducts(): Pair<List<ProductModel>, HttpStatusCode?>
    suspend fun getProductsByName(searchText: String, page: Int): Pair<List<ProductModel>, HttpStatusCode?>

}