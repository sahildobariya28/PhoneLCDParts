package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.ProductDTO
import com.phone.phonelcdparts.domain.model.ProductItem

fun ProductDTO.toDomainProductItem(): ProductItem {
    return ProductItem(
        id = this.id,
        sku = this.sku,
        name = this.name,
        attribute_set_id = this.attribute_set_id,
        price = this.price,
        status = this.status,
        visibility = this.visibility,
        type_id = this.type_id,
        created_at = this.created_at,
        updated_at = this.updated_at,
        weight = this.weight,
        media_gallery_entries = this.media_gallery_entries
    )
}
