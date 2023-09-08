package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.PostItemDTO
import com.phone.phonelcdparts.domain.model.PostItem

fun PostItemDTO.toDomainPost(): PostItem {
    return PostItem(
        id = this.id,
        title = this.title,
        body = this.body
    )
}