package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.service.ProductInfoService
import com.phone.phonelcdparts.domain.model.ProductDetailModel
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductInfoUseCase(private val repository: ProductInfoService) {

    operator fun invoke(sku: String): Flow<Resource<ProductDetailModel?>> = flow {

        try {
            emit(Resource.Loading())
            val(productInfoDTO, status) = repository.getProductInfo(sku)

            if (status != null) {

                if (status.isSuccess()){
                    emit(Resource.Success(data = productInfoDTO, status = status))
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