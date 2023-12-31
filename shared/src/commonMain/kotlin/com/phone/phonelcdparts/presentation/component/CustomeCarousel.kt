package com.phone.phonelcdparts.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.browserWrapper
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.domain.model.BannerModel
import com.phone.phonelcdparts.resource.Colors
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@Composable
fun CustomSingleCarousel(
    carouselItems: List<BannerModel>,
    showDots: Boolean = true,
    delayMillis: Long = 3000L
) {
    var selectedItemIndex by remember { mutableStateOf(0) }


    LaunchedEffect(selectedItemIndex) {
        while (true) {
            delay(delayMillis)
            selectedItemIndex = (selectedItemIndex + 1) % carouselItems.size
        }
    }

    Column(
        modifier = Modifier.padding(16.dp), // Remove top and right padding, keep bottom padding
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCarousel(
            carouselItems = carouselItems,
            selectedItemIndex = selectedItemIndex,
            onSelectedItemChange = { index ->
                selectedItemIndex = index
            }
        )
        if (showDots) {
            Spacer(modifier = Modifier.height(5.dp))
            DotsIndicator(
                numDots = carouselItems.size,
                currentIndex = selectedItemIndex,
                onDotClick = { index ->
                    selectedItemIndex = index
                }
            )
        }
    }
}


@Composable
inline fun ImageCarousel(
    carouselItems: List<BannerModel>,
    selectedItemIndex: Int,
    crossinline onSelectedItemChange: (Int) -> Unit
) {
    AnimatedVisibility(
        visible = selectedItemIndex >= 0, // To prevent animation on first item when recomposing
        enter = slideInHorizontally(
            initialOffsetX = { 1000 },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { 1000 },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        Crossfade(targetState = carouselItems[selectedItemIndex].image) { imageRes ->

            var offsetX by remember { mutableStateOf(0f) }



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3.1f)
                    .clip(RoundedCornerShape(15.dp))
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            if (zoom == 1f) {
                                when {
                                    pan.x > 0 && offsetX == 0f -> onSelectedItemChange(if (selectedItemIndex - 1 >= 0) selectedItemIndex - 1 else carouselItems.size - 1)
                                    pan.x < 0 && offsetX == 0f -> onSelectedItemChange(if (selectedItemIndex + 1 < carouselItems.size) selectedItemIndex + 1 else 0)
                                    else -> offsetX += pan.x
                                }
                            }
                        }
                    }
                    .graphicsLayer(translationX = offsetX)
            ) {

                KamelImage(
                    modifier = Modifier.fillMaxWidth().clickable {
                        carouselItems[selectedItemIndex].custom_link?.let {
                            browserWrapper.openBrowser(
                                it
                            )
                        }
                    },
                    resource = asyncPainterResource(data = "${HttpRoutes.BANNER_MEDIA_URL}${imageRes}\"") {
                        coroutineContext = Job() + Dispatchers.IO
                    },
                    contentDescription = "Profile",
                    contentScale = ContentScale.FillBounds
                )
            }

        }
    }
}

@Composable
inline fun DotsIndicator(
    numDots: Int,
    currentIndex: Int,
    onDotClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until numDots) {
            Dot(index = i, isSelected = i == currentIndex, onDotClick)
        }
    }
}

@Composable
inline fun Dot(
    index: Int,
    isSelected: Boolean,
    onDotClick: (Int) -> Unit
) {
    val color = if (isSelected) Colors.indicatorSelected else Colors.indicatorUnSelected
    val boxWidth = if (isSelected) {
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