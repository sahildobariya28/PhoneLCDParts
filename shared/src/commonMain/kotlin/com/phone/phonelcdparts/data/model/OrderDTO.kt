package com.phone.phonelcdparts.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDTO(
    @SerialName("entity_id")
    val entity_id: Int,
    @SerialName("billing_address_id")
    val billingAddressId: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("customer_email")
    val customerEmail: String,
    @SerialName("customer_firstname")
    val customerFirstname: String,
    @SerialName("customer_id")
    val customerId: Int,
    @SerialName("customer_is_guest")
    val customerIsGuest: Int,
    @SerialName("customer_lastname")
    val customerLastname: String,
    @SerialName("grand_total")
    val grandTotal: Double,
    @SerialName("increment_id")
    val incrementId: String,
    @SerialName("items")
    val items: List<ItemDTO>,
    @SerialName("status")
    val status: String,
    @SerialName("payment")
    val payment: PaymentDTO,
)

@Serializable
data class ItemDTO(

    @SerialName("item_id")
    val itemId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("price_incl_tax")
    val price_incl_tax: Double,
    @SerialName("sku")
    val sku: String,
)

@Serializable
data class PaymentDTO(
    @SerialName("method")
    val paymentMethod: String
)

@Serializable
data class AddressDTO(
    @SerialName("address_type")
    val addressType: String,
    @SerialName("city")
    val city: String,
    @SerialName("company")
    val company: String,
    @SerialName("country_id")
    val countryId: String,
    @SerialName("customer_address_id")
    val customerAddressId: Int,
    @SerialName("email")
    val email: String,
    @SerialName("entity_id")
    val entityId: Int,
    @SerialName("firstname")
    val firstname: String,
    @SerialName("lastname")
    val lastname: String,
    @SerialName("parent_id")
    val parentId: Int,
    @SerialName("postcode")
    val postcode: String,
    @SerialName("region")
    val region: String,
    @SerialName("region_code")
    val regionCode: String,
    @SerialName("region_id")
    val regionId: Int,
    @SerialName("street")
    val street: List<String>,
    @SerialName("telephone")
    val telephone: String
)