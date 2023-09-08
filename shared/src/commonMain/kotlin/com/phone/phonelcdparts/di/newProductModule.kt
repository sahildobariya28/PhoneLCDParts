package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.NewProductService
import com.phone.phonelcdparts.data.repository.NewProductServiceImpl
import com.phone.phonelcdparts.domain.use_case.BestSellerUseCase
import com.phone.phonelcdparts.domain.use_case.NewProductUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val newProductModule: Module = module {
    single<NewProductService> { NewProductServiceImpl() } bind NewProductService::class
    single { NewProductUseCase(get()) }
    single { BestSellerUseCase(get()) }
}