package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.OrdersService
import com.phone.phonelcdparts.data.repository.OrdersServiceImpl
import com.phone.phonelcdparts.domain.use_case.OrderUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val orderModule: Module = module {
    single<OrdersService> { OrdersServiceImpl() } bind OrdersService::class
    single { OrderUseCase(get()) }
}