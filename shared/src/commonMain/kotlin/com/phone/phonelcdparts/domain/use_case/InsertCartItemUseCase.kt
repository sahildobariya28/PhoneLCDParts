package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.service.CartService
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertCartItemUseCase(private val repository: CartService) {
    operator fun invoke(sku: String, count: Int = 1, quote_id:String): Flow<Resource<Int>> = flow {
        try {
            emit(Resource.Loading())
            val(cartItemCount, status) = repository.insertCartItem(sku, count, quote_id)

            if (status != null) {
                if (status.isSuccess()){

                    emit(Resource.Success(data = cartItemCount, status = status))
                }else{
                    emit(Resource.Error(data = -1, status = status))
                }
            }else{
                emit(Resource.Error(data = -1, status = HttpStatusCode.InternalServerError))
            }
        } catch (e: Exception) {
            emit(Resource.Error(data = -1, status = HttpStatusCode.InternalServerError))
        }
    }
}