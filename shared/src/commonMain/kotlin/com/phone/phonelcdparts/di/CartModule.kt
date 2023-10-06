package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.service.CartService
import com.phone.phonelcdparts.data.repository.CartRepository
import com.phone.phonelcdparts.domain.use_case.CartUseCase
import com.phone.phonelcdparts.domain.use_case.GetQuoteIdUseCase
import com.phone.phonelcdparts.domain.use_case.InsertCartItemUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val cartModule: Module = module {
    single<CartService> {CartRepository()} bind CartService::class
    single { CartUseCase(get()) }
    single { InsertCartItemUseCase(get()) }
    single { GetQuoteIdUseCase(get()) }
}