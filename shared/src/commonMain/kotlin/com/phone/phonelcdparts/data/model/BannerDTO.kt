package com.phone.phonelcdparts.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BannerDTO(
    var image: String?,
    var custom_link: String?,
)


