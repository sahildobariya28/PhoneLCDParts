package com.phone.phonelcdparts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BrandModel(
    var image: String?,
    var custom_link: String?,
)