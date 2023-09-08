package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.CartService
import com.phone.phonelcdparts.data.repository.CartServiceImpl
import com.phone.phonelcdparts.domain.use_case.CartUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val cartModule: Module = module {
    single<CartService> {CartServiceImpl()} bind CartService::class
    single { CartUseCase(get()) }
}