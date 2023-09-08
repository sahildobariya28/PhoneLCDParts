package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.UserInfoService
import com.phone.phonelcdparts.data.repository.UserInfoServiceImpl
import com.phone.phonelcdparts.domain.use_case.UserInfoUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val userInfoModule: Module = module {
    single<UserInfoService> { UserInfoServiceImpl() } bind UserInfoService::class
    single { UserInfoUseCase(get()) }
}