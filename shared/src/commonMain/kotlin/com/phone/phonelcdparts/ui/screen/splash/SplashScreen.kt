package com.phone.phonelcdparts.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.bottomSafeArea
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.topSafeArea
import com.phone.phonelcdparts.util.ImageLoader
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(signInRoute: () -> Unit) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Colors.theme)
                .padding(top = topSafeArea.dp, bottom = bottomSafeArea.dp),
            contentAlignment = Alignment.Center
        ) {

            ImageLoader("images/logo.png").value?.let {
                Image(
                    modifier = Modifier.fillMaxWidth(.6f),
                    bitmap = it,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        LaunchedEffect(true) {
            delay(3000) // Delay for 2000 milliseconds (2 seconds)
            signInRoute() // Navigate to the main screen using the provided NavController
        }
    }
}