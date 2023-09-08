package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.ProductInfoService
import com.phone.phonelcdparts.data.repository.ProductInfoServiceImpl
import com.phone.phonelcdparts.domain.use_case.ProductMediaUseCase
import com.phone.phonelcdparts.domain.use_case.ProductInfoUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val productInfoModule: Module = module {
    single<ProductInfoService> { ProductInfoServiceImpl() } bind ProductInfoService::class
    single { ProductInfoUseCase(get()) }
    single { ProductMediaUseCase(get()) }
}