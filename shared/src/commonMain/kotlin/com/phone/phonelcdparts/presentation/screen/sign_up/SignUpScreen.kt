package com.phone.phonelcdparts.screen.sign_up

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Back
import com.phone.phonelcdparts.presentation.component.PasswordTextField
import com.phone.phonelcdparts.presentation.component.SimpleTextField
import com.phone.phonelcdparts.presentation.screen.sign_up.SignUpViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
inline fun SignUpScreen(
    crossinline companyInfoRouter: () -> Unit,
    crossinline onTermConditionClick: () -> Unit,
    crossinline back: () -> Unit
) {

    val signUpViewModel =
        getViewModel(key = "sign_up", factory = viewModelFactory { SignUpViewModel() })

    Surface {

        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Box(modifier = Modifier.fillMaxWidth()) {

                    Image(
                        modifier = Modifier
                            .size(60.dp)
                            .clickable { back() }
                            .padding(20.dp),
                        imageVector = MyIconPack.Back,
                        contentDescription = null
                    )

                }

                Box(modifier = Modifier.fillMaxSize()) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Spacer(Modifier.height(40.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                            textAlign = TextAlign.Start,
                            text = "Hello, There",
                            fontSize = 30.sp,
                            color = Colors.theme,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                            text = "Enter your information below to get\nstarted your trips",
                            textAlign = TextAlign.Start,
                            color = Colors.theme,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(Modifier.height(50.dp))

                        SimpleTextField(
                            hint = "Enter First Name",
                            text = signUpViewModel.firstNameText
                        ) {
                            signUpViewModel.firstNameText = it
                        }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            "Enter Last Name",
                            text = signUpViewModel.lastNameText
                        ) {
                            signUpViewModel.lastNameText = it
                        }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            "Enter Email",
                            text = signUpViewModel.emailText
                        ) {
                            signUpViewModel.emailText = it
                        }

                        Spacer(Modifier.height(15.dp))

                        PasswordTextField(hint = "Enter Password",
                            text = signUpViewModel.passwordText,
                            isPasswordToggle = signUpViewModel.isPasswordToggle,
                            onTextChange = { signUpViewModel.passwordText = it },
                            onPasswordToggleChange = {signUpViewModel.isPasswordToggle = it})

                        Spacer(Modifier.height(15.dp))

                        PasswordTextField(hint = "Enter Conform Password",
                            text = signUpViewModel.conformPasswordText,
                            isPasswordToggle = signUpViewModel.isConformPasswordToggle,
                            onTextChange = { signUpViewModel.conformPasswordText = it },
                            onPasswordToggleChange = {signUpViewModel.isConformPasswordToggle = it})

                        Spacer(Modifier.height(15.dp))

                        TermCheckBox(onTermConditionClick)

                        Spacer(Modifier.height(30.dp))

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
                                    companyInfoRouter()
                                },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Create Account",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )

                        }

                    }
                }
            }
        }

    }

}


@Composable
inline fun TermCheckBox(crossinline onTermConditionClick: () -> Unit) {
    Row(modifier = Modifier.padding(horizontal = 30.dp)) {
        val isTermCondition = remember { mutableStateOf(false) }
        Checkbox(
            checked = isTermCondition.value,
            onCheckedChange = {
                isTermCondition.value = it
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Colors.theme,
                uncheckedColor = Colors.theme,
                checkmarkColor = Colors.white
            )
        )
        val agreementText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("By creating an account, you agree to our\n")
            }

            val signUpTextStart = length
            append("Term & Conditions")
            val signUpTextEnd = length

            addStringAnnotation("URL", "term&condition", signUpTextStart, signUpTextEnd)
        }

        ClickableText(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            text = agreementText,
            style = TextStyle(textAlign = TextAlign.Start, fontSize = 12.sp),
            onClick = { offset ->
                agreementText.getStringAnnotations("URL", offset, offset).firstOrNull()
                    ?.let { annotation ->
                        if (annotation.item == "term&condition") {
                            onTermConditionClick()
                        }
                    }
            }
        )

    }

}