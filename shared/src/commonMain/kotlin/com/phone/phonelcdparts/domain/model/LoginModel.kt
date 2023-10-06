package com.phone.phonelcdparts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginModel(
    val username: String,
    val password: String
)