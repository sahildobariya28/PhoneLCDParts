package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.NewProductDTO
import com.phone.phonelcdparts.domain.model.NewProductItem

fun NewProductDTO.toDomainNewProductItem(): NewProductItem {
    return NewProductItem(
        id = this.id,
        image = this.image,
        isinstock = this.isinstock,
        name = this.name,
        price = this.price,
        sku = this.sku
    )
}