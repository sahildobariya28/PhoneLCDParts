package com.phone.phonelcdparts.util

import androidx.compose.runtime.Composable

@Composable
expect fun SafeArea(content: @Composable (top:Float, bottom:Float) -> Unit)