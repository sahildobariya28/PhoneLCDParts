package com.phone.phonelcdparts.presentation.screen.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Back
import com.phone.phonelcdparts.presentation.component.SimpleTextField
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
inline fun CompanyInfoScreen(crossinline additionalInfoRouter: () -> Unit, crossinline back: () -> Unit) {

    val companyInfoViewModel = getViewModel(key = "company_info",
        factory = viewModelFactory {
            CompanyInfoViewModel()
        })
    Surface {

        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {

                    Box(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Company Information",
                            fontSize = 20.sp,
                            color = Colors.theme,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Image(
                        modifier = Modifier.size(60.dp).clickable { back() }.padding(20.dp),
                        imageVector = MyIconPack.Back,
                        contentDescription = null
                    )

                }


                Box(modifier = Modifier.fillMaxSize()) {

                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(Modifier.height(20.dp))

                        SimpleTextField(
                            hint = "Enter Company Location",
                            text = companyInfoViewModel.companyLocationText
                        ) { companyInfoViewModel.companyLocationText = it }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            hint = "Enter Street Address",
                            text = companyInfoViewModel.streetAddressText
                        ) { companyInfoViewModel.streetAddressText = it }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            hint = "Enter City",
                            text = companyInfoViewModel.cityText
                        ) { companyInfoViewModel.cityText = it }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            hint = "Enter Zip Code",
                            text = companyInfoViewModel.zipCodeText
                        ) { companyInfoViewModel.zipCodeText = it }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            hint = "Enter State",
                            text = companyInfoViewModel.stateText
                        ) { companyInfoViewModel.stateText = it }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            hint = "Enter Country",
                            text = companyInfoViewModel.countryText
                        ) { companyInfoViewModel.countryText = it }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            hint = "Enter VAT Number",
                            text = companyInfoViewModel.vatNoText
                        ) { companyInfoViewModel.vatNoText = it }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField(
                            hint = "Enter Phone Number",
                            text = companyInfoViewModel.phNoText
                        ) { companyInfoViewModel.phNoText = it }

                        Spacer(Modifier.height(30.dp))

                        Box(
                            modifier = Modifier.height(65.dp).fillMaxWidth()
                                .padding(horizontal = 30.dp).clip(
                                    RoundedCornerShape(50.dp)
                                ).background(Colors.text_theme).clickable {
                                    additionalInfoRouter()
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "Next", color = Color.White, fontWeight = FontWeight.SemiBold
                            )

                        }

                        Spacer(Modifier.height(30.dp))


                    }
                }
            }
        }

    }
}

