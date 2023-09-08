package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainCart
import com.phone.phonelcdparts.data.remote.CartService
import com.phone.phonelcdparts.domain.model.CartItem
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartUseCase(private val repository: CartService) {

    operator fun invoke(): Flow<Resource<List<CartItem>>> = flow {
        try {
            emit(Resource.Loading())
            val(fetchedCart, status) = repository.getCartItem()

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = if (fetchedCart.isNotEmpty()) fetchedCart.map { it.toDomainCart() } else emptyList()
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