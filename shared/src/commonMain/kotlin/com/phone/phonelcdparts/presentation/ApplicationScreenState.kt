package com.phone.phonelcdparts.presentation

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class ApplicationScreenState : Parcelable{
    object Splash: ApplicationScreenState()
    object SignIn: ApplicationScreenState()
    object SignUp: ApplicationScreenState()
    object CompanyInfo: ApplicationScreenState() // TODO remove implement inside signup
    object AdditionalInfo: ApplicationScreenState() // TODO remove implement inside signup
    object SuccessScreen: ApplicationScreenState() // TODO remove implement inside signup
    object ContentScreen: ApplicationScreenState()
}
