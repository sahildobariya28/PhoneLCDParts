package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.BrandDTO
import com.phone.phonelcdparts.domain.model.BrandItem

fun BrandDTO.toDomainBrand(): BrandItem {
    return BrandItem(
        image = this.image,
        custom_link = this.custom_link,
    )
}