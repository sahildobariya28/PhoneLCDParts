package com.phone.phonelcdparts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PostModel(
    val body: String,
    val id: Int,
    val title: String
)
