package com.phone.phonelcdparts.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*



@Composable
fun Carousel(
    items: List<ColorPainter>,
    itemsToShow: Float = 3.5f,
    autoScrollInterval: Long = 3000L
) {
    var currentItem by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val screenWidthDp = LocalConfiguration.current.screenWidthDp

    LaunchedEffect(currentItem) {
        coroutineScope.launch {
            delay(autoScrollInterval)
            currentItem = (currentItem + 1) % items.size
        }
    }

    val itemWidth = screenWidthDp.dp / itemsToShow

    LazyRow {
        itemsIndexed(items) { index, item ->
            val startIndex = (currentItem - 3 + items.size) % items.size
            val endIndex = (startIndex + itemsToShow.toInt()) % items.size

            if (index in startIndex..endIndex) {
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .height(150.dp) // Set the desired item height here
                        .padding(8.dp)
                        .background(item.color),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    // Place your content here
                }
            }
        }
    }
}


@Composable
fun CircularList(
    items: List<ColorPainter>,
    itemsToShow: Float = 3.5f
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val screenWidthDp = LocalConfiguration.current.screenWidthDp

    // Auto scrolling coroutine
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000) // Adjust the delay as needed for the auto scrolling interval
            coroutineScope.launch {
                val currentIndex = listState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0
                val nextIndex = (currentIndex + 1) % Int.MAX_VALUE
                listState.animateScrollToItem(nextIndex)
            }
        }
    }
    val itemWidth = screenWidthDp.dp / itemsToShow
    LazyRow(
        state = listState,
        modifier = Modifier
    ) {
        items(
            count = Int.MAX_VALUE,
            itemContent = {
                val index = it % items.size
                Box(
                    modifier = Modifier.height(100.dp).width(itemWidth).background(items[index].color),
                )
            }
        )
    }
}

@Preview
@Composable
fun CarouselScreen() {
    val images = listOf(
        // Replace with your images
        // Example: ImagePainter(imageResource(R.drawable.image1))
        ColorPainter(Color.Red),
        ColorPainter(Color.Green),
        ColorPainter(Color.LightGray),
        ColorPainter(Color.Blue),
        ColorPainter(Color.Yellow),
        ColorPainter(Color.DarkGray),
        ColorPainter(Color.Magenta),
    )

    val itemWidth = 100
    val itemHeight = 150
    val itemsToShow = 3.5f



    Column {
//        Carousel(images, itemsToShow, 3000L)
        CircularList(images, itemsToShow)
    }

}
