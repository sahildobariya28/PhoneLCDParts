package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.ItemDTO
import com.phone.phonelcdparts.data.model.OrderDTO
import com.phone.phonelcdparts.data.model.PaymentDTO
import com.phone.phonelcdparts.domain.model.ItemDomain
import com.phone.phonelcdparts.domain.model.OrderItem
import com.phone.phonelcdparts.domain.model.PaymentDomain

fun OrderDTO.toDomainOrderItem(): OrderItem {
    return OrderItem(
        entity_id = this.entity_id,
        billingAddressId = this.billingAddressId,
        createdAt = this.createdAt,
        customerEmail = this.customerEmail,
        customerFirstname = this.customerFirstname,
        customerId = this.customerId,
        customerIsGuest = this.customerIsGuest,
        customerLastname = this.customerLastname,
        grandTotal = this.grandTotal,
        incrementId = this.incrementId,
        items = this.items.map { it.toDomainItemDomain() },
        status = this.status,
        payment = this.payment.toDomainPayment()
    )
}

fun ItemDTO.toDomainItemDomain(): ItemDomain {
    return ItemDomain(
        itemId = this.itemId,
        name = this.name,
        price_incl_tax = this.price_incl_tax,
        sku = this.sku
    )
}

fun PaymentDTO.toDomainPayment(): PaymentDomain {
    return PaymentDomain(
        paymentMethod = this.paymentMethod
    )
}