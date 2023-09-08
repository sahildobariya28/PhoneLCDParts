package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainPost
import com.phone.phonelcdparts.data.remote.PostService
import com.phone.phonelcdparts.domain.model.PostItem
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostUseCase(private val repository: PostService) {

    operator fun invoke(): Flow<Resource<List<PostItem>>> = flow {
        try {
            emit(Resource.Loading())

            val (fetchedBanners, status) = repository.getPostList()

            if (status != null) {
                if (status.isSuccess()){
                    val domainData = if (fetchedBanners.isNotEmpty()) fetchedBanners.map { it.toDomainPost() } else emptyList()
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