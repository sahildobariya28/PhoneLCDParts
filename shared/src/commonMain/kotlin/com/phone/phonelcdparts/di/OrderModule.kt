package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.service.OrdersService
import com.phone.phonelcdparts.data.repository.OrdersRepository
import com.phone.phonelcdparts.domain.use_case.OrderUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val orderModule: Module = module {
    single<OrdersService> { OrdersRepository() } bind OrdersService::class
    single { OrderUseCase(get()) }
}