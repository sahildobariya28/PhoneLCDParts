package com.phone.phonelcdparts.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            loginModule,
            bannerModule,
            brandModule,
            newProductModule,
            productInfoModule,
            cartModule,
            userInfoModule,
            categoryModule,
            productModule,
            orderModule,
            postModule
        )
    }.koin
}