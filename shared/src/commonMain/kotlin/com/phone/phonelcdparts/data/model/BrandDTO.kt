package com.phone.phonelcdparts.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BrandDTO(
    var image: String?,
    var custom_link: String?,
)