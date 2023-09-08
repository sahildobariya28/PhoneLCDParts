package com.phone.phonelcdparts.ui.component

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import com.phone.phonelcdparts.util.toDp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomMultipleCarousel(
    modifier: Modifier = Modifier.fillMaxWidth(),
    carouselItems: List<Any>,
    delayMillis: Long = 3000L,
    cellCount: Float = 3.5f,
    preLoadItemCount: Int = 2,
    content: @Composable (card: Any) -> Unit,
) {

    var carouselList: List<Any> = carouselItems

    if (carouselList.size < ((preLoadItemCount * 2) + cellCount)) {
        carouselList = fixingListSize(carouselItems, preLoadItemCount, cellCount.toInt())
        println("djsfkjdskfjkfjdsjfds ${carouselList.size}" )
    }

    var screenWidth by remember { mutableStateOf(0) }

    Box(Modifier.fillMaxWidth().onSizeChanged { screenWidth = it.width }) {
        if (screenWidth > 0) {
            val swipeableState = rememberSwipeableState(0)
            var spanWidth: Int = (screenWidth / cellCount).toInt()
            var isRun: Boolean by remember { mutableStateOf(true) }
            var isIncrement: Boolean by remember { mutableStateOf(false) }

            if (!swipeableState.isAnimationRunning) {
                LaunchedEffect(Unit) {
                    while (true) {
                        delay(delayMillis)
                        isIncrement = false
                        isRun = !isRun
                    }
                }
            } else {
                DisposableEffect(Unit) {
                    onDispose {
                        if (swipeableState.offset.value > 0) {
                            isIncrement = true
                            isRun = !isRun
                        } else {
                            isIncrement = false
                            isRun = !isRun
                        }
                    }
                }
            }

            Row(
                Modifier
                    .horizontalScroll(rememberScrollState(), true)
                    .swipeable(
                        state = swipeableState,
                        anchors = mapOf(0f to 0, LocalDensity.current.density * 200 to 1),
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Horizontal
                    ),
            ) {
                carouselList.forEachIndexed { index, card ->
                    var offset =
                        remember { mutableStateOf(-(spanWidth * (preLoadItemCount - 1))) }


                    LaunchedEffect(isRun) {
                        if (isIncrement) {
                            if (offset.value == (spanWidth * (carouselList.size - index - preLoadItemCount))) {
                                offset.value = (spanWidth * -(index + preLoadItemCount))
                            }
                            offset.value = offset.value + spanWidth
                        } else {
                            if (offset.value == -(spanWidth * (index + preLoadItemCount))) {
                                offset.value = (offset.value + (spanWidth * carouselList.size))
                            }
                            offset.value = offset.value - spanWidth
                        }
                    }
                    val animateOffset by animateIntAsState(
                        targetValue = offset.value,
                        animationSpec = tween(300)
                    )

                    Box(
                        Modifier
                            .offset(x = toDp(animateOffset))
                            .width(toDp(spanWidth)),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(modifier = modifier) {
                            content(card)
                        }
                    }
                }
            }
        }
    }
}

fun fixingListSize(carouselItems: List<Any>, preLoadItemCount: Int, cellCount: Int): List<Any> {

    var carouselList = carouselItems + carouselItems
    if (carouselList.size < ((preLoadItemCount * 2) + cellCount)){
        fixingListSize(carouselList, preLoadItemCount, cellCount)
    }
    return carouselList
}

data class CardItem(val id: String, val name: String, val position: Int)


