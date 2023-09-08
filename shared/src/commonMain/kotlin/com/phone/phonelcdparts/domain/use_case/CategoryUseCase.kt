package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainBanner
import com.phone.phonelcdparts.data.mapper.toDomainCategoryItem
import com.phone.phonelcdparts.data.remote.BannerService
import com.phone.phonelcdparts.data.remote.CategoryService
import com.phone.phonelcdparts.domain.model.BannerItem
import com.phone.phonelcdparts.domain.model.CategoryItem
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryUseCase(private val repository: CategoryService) {
    operator fun invoke(): Flow<Resource<List<CategoryItem>>> = flow {

        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.getCategories()

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = if (fetchedBanners.isNotEmpty()) fetchedBanners.map { it.toDomainCategoryItem() } else emptyList()
                    emit(Resource.Success(data = domainData, status = status))
                }else{
                    emit(Resource.Error(data = emptyList(), status = status))
                }
            }else{
                emit(Resource.Error(data = emptyList(), status = HttpStatusCode.InternalServerError))
            }
        } catch (e: Exception) {
            emit(Resource.Error(data = emptyList(), status = HttpStatusCode.InternalServerError))
        }
    }
}