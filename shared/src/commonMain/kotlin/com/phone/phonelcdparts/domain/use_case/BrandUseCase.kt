package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainBrand
import com.phone.phonelcdparts.data.remote.BrandService
import com.phone.phonelcdparts.domain.model.BrandItem
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BrandUseCase(private val repository: BrandService) {

    operator fun invoke(): Flow<Resource<List<BrandItem>>> = flow {
        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.getBrands()

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = if (fetchedBanners.isNotEmpty()) fetchedBanners.map { it.toDomainBrand() } else emptyList()
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