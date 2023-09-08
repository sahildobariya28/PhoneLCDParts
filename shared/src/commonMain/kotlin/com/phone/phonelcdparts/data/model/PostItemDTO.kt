package com.phone.phonelcdparts.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostItemDTO(
    @SerialName("body")
    val body: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int
)