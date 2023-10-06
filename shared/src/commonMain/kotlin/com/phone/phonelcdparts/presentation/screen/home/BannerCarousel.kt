package com.phone.phonelcdparts.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.presentation.component.CustomMultipleCarousel
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logobigdarkbw
import com.phone.phonelcdparts.presentation.component.shimmerEffect

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarouselShimmer(isError: Boolean = false) {
//    var width by remember { mutableStateOf(0) }
//    val pagerState = rememberPagerState(initialPage = 10)
//    val coroutineScope = rememberCoroutineScope()
//
//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(2000)
//            coroutineScope.launch {
//
//                if (pagerState.currentPage < (10 - 1)) {
//                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                } else {
//                    pagerState.animateScrollToPage(0)
//                }
//
//            }
//            coroutineScope.cancel()
//        }
//    }

    CustomMultipleCarousel(
        modifier = Modifier.padding(15.dp).fillMaxWidth().aspectRatio(3.1f)
            .clip(RoundedCornerShape(15.dp)),
        carouselItems = (1..10).toList(),
        delayMillis = 5000L,
        cellCount = 1.0f,
        preLoadItemCount = 2
    ) { card ->

        Box(Modifier.fillMaxSize().shimmerEffect(), contentAlignment = Alignment.Center) {
            if (isError) {
                Image(
                    modifier = Modifier.fillMaxWidth(.6f),
                    imageVector = MyIconPack.Logobigdarkbw,
                    contentDescription = card.toString()
                )
            }
        }

    }

//    Column(Modifier.padding(16.dp)) {
//
//        HorizontalPager(
//            state = pagerState,
//            pageCount = 10,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(150.dp)
//                .onSizeChanged { width = it.width }
//                .clip(RoundedCornerShape(15.dp))
//        ) {
//            Box(Modifier.fillMaxSize().shimmerEffect(), contentAlignment = Alignment.Center) {
//                if (isError) {
//                    Image(
//                        modifier = Modifier.fillMaxWidth(.6f),
//                        imageVector = MyIconPack.Logobigdarkbw,
//                        contentDescription = null
//                    )
//                }
//            }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(10) { index ->
//
//                val boxWidth = if (index == pagerState.currentPage) {
//                    24.dp
//                } else {
//                    8.dp
//                }
//                Box(
//                    modifier = Modifier
//                        .height(8.dp)
//                        .width(boxWidth)
//                        .clip(CircleShape)
//                        .shimmerEffect()
//                )
//                Spacer(Modifier.width(5.dp))
//            }
//        }
//    }
}