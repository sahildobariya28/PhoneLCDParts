package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.CategoryItem
import io.ktor.http.HttpStatusCode

interface CategoryService {
    suspend fun getCategories(): Pair<List<CategoryItem>, HttpStatusCode?>
}