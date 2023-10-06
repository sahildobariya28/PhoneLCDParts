package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.UserDetailModel
import io.ktor.http.HttpStatusCode

interface UserInfoService {

    suspend fun getUserInfo(): Pair<UserDetailModel?, HttpStatusCode?>

}