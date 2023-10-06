package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.service.BrandService
import com.phone.phonelcdparts.data.repository.BrandRepository
import com.phone.phonelcdparts.domain.use_case.BrandUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val brandModule: Module = module {
    single<BrandService> { BrandRepository() } bind BrandService::class
    single { BrandUseCase(get()) }
}