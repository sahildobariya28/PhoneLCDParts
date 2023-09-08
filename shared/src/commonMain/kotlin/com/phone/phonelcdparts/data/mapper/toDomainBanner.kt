package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.BannerDTO
import com.phone.phonelcdparts.data.model.NewProductDTO
import com.phone.phonelcdparts.domain.model.BannerItem

fun BannerDTO.toDomainBanner(): BannerItem {
    return BannerItem(
        image = this.image,
        custom_link = this.custom_link,
    )
}


