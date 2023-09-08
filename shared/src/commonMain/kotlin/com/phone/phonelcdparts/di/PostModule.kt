package com.phone.phonelcdparts.di


import com.phone.phonelcdparts.data.remote.PostService
import com.phone.phonelcdparts.data.repository.PostRepositoryImpl
import com.phone.phonelcdparts.domain.use_case.PostUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val postModule: Module = module {
    single<PostService> { PostRepositoryImpl() } bind PostService::class
    single { PostUseCase(get()) }
}