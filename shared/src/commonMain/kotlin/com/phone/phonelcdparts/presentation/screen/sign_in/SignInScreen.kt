package com.phone.phonelcdparts.presentation.screen.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.applicationContext
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.presentation.component.PasswordTextField
import com.phone.phonelcdparts.presentation.component.SimpleTextField
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.ktor.http.isSuccess

@Composable
inline fun SignInScreen(crossinline homeRoute: () -> Unit, crossinline signUpRoute: () -> Unit) {

    MaterialTheme {
        Surface {

            val signInViewModel = getViewModel(
                key = "view-model",
                factory = viewModelFactory {
                    SignInViewModel()
                }
            )
            val loginState by signInViewModel.accessToken.collectAsState()

            val isLoading = remember { mutableStateOf(false) }

            Box(modifier = Modifier.fillMaxSize()) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Spacer(Modifier.height(40.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                        textAlign = TextAlign.Start,
                        text = "Hey!\nWelcome back",
                        fontSize = 30.sp,
                        color = Colors.theme,
                        fontWeight = FontWeight.Bold
                    )


                    Spacer(Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                        text = "Sign In to your account",
                        textAlign = TextAlign.Start,
                        color = Colors.theme,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.height(50.dp))

//                    SimpleTextField("Enter Email", "jenul1@phonelcdparts.com") { newText -> signInViewModel.emailText = newText }
                    SimpleTextField(
                        "Enter Email",
                        signInViewModel.emailText
                    ) { newText -> signInViewModel.emailText = newText }

                    Spacer(Modifier.height(15.dp))

//                    PasswordTextField(hint = "Enter Password", text = "SoftAdminMage2022", isPasswordToggle = signInViewModel.isPasswordToggle, onTextChange = {signInViewModel.passwordText = it}, onPasswordToggleChange = {signInViewModel.isPasswordToggle = it})
                    PasswordTextField(
                        hint = "Enter Password",
                        text = signInViewModel.passwordText,
                        isPasswordToggle = signInViewModel.isPasswordToggle,
                        onTextChange = { signInViewModel.passwordText = it },
                        onPasswordToggleChange = { signInViewModel.isPasswordToggle = it })

                    Spacer(Modifier.height(15.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(end = 40.dp),
                        text = "Forgot password?",
                        textAlign = TextAlign.End,
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(Modifier.height(30.dp))
                    val responseText = remember { mutableStateOf("response") }

                    if (loginState.isLoading) {
                        isLoading.value = true
                    } else if (loginState.status.isSuccess()) {
                        loginState.data?.let { token ->
                            if (applicationContext?.getString("login_token") == null) {
                                applicationContext?.put(
                                    "login_token",
                                    token as String
                                )
                                isLoading.value = false
                                responseText.value = token as String
                                homeRoute()
                            }
                        }
                    } else if (!loginState.status.isSuccess()) {
                        isLoading.value = false
                    }

                    Row(
                        modifier = Modifier
                            .height(65.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .clip(
                                RoundedCornerShape(50.dp)
                            )
                            .background(Colors.text_theme)
                            .clickable {
                                signInViewModel.getLogin(
                                    signInViewModel.emailText,
                                    signInViewModel.passwordText
                                )
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sign In",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )

                    }
                    Text(responseText.value)

                    Spacer(Modifier.height(30.dp))

                    AgreementText {

                        signUpRoute()

                    }

                }

                if (isLoading.value) {
                    ShowLoginDialog()
                }
            }
        }
    }
}

@Composable
fun ShowLoginDialog() {
    Box(Modifier.fillMaxSize().background(Color(0x30000000)), contentAlignment = Alignment.Center) {
        Column(
            Modifier.fillMaxWidth(.8f).clip(RoundedCornerShape(15.dp)).background(Color.White)
                .padding(vertical = 30.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(Modifier.padding(vertical = 20.dp))
            Text(
                "Please Wait...",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.text_theme
            )
        }
    }
}

@Composable
inline fun AgreementText(crossinline onSignUpClick: () -> Unit) {
    val agreementText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append("Don’t have an Account? ")
        }

        val signUpTextStart = length
        append("Sign Up")
        val signUpTextEnd = length

        addStringAnnotation("URL", "signUp", signUpTextStart, signUpTextEnd)
    }

    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        text = agreementText,
        style = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp),
        onClick = { offset ->
            agreementText.getStringAnnotations("URL", offset, offset).firstOrNull()
                ?.let { annotation ->
                    if (annotation.item == "signUp") {
                        onSignUpClick()
                    }
                }
        }
    )
}