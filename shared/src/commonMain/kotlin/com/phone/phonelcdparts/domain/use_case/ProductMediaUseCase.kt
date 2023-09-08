package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainProductMediaGallery
import com.phone.phonelcdparts.data.remote.ProductInfoService
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductMediaUseCase(private val repository: ProductInfoService) {

    operator fun invoke(sku: String): Flow<Resource<List<String>>> = flow {

        try {
            emit(Resource.Loading())
            val(productInfoDTO, status) = repository.getProductMedia(sku)

            if (status != null) {

                if (status.isSuccess()){

//                    val domainData = if (productInfoDTO != null) productInfoDTO.toDomainProductInfo() else null
                    val domainData = if (productInfoDTO?.isNotEmpty() == true) productInfoDTO.map { it.toDomainProductMediaGallery() } else emptyList()
                    emit(Resource.Success(data = domainData, status = status))
                }else{
                    emit(Resource.Error(data = null, status = status))
                }
            }else{
                emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
            }
        } catch (e: Exception) {
            emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
        }
    }

}