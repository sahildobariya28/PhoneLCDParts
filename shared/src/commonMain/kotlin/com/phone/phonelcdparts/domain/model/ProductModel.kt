package com.phone.phonelcdparts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    val id: Int,
    val sku: String,
    val name: String,
    val attribute_set_id: Int,
    val price: Double,
    val status: Int,
    val visibility: Int,
    val type_id: String,
    val created_at: String,
    val updated_at: String,
    val weight: Double,
    val media_gallery_entries: List<MediaGalleryModel>,
)

//@Serializable
//data class ExtensionAttributes(
//    val website_ids: List<Int>,
//    val category_links: List<CategoryLink>
//)

//@Serializable
//data class CategoryLink(
//    val position: Int,
//    val category_id: String
//)

//@Serializable
//data class CustomAttribute(
//    val attribute_code: String,
//    val value: String
//)