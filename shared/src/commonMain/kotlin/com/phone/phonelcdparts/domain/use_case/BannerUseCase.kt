package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainBanner
import com.phone.phonelcdparts.data.remote.BannerService
import com.phone.phonelcdparts.domain.model.BannerItem
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BannerUseCase(private val repository: BannerService) {

    operator fun invoke(): Flow<Resource<List<BannerItem>>> = flow {

        try {
            emit(Resource.Loading())
            val(fetchedBanners, status) = repository.getBanners()

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = if (fetchedBanners.isNotEmpty()) fetchedBanners.map { it.toDomainBanner() } else emptyList()
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