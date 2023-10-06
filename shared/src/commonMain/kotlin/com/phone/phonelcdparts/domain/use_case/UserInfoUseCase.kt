package com.phone.phonelcdparts.domain.use_case

import com.phone.phonelcdparts.core.Resource
import com.phone.phonelcdparts.data.service.UserInfoService
import com.phone.phonelcdparts.domain.model.UserDetailModel
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserInfoUseCase(private val repository: UserInfoService) {

    operator fun invoke(): Flow<Resource<UserDetailModel?>> = flow {

        try {
            emit(Resource.Loading())
            val (userInfo, status) = repository.getUserInfo()

            if (status != null) {

                if (status.isSuccess()) {
                    emit(Resource.Success(data = userInfo, status = status))
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