package com.phone.phonelcdparts.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewProductItem(
    @SerialName("id")
    val id: String,
    @SerialName("image")
    val image: String,
    @SerialName("Isinstock")
    val isinstock: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Double,
    @SerialName("sku")
    val sku: String
)