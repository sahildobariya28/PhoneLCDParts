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

val MyIconPack.Location: ImageVector
    get() {
        if (_location != null) {
            return _location!!
        }
        _location = Builder(name = "Location", defaultWidth = 13.0.dp, defaultHeight = 17.0.dp,
                viewportWidth = 13.0f, viewportHeight = 17.0f).apply {
            path(fill = SolidColor(Color(0xFF07253B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.2401f, 5.2399f)
                curveTo(11.993f, 3.6804f, 11.268f, 2.3885f, 10.0505f, 1.396f)
                curveTo(8.6699f, 0.2704f, 7.0747f, -0.1814f, 5.3056f, 0.0657f)
                curveTo(3.497f, 0.3182f, 2.0656f, 1.2217f, 1.0515f, 2.7295f)
                curveTo(-0.0158f, 4.316f, -0.2538f, 6.0564f, 0.2603f, 7.8996f)
                curveTo(0.6031f, 9.1283f, 1.1567f, 10.2647f, 1.789f, 11.3641f)
                curveTo(2.8763f, 13.2551f, 4.1796f, 14.993f, 5.5992f, 16.6452f)
                curveTo(5.7178f, 16.7833f, 5.8813f, 16.8827f, 6.0239f, 17.0f)
                horizontalLineTo(6.2893f)
                curveTo(6.432f, 16.8827f, 6.5946f, 16.7829f, 6.714f, 16.6452f)
                curveTo(7.7244f, 15.4763f, 8.6686f, 14.2559f, 9.5259f, 12.9697f)
                curveTo(10.4169f, 11.6328f, 11.2206f, 10.2473f, 11.7821f, 8.7348f)
                curveTo(12.201f, 7.6063f, 12.4327f, 6.4532f, 12.2401f, 5.2399f)
                close()
                moveTo(6.1579f, 9.2539f)
                curveTo(4.457f, 9.256f, 3.0673f, 7.8667f, 3.0623f, 6.1596f)
                curveTo(3.0573f, 4.45f, 4.4591f, 3.0465f, 6.1637f, 3.0544f)
                curveTo(7.8696f, 3.0619f, 9.2535f, 4.4529f, 9.251f, 6.1567f)
                curveTo(9.2481f, 7.8629f, 7.8612f, 9.2519f, 6.1579f, 9.2539f)
                close()
            }
        }
        .build()
        return _location!!
    }

private var _location: ImageVector? = null
