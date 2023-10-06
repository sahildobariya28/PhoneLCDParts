package com.phone.phonelcdparts.presentation.screen.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
inline fun AdditionalInfoScreen(crossinline successScreenRouter: () -> Unit, crossinline back: () -> Unit) {

    val additionalInfoViewModel = getViewModel(key = "additional_info", factory = viewModelFactory {
        AdditionalInfoViewModel()
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
                            text = "Additional Information",
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

                        PopupMenu(
                            additionalInfoViewModel.que1,
                            listOf(
                                "Ebay",
                                "Instagram",
                                "Facebook",
                                "Facebook Group",
                                "YouTube",
                                "Twitter",
                                "Linkedin",
                                "Google Search",
                                "Other"
                            )
                        ) {
                            additionalInfoViewModel.que1 = it
                        }

                        Spacer(Modifier.height(15.dp))

                        PopupMenu(
                            additionalInfoViewModel.que2,
                            listOf(
                                "Repair Store",
                                "Wholesaler",
                                "Individual",
                                "Other"
                            )
                        ) {
                            additionalInfoViewModel.que2 = it
                        }

                        Spacer(Modifier.height(15.dp))

                        PopupMenu(
                            additionalInfoViewModel.que3,
                            listOf(
                                "Yes",
                                "No"
                            )
                        ) {
                            additionalInfoViewModel.que3 = it
                        }

                        Spacer(Modifier.height(15.dp))

                        SimpleTextField("Enter Company Location", text = additionalInfoViewModel.representativeText) {
                            additionalInfoViewModel.representativeText = it
                        }

                        Spacer(Modifier.height(30.dp))

                        Box(
                            modifier = Modifier.height(65.dp).fillMaxWidth()
                                .padding(horizontal = 30.dp).clip(
                                    RoundedCornerShape(50.dp)
                                ).background(Colors.text_theme).clickable {
                                    successScreenRouter()
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

@Composable
inline fun PopupMenu(
    que1Text: String,
    menuList: List<String>,
    crossinline onMenuItemClick: (String) -> Unit,

    ) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .clip(
                RoundedCornerShape(35.dp)
            )
            .background(Colors.text_field_bg_color)
            .clickable {
                isMenuExpanded = true
            }
            .padding(horizontal = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.height(65.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(imageVector = Icons.Default.Email, contentDescription = null)
            Spacer(Modifier.width(10.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = que1Text,
                fontSize = 14.sp
            )
            Spacer(Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }
        if (isMenuExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.text_field_bg_color)
                    .clip(RoundedCornerShape(35.dp))
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                menuList.forEach { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onMenuItemClick(item)
                                isMenuExpanded = false
                            }
                            .padding(horizontal = 15.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}
