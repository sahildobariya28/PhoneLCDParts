package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.MediaGalleryEntry
import com.phone.phonelcdparts.data.model.ProductInfoDTO
import com.phone.phonelcdparts.domain.model.ProductInfo

fun ProductInfoDTO.toDomainProductInfo(): ProductInfo {
    return ProductInfo(
        name = this.name,
        sku = this.sku,
        price = this.price,
        imagePath = if (this.mediaGalleryEntries.isEmpty()) {""} else this.mediaGalleryEntries[0].file
    )
}

fun MediaGalleryEntry.toDomainProductMediaGallery(): String {
    return this.file
}