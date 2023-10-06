package com.phone.phonelcdparts.presentation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.phone.phonelcdparts.applicationContext
import com.phone.phonelcdparts.presentation.screen.sign_in.SignInScreen
import com.phone.phonelcdparts.presentation.screen.sign_up.AdditionalInfoScreen
import com.phone.phonelcdparts.presentation.screen.sign_up.CompanyInfoScreen
import com.phone.phonelcdparts.screen.sign_up.SignUpScreen
import com.phone.phonelcdparts.presentation.screen.sign_up.SuccessScreen
import com.phone.phonelcdparts.presentation.screen.splash.SplashScreen
import com.phone.phonelcdparts.presentation.screen.maincontent.MainScreenContainer
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter

@Composable
fun ApplicationContainer() {
    val router: Router<ApplicationScreenState> = rememberRouter(
        ApplicationScreenState::class, listOf(
            ApplicationScreenState.Splash
        ))

    RoutedContent(
        router = router,
        animation = stackAnimation(slide())
    ) { screen ->
        when(screen){
            is ApplicationScreenState.Splash -> SplashScreen(
                signInRoute = {
                    if (applicationContext?.getString("login_token") != null && applicationContext?.getString("login_token")!!.isNotEmpty()) {
                        router.bringToFront(ApplicationScreenState.ContentScreen)
                    }else{
                        router.bringToFront(ApplicationScreenState.SignIn)
                    }
                }
            )

            is ApplicationScreenState.SignIn -> SignInScreen(homeRoute = {
                router.bringToFront(ApplicationScreenState.ContentScreen)
            }, signUpRoute = {
                router.bringToFront(ApplicationScreenState.SignUp)
            })

            is ApplicationScreenState.SignUp -> SignUpScreen(companyInfoRouter = {
                router.bringToFront(ApplicationScreenState.CompanyInfo)
            }, onTermConditionClick = {
                router.bringToFront(ApplicationScreenState.CompanyInfo)
            }, back = {
                router.pop()
            })
            is ApplicationScreenState.CompanyInfo -> CompanyInfoScreen(additionalInfoRouter = {
                    router.bringToFront(ApplicationScreenState.AdditionalInfo)
                },back = {
                    router.pop()
                })

            is ApplicationScreenState.AdditionalInfo -> AdditionalInfoScreen(successScreenRouter = {
                router.bringToFront(ApplicationScreenState.SuccessScreen)
            },back = {
                router.pop()
            })
            is ApplicationScreenState.SuccessScreen -> SuccessScreen(successScreenRouter = {
                router.bringToFront(ApplicationScreenState.ContentScreen)
            })
            is ApplicationScreenState.ContentScreen -> MainScreenContainer()
        }
    }
}