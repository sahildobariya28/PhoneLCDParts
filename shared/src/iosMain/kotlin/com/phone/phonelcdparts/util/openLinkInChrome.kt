package com.phone.phonelcdparts.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openLink(context: Any, url: String) {
    val nsUrl = NSURL(string = url)
    UIApplication.sharedApplication.openURL(nsUrl)
}