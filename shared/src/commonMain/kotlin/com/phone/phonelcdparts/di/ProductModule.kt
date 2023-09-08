package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.ProductService
import com.phone.phonelcdparts.data.repository.ProductServiceImpl
import com.phone.phonelcdparts.domain.use_case.ProductUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val productModule: Module = module {
    single<ProductService> { ProductServiceImpl() } bind ProductService::class
    single { ProductUseCase(get()) }
}