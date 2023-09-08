package com.phone.phonelcdparts.ui.screen.maincontent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.phone.phonelcdparts.bottomSafeArea
import com.phone.phonelcdparts.domain.model.OrderItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logobiglight
import com.phone.phonelcdparts.ui.BackHandler
import com.phone.phonelcdparts.ui.component.TopBarUi
import com.phone.phonelcdparts.ui.screen.cart.CartScreen
import com.phone.phonelcdparts.ui.screen.cart.CartScreenViewModel
import com.phone.phonelcdparts.ui.screen.category.CategoryScreen
import com.phone.phonelcdparts.ui.screen.category.CategoryViewModel
import com.phone.phonelcdparts.ui.screen.home.HomeScreen
import com.phone.phonelcdparts.ui.screen.home.NewHomeScreenViewModel
import com.phone.phonelcdparts.ui.screen.order_list.OrderDetailScreen
import com.phone.phonelcdparts.ui.screen.order_list.OrderScreen
import com.phone.phonelcdparts.ui.screen.order_list.OrdersViewModel
import com.phone.phonelcdparts.ui.screen.product.ProductInfoScreen
import com.phone.phonelcdparts.ui.screen.profile.AccountScreen
import com.phone.phonelcdparts.ui.screen.profile.AccountScreenViewModel
import com.phone.phonelcdparts.ui.screen.search.SearchScreen
import com.phone.phonelcdparts.ui.screen.search.SearchScreenViewModel
import com.phone.phonelcdparts.ui.screen.search.TestingSearchScreen
import com.phone.phonelcdparts.util.toDp
import com.phone.phonelcdparts.util.toPx
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreenContainer() {
    var mainScreenViewModel = viewModelFactory { MainScreenViewModel() }.createViewModel()
    var homeScreenViewModel = viewModelFactory { NewHomeScreenViewModel() }.createViewModel()
    var categoryScreenViewModel = viewModelFactory { CategoryViewModel() }.createViewModel()
    var cartScreenViewModel = viewModelFactory { CartScreenViewModel() }.createViewModel()
    var accountScreenViewModel = viewModelFactory { AccountScreenViewModel() }.createViewModel()
    var ordersViewModel = viewModelFactory { OrdersViewModel() }.createViewModel()
    var searchScreenViewModel = viewModelFactory { SearchScreenViewModel() }.createViewModel()

    val screens = getBottomListItem()
    val router: Router<MainScreenState> = rememberRouter(
        MainScreenState::class, listOf(
            MainScreenState.Home
        )
    )

    Surface {
        NavigationDrawer(screens, router, mainScreenViewModel)
        BodyContent(
            screens,
            router,
            mainScreenViewModel,
            cartScreenViewModel,
            homeScreenViewModel,
            categoryScreenViewModel,
            accountScreenViewModel,
            ordersViewModel,
            searchScreenViewModel
        )
    }
}

