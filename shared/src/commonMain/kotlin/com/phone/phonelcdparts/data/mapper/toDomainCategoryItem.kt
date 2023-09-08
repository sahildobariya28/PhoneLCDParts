package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.BannerDTO
import com.phone.phonelcdparts.data.model.CategoryDTO
import com.phone.phonelcdparts.domain.model.BannerItem
import com.phone.phonelcdparts.domain.model.CategoryItem

fun CategoryDTO.toDomainCategoryItem(): CategoryItem {
    return CategoryItem(
        children = this.children,
        id = this.id,
        includeInMenu = this.includeInMenu,
        isActive = this.isActive,
        level = this.level,
        name = this.name,
        parentId = this.parentId,
        path = this.path,
        position = this.position,
        customAttributes = this.customAttributes
    )
}
