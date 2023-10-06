package com.phone.phonelcdparts.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BannerModel(
    var image: String?,
    var custom_link: String?,
)


