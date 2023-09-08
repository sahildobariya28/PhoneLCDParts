package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.CartDTO
import com.phone.phonelcdparts.domain.model.CartItem

fun CartDTO.toDomainCart(): CartItem {
    return CartItem(
        itemId = this.itemId,
        name = this.name,
        price = this.price,
        productType = this.productType,
        qty = this.qty,
        quoteId = this.quoteId,
        sku = this.sku
    )
}