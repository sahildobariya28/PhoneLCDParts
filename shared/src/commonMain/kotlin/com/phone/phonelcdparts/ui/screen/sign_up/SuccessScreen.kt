package com.phone.phonelcdparts.ui.screen.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Checked


@Composable
fun SuccessScreen(successScreenRouter: () -> Unit) {

    Surface {

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Image(modifier = Modifier.size(240.dp), imageVector = MyIconPack.Checked, contentDescription = null)

            Spacer(Modifier.height(40.dp))

            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                textAlign = TextAlign.Center,
                text = "Sign Up Success",
                fontSize = 26.sp,
                color = Colors.text_theme,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                text = "Your Sign Up has been successfully\ncompleted.",
                textAlign = TextAlign.Center,
                color = Colors.text_hint,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}