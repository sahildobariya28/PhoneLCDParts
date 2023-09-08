package com.phone.phonelcdparts.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageLoader(image: String): MutableState<ImageBitmap?> {

    val imageBitmap = remember { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(Unit) {
        imageBitmap.value = resource(image).readBytes().toImageBitmap()
    }
    return imageBitmap

}