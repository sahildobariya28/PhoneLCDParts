package com.phone.phonelcdparts.util

import androidx.compose.runtime.Composable
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGFloat
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIApplication

@Composable
actual fun SafeArea(content: @Composable (top:Float, bottom:Float) -> Unit) {
    val statusBarHeight = UIApplication.sharedApplication.statusBarFrame.useContents { size.height }
    val window = UIApplication.sharedApplication.keyWindow!!
    var paddingTop: CGFloat
    var paddingBottom: CGFloat
    if (window.respondsToSelector(NSSelectorFromString("safeAreaInsets"))) {
        paddingTop = window.safeAreaInsets.useContents { top }
        paddingBottom = window.safeAreaInsets.useContents { bottom }
    } else {
        paddingTop = statusBarHeight
        paddingBottom = 0.0
    }
    content.invoke((paddingTop).toFloat(), paddingBottom.toFloat())
}