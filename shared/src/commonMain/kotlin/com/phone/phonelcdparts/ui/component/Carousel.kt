package com.phone.phonelcdparts.ui.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun Carousel(
    count: Int,
    parentModifier: Modifier = Modifier,
    contentWidth: Dp,
    contentHeight: Dp,
    contentView: @Composable (i: Int) -> Unit
) {
    val listState = rememberLazyListState(Int.MAX_VALUE / 2)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000) // Adjust the delay as needed for the auto scrolling interval
            coroutineScope.launch {
                listState.animateScrollToItem(listState.layoutInfo.visibleItemsInfo.first().index + 1)
            }
        }
    }

    BoxWithConstraints(
        modifier = parentModifier
    ) {

        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = false
        ) {
            items(
                count = Int.MAX_VALUE,
                itemContent = { globalIndex ->

                    Box(modifier = Modifier
                        .width(contentWidth)
                        .height(contentHeight)
                    ) {

                        contentView(globalIndex % count)
                    }
                }
            )
        }
    }
}

