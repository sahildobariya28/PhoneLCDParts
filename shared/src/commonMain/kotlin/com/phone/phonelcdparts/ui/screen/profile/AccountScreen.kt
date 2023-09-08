package com.phone.phonelcdparts.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.domain.model.UserInfo
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.ui.component.shimmerEffect
import io.ktor.http.isSuccess

@Composable
fun AccountScreen(accountScreenViewModel: AccountScreenViewModel) {

    val userInfoState by accountScreenViewModel.userInfo.collectAsState()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        if (userInfoState.isLoading){
            AccountDetailShimmer(false)
        }else if (userInfoState.status.isSuccess()) {
            userInfoState.data?.let { AccountDetail(it as UserInfo?) }
        }else if (!userInfoState.status.isSuccess()){
            AccountDetailShimmer(true)
        }
    }
}

@Composable
fun AccountDetail(userInfo: UserInfo?) {
    if (userInfo != null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(30.dp))
            Image(
                modifier = Modifier.padding().size(100.dp).clip(CircleShape)
                    .border(BorderStroke(5.dp, Colors.text_field_bg_color), CircleShape),
                imageVector = Icons.Rounded.Person,
                contentDescription = "",
                colorFilter = ColorFilter.tint(Colors.theme)
            )
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                    .background(Colors.text_field_bg_color).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding().size(25.dp),
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Colors.theme)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = userInfo.firstname,
                    fontSize = 14.sp,
                    color = Colors.theme,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                    .background(Colors.text_field_bg_color).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding().size(25.dp),
                    imageVector = Icons.Rounded.Email,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Colors.theme)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    text = userInfo.email,
                    fontSize = 14.sp,
                    color = Colors.theme,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                    .background(Colors.text_field_bg_color).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding().size(25.dp),
                    imageVector = Icons.Rounded.Phone,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Colors.theme)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = userInfo.telephone,
                    fontSize = 14.sp,
                    color = Colors.theme,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                    .background(Colors.text_field_bg_color).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding().size(25.dp),
                    imageVector = Icons.Rounded.LocationOn,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Colors.theme)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = userInfo.street,
                    fontSize = 14.sp,
                    color = Colors.theme,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun AccountDetailShimmer(isError: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .padding()
                .size(100.dp)
                .clip(CircleShape)
                .border(BorderStroke(5.dp, Colors.text_field_bg_color), CircleShape)
                .shimmerEffect(),
            contentAlignment = Alignment.Center
        ){
            if(isError){
                Image(
                    modifier = Modifier.fillMaxHeight(.5f),
                    imageVector = MyIconPack.Logosmallbw,
                    contentDescription = null
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                .background(Colors.text_field_bg_color).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding().size(25.dp).clip(CircleShape).shimmerEffect())
            Spacer(Modifier.width(10.dp))
            Box(modifier = Modifier.height(15.dp).fillMaxWidth(.4f).clip(RoundedCornerShape(10.dp)).shimmerEffect())
        }
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                .background(Colors.text_field_bg_color).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding().size(25.dp).clip(CircleShape).shimmerEffect())
            Spacer(Modifier.width(10.dp))
            Box(modifier = Modifier.height(15.dp).fillMaxWidth(.7f).clip(RoundedCornerShape(10.dp)).shimmerEffect())
        }
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                .background(Colors.text_field_bg_color).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding().size(25.dp).clip(CircleShape).shimmerEffect())
            Spacer(Modifier.width(10.dp))
            Box(modifier = Modifier.height(15.dp).fillMaxWidth(.5f).clip(RoundedCornerShape(10.dp)).shimmerEffect())
        }
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                .background(Colors.text_field_bg_color).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding().size(25.dp).clip(CircleShape).shimmerEffect())
            Spacer(Modifier.width(10.dp))
            Column {
                Box(
                    modifier = Modifier.height(15.dp).fillMaxWidth().clip(RoundedCornerShape(10.dp))
                        .shimmerEffect()
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier.height(15.dp).fillMaxWidth(.5f)
                        .clip(RoundedCornerShape(10.dp)).shimmerEffect()
                )
            }
        }
    }
}