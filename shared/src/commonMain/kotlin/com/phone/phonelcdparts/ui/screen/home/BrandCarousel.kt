package com.phone.phonelcdparts.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.browserWrapper
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.domain.model.BrandItem
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.ui.component.Carousel
import com.phone.phonelcdparts.ui.component.shimmerEffect
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun BrandCarousel(brands: List<BrandItem>) {

    var brandsList = remember { mutableStateOf(brands) }

    Carousel(
        count = brandsList.value.size,
        parentModifier = Modifier.padding(start = 16.dp),
        contentWidth = 80.dp,
        contentHeight = 35.dp,
        contentView = { index ->

            val painter = rememberAsyncImagePainter("${HttpRoutes.BANNER_MEDIA_URL}${brandsList.value[index].image}", filterQuality = FilterQuality.None)
            Box(
                Modifier.width(80.dp).height(35.dp).clip(RoundedCornerShape(7.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(7.dp))
                    .clickable {
                        brandsList.value[index].custom_link?.let { browserWrapper.openBrowser(it) }
                    }
                    .padding(5.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    )
}

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