package com.phone.phonelcdparts.resource

import androidx.compose.ui.graphics.vector.ImageVector
import com.phone.phonelcdparts.resource.myiconpack.Add
import com.phone.phonelcdparts.resource.myiconpack.Back
import com.phone.phonelcdparts.resource.myiconpack.Cart
import com.phone.phonelcdparts.resource.myiconpack.Checked
import com.phone.phonelcdparts.resource.myiconpack.City
import com.phone.phonelcdparts.resource.myiconpack.Companylocation
import com.phone.phonelcdparts.resource.myiconpack.Contact
import com.phone.phonelcdparts.resource.myiconpack.Countrycode
import com.phone.phonelcdparts.resource.myiconpack.Customercare
import com.phone.phonelcdparts.resource.myiconpack.Email
import com.phone.phonelcdparts.resource.myiconpack.Eyeinvisible
import com.phone.phonelcdparts.resource.myiconpack.Formedit
import com.phone.phonelcdparts.resource.myiconpack.Hidepassword
import com.phone.phonelcdparts.resource.myiconpack.Location
import com.phone.phonelcdparts.resource.myiconpack.Lock
import com.phone.phonelcdparts.resource.myiconpack.Logobigdark
import com.phone.phonelcdparts.resource.myiconpack.Logobigdarkbw
import com.phone.phonelcdparts.resource.myiconpack.Logobiglight
import com.phone.phonelcdparts.resource.myiconpack.Logosmall
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.resource.myiconpack.Minus
import com.phone.phonelcdparts.resource.myiconpack.Plus
import com.phone.phonelcdparts.resource.myiconpack.Selectedaccount
import com.phone.phonelcdparts.resource.myiconpack.Selectedcategory
import com.phone.phonelcdparts.resource.myiconpack.Selectedhome
import com.phone.phonelcdparts.resource.myiconpack.Selectedorder
import com.phone.phonelcdparts.resource.myiconpack.Showpassword
import com.phone.phonelcdparts.resource.myiconpack.State
import com.phone.phonelcdparts.resource.myiconpack.Sub
import com.phone.phonelcdparts.resource.myiconpack.Unselectedaccount
import com.phone.phonelcdparts.resource.myiconpack.Unselectedcategory
import com.phone.phonelcdparts.resource.myiconpack.Unselectedhome
import com.phone.phonelcdparts.resource.myiconpack.Unselectedorder
import com.phone.phonelcdparts.resource.myiconpack.Upload
import com.phone.phonelcdparts.resource.myiconpack.User
import com.phone.phonelcdparts.resource.myiconpack.Userabout
import com.phone.phonelcdparts.resource.myiconpack.Vatnumber
import com.phone.phonelcdparts.resource.myiconpack.Zipcode
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Contact, Selectedhome, Checked, User, Selectedcategory, Vatnumber, City,
        Countrycode, Zipcode, Lock, Formedit, Cart, Companylocation, Logosmall, Sub, Customercare,
        Showpassword, Plus, Userabout, Selectedorder, Email, Add, Unselectedorder, Back,
        Logosmallbw, Unselectedcategory, Unselectedhome, Hidepassword, Unselectedaccount,
        Logobigdarkbw, Location, Eyeinvisible, Selectedaccount, Upload, Logobiglight, Minus,
        Logobigdark, State)
    return __AllIcons!!
  }
