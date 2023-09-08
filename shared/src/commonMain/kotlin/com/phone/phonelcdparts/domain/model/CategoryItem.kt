package com.phone.phonelcdparts.domain.model

import com.phone.phonelcdparts.data.model.CustomAttribute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CategoryItem(
    @SerialName("children")
    val children: String,
    @SerialName("custom_attributes")
    val customAttributes: List<CustomAttribute>,
    @SerialName("id")
    val id: Int,
    @SerialName("include_in_menu")
    val includeInMenu: Boolean,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("level")
    val level: Int,
    @SerialName("name")
    val name: String,
    @SerialName("parent_id")
    val parentId: Int,
    @SerialName("path")
    val path: String,
    @SerialName("position")
    val position: Int,
)
@Serializable
data class CustomAttribute(
    @SerialName("attribute_code")
    val attributeCode: String,
    @SerialName("value")
    val value: String
)