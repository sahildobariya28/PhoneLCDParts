package com.phone.phonelcdparts.presentation.screen.maincontent

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class MainScreenState: Parcelable{
    object Home: MainScreenState()
    object Category: MainScreenState()
    object Order: MainScreenState()
    object Account: MainScreenState()
    object Cart: MainScreenState()
    object ProductInfo: MainScreenState()
    object OrderDetail: MainScreenState()
}