package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.mapper.toDomainUserInfo
import com.phone.phonelcdparts.data.remote.UserInfoService
import com.phone.phonelcdparts.domain.model.UserInfo
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserInfoUseCase(private val repository: UserInfoService) {

    operator fun invoke(): Flow<Resource<UserInfo?>> = flow {

        try {
            emit(Resource.Loading())
            val (userInfo, status) = repository.getUserInfo()

            if (status != null) {

                if (status.isSuccess()) {
                    val domainData =
                        if (userInfo != null) userInfo.toDomainUserInfo() else null
                    emit(Resource.Success(data = domainData, status = status))
                } else {
                    emit(Resource.Error(data = null, status = status))
                }
            } else {
                emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
            }
        } catch (e: Exception) {
            emit(Resource.Error(data = null, status = HttpStatusCode.InternalServerError))
        }
    }
}