@Composable
fun BodyContent(
    screens: List<BottomNavState>,
    router: Router<MainScreenState>,
    mainScreenViewModel: MainScreenViewModel,
    cartScreenViewModel: CartScreenViewModel,
    homeScreenViewModel: NewHomeScreenViewModel,
    categoryScreenViewModel: CategoryViewModel,
    accountScreenViewModel: AccountScreenViewModel,
    ordersViewModel: OrdersViewModel,
    searchScreenViewModel: SearchScreenViewModel
) {

    var sku = remember { mutableStateOf("") }
    var selectedOrderItem = remember { mutableStateOf(Any()) }
    //navigation drawer
    val offSetAnim by animateDpAsState(targetValue = if (mainScreenViewModel.navigateClick) 253.dp else 0.dp, animationSpec = tween(durationMillis = 500))
    val scaleAnim by animateFloatAsState(targetValue = if (mainScreenViewModel.navigateClick) 0.6f else 1.0f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .scale(scaleAnim)
            .offset(x = offSetAnim)
            .clip(
                if (mainScreenViewModel.navigateClick) RoundedCornerShape(40.dp) else RoundedCornerShape(
                    0.dp
                )
            )
            .background(Color.White)
    ) {
        Column(Modifier.fillMaxSize()) {

            TopBarUi(searchScreenViewModel, cartScreenViewModel, router, {
                mainScreenViewModel.navigateClick = !mainScreenViewModel.navigateClick
            }, {searchScreenViewModel.isSearch = !searchScreenViewModel.isSearch})

            Box(
                Modifier.weight(1f).background(Color.White),
                contentAlignment = Alignment.BottomCenter
            ) {

                Column(Modifier.fillMaxSize()) {
                    Box(
                        Modifier.weight(1f).background(Color.White),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        RoutedContent(
                            router = router,
                            animation = stackAnimation(slide(animationSpec = tween(100)))
                        ) { screen ->
                            when (screen) {

                                is MainScreenState.Home -> {
                                    loadScreen(HomeScreen(
                                        homeScreenViewModel,
                                    ) {
                                        sku.value = it
                                        router.bringToFront(MainScreenState.ProductInfo)
                                    })
                                }

                                is MainScreenState.Category -> {
                                    loadScreen(CategoryScreen(categoryScreenViewModel))
                                }

                                is MainScreenState.Order -> {

                                    loadScreen(OrderScreen(ordersViewModel) {
                                        selectedOrderItem.value = it
                                        router.bringToFront(MainScreenState.OrderDetail)
                                    })
                                }

                                is MainScreenState.Account -> {
                                    loadScreen(AccountScreen(accountScreenViewModel))
                                }

                                is MainScreenState.Cart -> {
                                    loadScreen(CartScreen(cartScreenViewModel) {
                                        sku.value = it
                                        router.bringToFront(MainScreenState.ProductInfo)
                                    })
                                }

                                is MainScreenState.ProductInfo -> {
                                    loadScreen(ProductInfoScreen(sku.value))
                                }

                                is MainScreenState.OrderDetail -> {
                                    if (selectedOrderItem.value is OrderItem) {
                                        loadScreen(OrderDetailScreen(selectedOrderItem.value as OrderItem))
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Gray.copy(alpha = 0.0f),
                                            Color.Gray.copy(alpha = 0.2f)
                                        )
                                    )
                                )
                        )


                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().background(Color.White).padding(bottom = bottomSafeArea.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        screens.forEach { bottomNavState ->
                            val coroutineScope = rememberCoroutineScope()
                            var isSelected: Boolean = router.stack.value.items.last().configuration == bottomNavState.route || if (router.stack.value.items.last().configuration == MainScreenState.OrderDetail) MainScreenState.Order == bottomNavState.route else false
                            Column(
                                Modifier.weight(25f).clickable {

                                    coroutineScope.launch {
                                        router.bringToFront(bottomNavState.route)
                                    }
                                }.padding(vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                val icon =
                                    if (isSelected) bottomNavState.selectedIcon else bottomNavState.unSelectedIcon
                                val color = if (isSelected) Colors.selected else Colors.unselected
                                Image(
                                    modifier = Modifier.size(40.dp).padding(8.dp),
                                    imageVector = icon,
                                    contentDescription = "Navigation Icon",
                                )
                                Text(
                                    text = bottomNavState.title,
                                    color = color,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                if (searchScreenViewModel.isSearch) {
                    TestingSearchScreen(searchScreenViewModel)
                }
            }
        }
    }
    BackHandler(isEnabled = true) {
        router.pop()
    }
}

@Composable
fun loadScreen(homeScreen: Unit) {
    var isReady = remember { mutableStateOf(false) }
    if (isReady.value) {
        Box(Modifier.fillMaxSize()) {
            homeScreen
        }
    }
    rememberCoroutineScope().launch {
        delay(100)
        isReady.value = true
    }
}

@Composable
fun NavigationDrawer(
    screens: List<BottomNavState>,
    router: Router<MainScreenState>,
    mainScreenViewModel: MainScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.theme),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {

        Box(modifier = Modifier.weight(20f), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    imageVector = MyIconPack.Logobiglight,
                    contentDescription = "Person",
                )
            }
        }

        Box(modifier = Modifier.weight(60f), contentAlignment = Alignment.Center) {
            Column(Modifier.fillMaxSize()) {
                screens.forEach { bottomNavState ->
                    val coroutineScope = rememberCoroutineScope()
                    val isSelected: Boolean = router.stack.value.items.last().configuration == bottomNavState.route

                    val icon =
                        if (isSelected) bottomNavState.selectedIcon else bottomNavState.unSelectedIcon
                    val color = if (isSelected) Colors.red.copy(alpha = .2f) else Colors.theme

                    val translationX by animateFloatAsState(
                        targetValue = if (mainScreenViewModel.navigateClick) 0f else toPx((-50f).dp),
                        animationSpec = tween(1000)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color)
                            .clickable {
                                coroutineScope.launch {
                                    if (!isSelected) {
                                        mainScreenViewModel.navigateClick =
                                            !mainScreenViewModel.navigateClick
                                        router.bringToFront(bottomNavState.route)
                                    }
                                }
                            }
                            .padding(horizontal = 15.dp, vertical = 20.dp)
                            .offset(x = toDp(translationX.toInt()))
                            .padding(start = 30.dp), // Adjust the padding to accommodate the translated position
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            imageVector = icon,
                            contentDescription = "Navigation Icon",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = bottomNavState.title,
                            color = Colors.white,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }
        }

        Box(modifier = Modifier.weight(20f), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.clickable { }.padding(horizontal = 15.dp, vertical = 20.dp)
                    .fillMaxWidth(.6f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "sign out",
                    color = Color.White,
                    fontSize = 17.sp
                )
                Spacer(Modifier.width(10.dp))
                Image(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Logout",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}