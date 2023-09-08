package com.phone.phonelcdparts.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaGalleryEntry(
    @SerialName("disabled")
    val disabled: Boolean,
    @SerialName("file")
    val file: String,
    @SerialName("id")
    val id: Int,
    @SerialName("label")
    val label: String?,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("position")
    val position: Int,
    @SerialName("types")
    val types: List<String>
)