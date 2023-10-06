package com.phone.phonelcdparts.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.presentation.component.Carousel
import com.phone.phonelcdparts.presentation.component.shimmerEffect



@Composable
fun BrandCarouselShimmer(isError: Boolean) {

    Carousel(
        count = 10,
        parentModifier = Modifier.padding(start = 16.dp),
        contentWidth = 80.dp,
        contentHeight = 35.dp,
        contentView = {
            Box(Modifier.width(80.dp).height(35.dp).clip(RoundedCornerShape(7.dp)).shimmerEffect(), contentAlignment = Alignment.Center){
                if(isError){
                    Image(modifier = Modifier.fillMaxHeight(.5f), imageVector = MyIconPack.Logosmallbw, contentDescription = null)
                }
            }
        }
    )
}