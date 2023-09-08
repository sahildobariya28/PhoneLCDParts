package com.phone.phonelcdparts.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object AndroidAppContext {
    lateinit var context: Context
}

actual fun openLink(context: Any, url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    (context as? Context)?.startActivity(intent)
}