package com.phone.phonelcdparts.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDTO(
    @SerialName("item_id")
    val itemId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Double,
    @SerialName("product_type")
    val productType: String,
    @SerialName("qty")
    val qty: Int,
    @SerialName("quote_id")
    val quoteId: String,
    @SerialName("sku")
    val sku: String
)