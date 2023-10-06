package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.service.LoginService
import com.phone.phonelcdparts.data.repository.LoginRepository
import com.phone.phonelcdparts.domain.use_case.LoginUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule: Module = module {
    single<LoginService> { LoginRepository() } bind LoginService::class
    single { LoginUseCase(get()) }
}