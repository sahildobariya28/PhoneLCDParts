package com.phone.phonelcdparts.di


import com.phone.phonelcdparts.data.service.PostService
import com.phone.phonelcdparts.data.repository.PostRepository
import com.phone.phonelcdparts.domain.use_case.PostUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val postModule: Module = module {
    single<PostService> { PostRepository() } bind PostService::class
    single { PostUseCase(get()) }
}