package com.phone.phonelcdparts.ui.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

//var startOffsetX = mutableStateOf(0f)
//var size = mutableStateOf(IntSize.Zero)
//@Composable
//fun ShimmerAnim() {
//    var size by remember {
//        mutableStateOf(IntSize.Zero)
//    }
//    val transition = rememberInfiniteTransition()
//    startOffsetX.value = transition.animateFloat(
//        initialValue = -2 * size.width.toFloat(),
//        targetValue = 2 * size.width.toFloat(),
//        animationSpec = infiniteRepeatable(
//            animation = tween(1000)
//        )
//    ).value
//}
//
//fun Modifier.shimmerEffect2(color: Color = Color.LightGray): Modifier = composed {
//    background(
//        brush = Brush.linearGradient(
//            colors = listOf(
//                color.copy(alpha = 0.6f),
//                color.copy(alpha = 0.2f),
//                color.copy(alpha = 0.6f),
//            ),
//            start = Offset(startOffsetX.value, 0f),
//            end = Offset(startOffsetX.value + size.value.width.toFloat(), size.value.height.toFloat())
//        )
//    )
//        .onGloballyPositioned {
//            size.value = it.size
//        }
//}