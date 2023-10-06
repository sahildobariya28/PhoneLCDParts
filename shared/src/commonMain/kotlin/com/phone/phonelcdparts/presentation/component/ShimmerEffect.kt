package com.phone.phonelcdparts.presentation.component

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
fun Modifier.shimmerEffect(enableAnimation: Boolean = true, color: Color = Color.LightGray): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val brush = if (enableAnimation) {
        createAnimatedBrush(color, size)
    } else {
        createStaticBrush(color)
    }

    background(brush)
        .onGloballyPositioned {
            size = it.size
        }
}

@Composable
private fun createAnimatedBrush(color: Color, size: IntSize): Brush {

    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2f * size.width.toFloat(),
        targetValue = 2f * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    return Brush.linearGradient(
        colors = listOf(
            color.copy(alpha = 0.6f),
            color.copy(alpha = 0.2f),
            color.copy(alpha = 0.6f),
        ),
        start = Offset(startOffsetX, 0f),
        end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
    )
}

private fun createStaticBrush(color: Color): Brush {
    return Brush.linearGradient(
        colors = listOf(
            color.copy(alpha = 1f),
            color.copy(alpha = 1f),
            color.copy(alpha = 1f),
        )
    )
}