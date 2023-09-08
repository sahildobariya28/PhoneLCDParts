package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.BrandService
import com.phone.phonelcdparts.data.repository.BrandServiceImpl
import com.phone.phonelcdparts.domain.use_case.BrandUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val brandModule: Module = module {
    single<BrandService> { BrandServiceImpl() } bind BrandService::class
    single { BrandUseCase(get()) }
}