package com.phone.phonelcdparts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PostItem(
    val body: String,
    val id: Int,
    val title: String
)
