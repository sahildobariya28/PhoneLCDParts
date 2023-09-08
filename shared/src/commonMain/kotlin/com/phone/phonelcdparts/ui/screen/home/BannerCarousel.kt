package com.phone.phonelcdparts.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.browserWrapper
import com.phone.phonelcdparts.core.HttpRoutes.BANNER_MEDIA_URL
import com.phone.phonelcdparts.domain.model.BannerItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logobigdarkbw
import com.phone.phonelcdparts.ui.component.shimmerEffect
import com.phone.phonelcdparts.util.toDp
import com.seiko.imageloader.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarousel(banners: List<BannerItem>) {

    var bannerList = remember { mutableStateOf(banners) }

    var width by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = bannerList.value.size)

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)

//                val newList = bannerList.value.toMutableList()
//                val lastItem = newList.removeAt(newList.size - 1)
//                newList.add(0, lastItem)
//                bannerList.value = newList

            if (pagerState.currentPage < (bannerList.value.size - 1)) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            } else {
                pagerState.animateScrollToPage(0)
            }
        }
    }


    Column(Modifier.padding(16.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = 8.dp,
        ) {
            HorizontalPager(
                state = pagerState,
                pageCount = bannerList.value.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3.1f)
                    .onSizeChanged { width = it.width }
                    .clip(RoundedCornerShape(15.dp))
            ) { page ->
                val painter =
                    rememberAsyncImagePainter(
                        "$BANNER_MEDIA_URL${bannerList.value[page].image}",
                        filterQuality = FilterQuality.None
                    )
                Box(Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.width(toDp(width)).clickable {
                            bannerList.value[page].custom_link?.let {
                                bannerList.value[page].custom_link?.let { it1 ->
                                    browserWrapper.openBrowser(
                                        it1
                                    )
                                }
//                browserWrapper.openBrowser(it)
                            }
                        },
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(bannerList.value.size) { index ->
                val color = if (index == pagerState.currentPage) {
                    Colors.indicatorSelected
                } else {
                    Colors.indicatorUnSelected
                }
                val boxWidth = if (index == pagerState.currentPage) {
                    24.dp
                } else {
                    8.dp
                }
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(boxWidth)
                        .background(color = color, shape = CircleShape)
                        .padding(horizontal = 4.dp)
                )
                Spacer(Modifier.width(5.dp))
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarouselShimmer(isError: Boolean = false) {
    var width by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 10)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            coroutineScope.launch {

                if (pagerState.currentPage < (10 - 1)) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                } else {
                    pagerState.animateScrollToPage(0)
                }

            }
        }
    }

    Column(Modifier.padding(16.dp)) {
        HorizontalPager(
            state = pagerState,
            pageCount = 10,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .onSizeChanged { width = it.width }
                .clip(RoundedCornerShape(15.dp))
        ) {
            Box(Modifier.fillMaxSize().shimmerEffect(), contentAlignment = Alignment.Center) {
                if (isError) {
                    Image(
                        modifier = Modifier.fillMaxWidth(.6f),
                        imageVector = MyIconPack.Logobigdarkbw,
                        contentDescription = null
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(10) { index ->

                val boxWidth = if (index == pagerState.currentPage) {
                    24.dp
                } else {
                    8.dp
                }
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(boxWidth)
                        .clip(CircleShape)
                        .shimmerEffect()
                )
                Spacer(Modifier.width(5.dp))
            }
        }
    }
}