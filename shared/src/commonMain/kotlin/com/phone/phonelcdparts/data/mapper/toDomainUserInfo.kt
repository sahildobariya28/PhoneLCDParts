package com.phone.phonelcdparts.data.mapper

import com.phone.phonelcdparts.data.model.UserInfoDTO
import com.phone.phonelcdparts.domain.model.UserInfo

fun UserInfoDTO.toDomainUserInfo():UserInfo {
    return UserInfo(
        id = this.id,
        email = this.email,
        firstname = this.firstname,
        lastname = this.lastname,
        region = this.region,
        region_id = this.region_id,
        country_id = this.country_id,
        street = this.street,
        company = this.company,
        telephone = this.telephone,
        postcode = this.postcode,
        city = this.city
    )
}