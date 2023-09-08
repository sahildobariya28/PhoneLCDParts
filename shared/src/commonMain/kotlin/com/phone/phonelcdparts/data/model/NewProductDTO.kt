package com.phone.phonelcdparts.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewProductDTO(
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