package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.service.ProductInfoService
import com.phone.phonelcdparts.data.repository.ProductInfoRepository
import com.phone.phonelcdparts.domain.use_case.ProductInfoUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val productInfoModule: Module = module {
    single<ProductInfoService> { ProductInfoRepository() } bind ProductInfoService::class
    single { ProductInfoUseCase(get()) }
}