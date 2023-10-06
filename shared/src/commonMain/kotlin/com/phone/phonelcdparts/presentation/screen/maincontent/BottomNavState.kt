package com.phone.phonelcdparts.presentation.screen.maincontent

import androidx.compose.ui.graphics.vector.ImageVector
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Selectedaccount
import com.phone.phonelcdparts.resource.myiconpack.Selectedcategory
import com.phone.phonelcdparts.resource.myiconpack.Selectedhome
import com.phone.phonelcdparts.resource.myiconpack.Selectedorder
import com.phone.phonelcdparts.resource.myiconpack.Unselectedaccount
import com.phone.phonelcdparts.resource.myiconpack.Unselectedcategory
import com.phone.phonelcdparts.resource.myiconpack.Unselectedhome
import com.phone.phonelcdparts.resource.myiconpack.Unselectedorder

data class BottomNavState(
    val position: Int,
    val route: MainScreenState,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)

fun getBottomListItem(): List<BottomNavState>{

    return listOf(
        BottomNavState(
            0,
            MainScreenState.Home,
            "Home",
            MyIconPack.Selectedhome,
            MyIconPack.Unselectedhome
        ),
        BottomNavState(
            1,
            MainScreenState.Category,
            "Category",
            MyIconPack.Selectedcategory,
            MyIconPack.Unselectedcategory
        ),
        BottomNavState(
            2,
            MainScreenState.Order,
            "Order",
            MyIconPack.Selectedorder,
            MyIconPack.Unselectedorder
        ),
        BottomNavState(
            3,
            MainScreenState.Account,
            "Account",
            MyIconPack.Selectedaccount,
            MyIconPack.Unselectedaccount
        )
    )
}
