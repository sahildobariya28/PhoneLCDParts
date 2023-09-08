import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.phone.phonelcdparts.App
import com.phone.phonelcdparts.states.Action
import com.phone.phonelcdparts.store
import com.phone.phonelcdparts.util.BrowserWrapper
import com.phone.phonelcdparts.util.KMMPreference
import io.github.xxfast.decompose.LocalComponentContext
import platform.UIKit.UIViewController


//fun Main(pref: KMMPreference, safeAreaInsetsBottom: CGFloat, safeAreaInsetsTop: CGFloat, browserWrapper: BrowserWrapper): UIViewController = ComposeUIViewController {
//
//    val lifecycle = LifecycleRegistry()
//    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
//    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
//        MaterialTheme {
//            App(pref, browserWrapper,safeAreaInsetsBottom.toFloat(), safeAreaInsetsBottom.toFloat())
//        }
//    }
//}

fun Main(
    pref: KMMPreference,
    browserWrapper: BrowserWrapper,
): UIViewController = ComposeUIViewController{

    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        MaterialTheme {
                    App(pref, browserWrapper)
        }
    }
}


//fun Main(pref: KMMPreference, browserWrapper: BrowserWrapper): UIViewController = ComposeUIViewController{
//    val lifecycle = LifecycleRegistry()
//    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
//    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
//        MaterialTheme {
//            App(pref, browserWrapper)
//        }
//    }
//}

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}