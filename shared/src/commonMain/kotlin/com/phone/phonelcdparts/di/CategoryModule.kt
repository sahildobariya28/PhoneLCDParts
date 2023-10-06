package com.phone.phonelcdparts.di

import com.phone.phonelcdparts.data.service.CategoryService
import com.phone.phonelcdparts.data.repository.CategoryRepository
import com.phone.phonelcdparts.domain.use_case.CategoryUseCase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val categoryModule: Module = module {
    single<CategoryService> { CategoryRepository() } bind CategoryService::class
    single { CategoryUseCase(get()) }
}