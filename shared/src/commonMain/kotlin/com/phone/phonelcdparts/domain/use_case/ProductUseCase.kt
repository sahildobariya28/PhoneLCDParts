package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainProductItem
import com.phone.phonelcdparts.data.remote.ProductService
import com.phone.phonelcdparts.domain.model.ProductItem
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductUseCase(private val repository: ProductService) {

    operator fun invoke(): Flow<Resource<List<ProductItem>>> = flow {

        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.getProducts()

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = if (fetchedBanners.isNotEmpty()) fetchedBanners.map { it.toDomainProductItem() } else emptyList()
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

    operator fun invoke(searchText: String, page: Int): Flow<Resource<List<ProductItem>>> = flow {
        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.getProductsByName(searchText, page)

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = if (fetchedBanners.isNotEmpty()) fetchedBanners.map { it.toDomainProductItem() } else emptyList()
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