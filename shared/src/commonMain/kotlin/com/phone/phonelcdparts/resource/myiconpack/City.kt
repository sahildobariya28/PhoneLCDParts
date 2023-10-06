package com.phone.phonelcdparts.resource.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack

val MyIconPack.City: ImageVector
    get() {
        if (_city != null) {
            return _city!!
        }
        _city = Builder(name = "City", defaultWidth = 17.0.dp, defaultHeight = 17.0.dp,
                viewportWidth = 17.0f, viewportHeight = 17.0f).apply {
            path(fill = SolidColor(Color(0xFF07253B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(15.7309f, 16.0042f)
                verticalLineTo(10.7004f)
                horizontalLineTo(10.0075f)
                verticalLineTo(15.9957f)
                horizontalLineTo(9.0201f)
                verticalLineTo(9.6992f)
                horizontalLineTo(12.3562f)
                verticalLineTo(4.0219f)
                horizontalLineTo(11.2522f)
                verticalLineTo(1.7956f)
                horizontalLineTo(9.9858f)
                verticalLineTo(0.0f)
                horizontalLineTo(8.9895f)
                verticalLineTo(1.7854f)
                horizontalLineTo(7.7249f)
                verticalLineTo(4.0117f)
                horizontalLineTo(6.6048f)
                verticalLineTo(15.99f)
                horizontalLineTo(5.6063f)
                verticalLineTo(6.2504f)
                horizontalLineTo(1.2606f)
                verticalLineTo(16.002f)
                horizontalLineTo(0.0f)
                verticalLineTo(17.0f)
                horizontalLineTo(16.9916f)
                verticalLineTo(16.0042f)
                horizontalLineTo(15.7309f)
                close()
                moveTo(10.1064f, 5.2533f)
                horizontalLineTo(11.0783f)
                verticalLineTo(6.2313f)
                horizontalLineTo(10.1064f)
                verticalLineTo(5.2533f)
                close()
                moveTo(10.1033f, 7.4787f)
                horizontalLineTo(11.0783f)
                verticalLineTo(8.4567f)
                horizontalLineTo(10.1033f)
                verticalLineTo(7.4787f)
                close()
                moveTo(2.9192f, 7.4827f)
                horizontalLineTo(3.895f)
                verticalLineTo(8.4572f)
                horizontalLineTo(2.9192f)
                verticalLineTo(7.4827f)
                close()
                moveTo(2.92f, 12.2458f)
                horizontalLineTo(3.895f)
                verticalLineTo(13.2207f)
                horizontalLineTo(2.92f)
                verticalLineTo(12.2458f)
                close()
                moveTo(3.8968f, 15.6023f)
                horizontalLineTo(2.92f)
                verticalLineTo(14.6269f)
                horizontalLineTo(3.8968f)
                verticalLineTo(15.6023f)
                close()
                moveTo(3.8972f, 10.8379f)
                horizontalLineTo(2.92f)
                verticalLineTo(9.8669f)
                horizontalLineTo(3.8972f)
                verticalLineTo(10.8379f)
                close()
                moveTo(7.8814f, 5.2546f)
                horizontalLineTo(8.8515f)
                verticalLineTo(6.2358f)
                horizontalLineTo(7.8814f)
                verticalLineTo(5.2546f)
                close()
                moveTo(8.8524f, 8.4567f)
                horizontalLineTo(7.8805f)
                verticalLineTo(7.4787f)
                horizontalLineTo(8.8524f)
                verticalLineTo(8.4567f)
                close()
                moveTo(8.9948f, 3.031f)
                horizontalLineTo(9.9733f)
                verticalLineTo(4.0033f)
                horizontalLineTo(8.9948f)
                verticalLineTo(3.031f)
                close()
                moveTo(12.2329f, 15.9918f)
                horizontalLineTo(11.2579f)
                verticalLineTo(12.016f)
                horizontalLineTo(12.2329f)
                verticalLineTo(15.9918f)
                close()
                moveTo(14.4703f, 15.9984f)
                horizontalLineTo(13.4993f)
                verticalLineTo(12.0178f)
                horizontalLineTo(14.4703f)
                verticalLineTo(15.9984f)
                close()
            }
        }
        .build()
        return _city!!
    }

private var _city: ImageVector? = null
