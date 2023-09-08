package com.phone.phonelcdparts.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("firstname")
    val firstname: String,
    @SerialName("lastname")
    val lastname: String,
    @SerialName("region")
    val region: String,
    @SerialName("region_id")
    val region_id: Int,
    @SerialName("country_id")
    val country_id: String,
    @SerialName("street")
    val street: String,
    @SerialName("company")
    val company: String,
    @SerialName("telephone")
    val telephone: String,
    @SerialName("postcode")
    val postcode: String,
    @SerialName("city")
    val city: String
)