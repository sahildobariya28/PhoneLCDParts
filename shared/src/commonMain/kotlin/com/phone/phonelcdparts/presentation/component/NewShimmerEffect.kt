package com.phone.phonelcdparts.presentation.component

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