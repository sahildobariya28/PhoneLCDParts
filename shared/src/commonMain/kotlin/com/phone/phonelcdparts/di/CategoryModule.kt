package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.remote.CategoryService
import com.phone.phonelcdparts.data.repository.CategoryServiceImpl
import com.phone.phonelcdparts.domain.use_case.CategoryUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val categoryModule: Module = module {
    single<CategoryService> { CategoryServiceImpl() } bind CategoryService::class
    single { CategoryUseCase(get()) }
}