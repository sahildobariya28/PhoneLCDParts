package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.service.BannerService
import com.phone.phonelcdparts.data.repository.BannerRepository
import com.phone.phonelcdparts.domain.use_case.BannerUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val bannerModule: Module = module {
    single<BannerService> { BannerRepository() } bind BannerService::class
    single { BannerUseCase(get()) }
}

