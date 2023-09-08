package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.BannerService
import com.phone.phonelcdparts.data.remote.NewProductService
import com.phone.phonelcdparts.data.repository.BannerServiceImpl
import com.phone.phonelcdparts.data.repository.NewProductServiceImpl
import com.phone.phonelcdparts.domain.use_case.BannerUseCase
import com.phone.phonelcdparts.domain.use_case.NewProductUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val bannerModule: Module = module {
    single<BannerService> { BannerServiceImpl() } bind BannerService::class
    single { BannerUseCase(get()) }
}

