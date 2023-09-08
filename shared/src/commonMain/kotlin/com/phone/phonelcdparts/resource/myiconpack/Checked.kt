package com.phone.phonelcdparts.resource.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack

public val MyIconPack.Checked: ImageVector
    get() {
        if (_checked != null) {
            return _checked!!
        }
        _checked = Builder(name = "Checked", defaultWidth = 18.0.dp, defaultHeight = 18.0.dp,
                viewportWidth = 18.0f, viewportHeight = 18.0f).apply {
            path(fill = SolidColor(Color(0xFF07253B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(9.0f, 0.0f)
                lineTo(9.0f, 0.0f)
                arcTo(9.0f, 9.0f, 0.0f, false, true, 18.0f, 9.0f)
                lineTo(18.0f, 9.0f)
                arcTo(9.0f, 9.0f, 0.0f, false, true, 9.0f, 18.0f)
                lineTo(9.0f, 18.0f)
                arcTo(9.0f, 9.0f, 0.0f, false, true, 0.0f, 9.0f)
                lineTo(0.0f, 9.0f)
                arcTo(9.0f, 9.0f, 0.0f, false, true, 9.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(12.0909f, 5.6409f)
                curveTo(12.2452f, 5.4871f, 12.4535f, 5.4006f, 12.6708f, 5.4f)
                curveTo(12.8881f, 5.3994f, 13.0968f, 5.4848f, 13.252f, 5.6377f)
                curveTo(13.4071f, 5.7905f, 13.4962f, 5.9987f, 13.4999f, 6.217f)
                curveTo(13.5036f, 6.4353f, 13.4216f, 6.6464f, 13.2718f, 6.8045f)
                lineTo(8.8703f, 12.3344f)
                curveTo(8.7946f, 12.4163f, 8.7033f, 12.482f, 8.6018f, 12.5276f)
                curveTo(8.5003f, 12.5732f, 8.3907f, 12.5978f, 8.2796f, 12.5999f)
                curveTo(8.1684f, 12.6019f, 8.058f, 12.5815f, 7.9549f, 12.5396f)
                curveTo(7.8518f, 12.4978f, 7.7581f, 12.4356f, 7.6795f, 12.3565f)
                lineTo(4.7632f, 9.4242f)
                curveTo(4.682f, 9.3482f, 4.6168f, 9.2564f, 4.5716f, 9.1545f)
                curveTo(4.5264f, 9.0525f, 4.5021f, 8.9424f, 4.5001f, 8.8308f)
                curveTo(4.4982f, 8.7192f, 4.5186f, 8.6084f, 4.5602f, 8.5049f)
                curveTo(4.6018f, 8.4014f, 4.6637f, 8.3074f, 4.7422f, 8.2285f)
                curveTo(4.8207f, 8.1495f, 4.9143f, 8.0873f, 5.0172f, 8.0455f)
                curveTo(5.1202f, 8.0037f, 5.2305f, 7.9832f, 5.3415f, 7.9852f)
                curveTo(5.4525f, 7.9871f, 5.562f, 8.0115f, 5.6635f, 8.057f)
                curveTo(5.7649f, 8.1024f, 5.8562f, 8.1679f, 5.9319f, 8.2496f)
                lineTo(8.2407f, 10.569f)
                lineTo(12.07f, 5.6653f)
                curveTo(12.0768f, 5.6567f, 12.0842f, 5.6485f, 12.092f, 5.6409f)
                horizontalLineTo(12.0909f)
                close()
            }
        }
        .build()
        return _checked!!
    }

private var _checked: ImageVector? = null
