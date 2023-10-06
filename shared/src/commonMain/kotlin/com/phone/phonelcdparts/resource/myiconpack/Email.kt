package com.phone.phonelcdparts.resource.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack

val MyIconPack.Email: ImageVector
    get() {
        if (_email != null) {
            return _email!!
        }
        _email = Builder(name = "Email", defaultWidth = 16.0.dp, defaultHeight = 19.0.dp,
                viewportWidth = 16.0f, viewportHeight = 19.0f).apply {
            group {
            }
            group {
                path(fill = SolidColor(Color(0xFF07253B)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(0.0126f, 6.0714f)
                    lineTo(0.4834f, 6.3409f)
                    curveTo(2.7044f, 7.6287f, 4.9246f, 8.9172f, 7.144f, 10.2065f)
                    curveTo(7.4151f, 10.3806f, 7.7313f, 10.4732f, 8.0543f, 10.4732f)
                    curveTo(8.3773f, 10.4732f, 8.6935f, 10.3806f, 8.9646f, 10.2065f)
                    curveTo(11.277f, 8.8644f, 13.5909f, 7.5245f, 15.9063f, 6.1869f)
                    lineTo(16.1097f, 6.0737f)
                    curveTo(16.1097f, 6.1496f, 16.1223f, 6.2061f, 16.1223f, 6.2639f)
                    curveTo(16.1223f, 8.7549f, 16.1223f, 11.246f, 16.1223f, 13.7371f)
                    curveTo(16.1313f, 14.0611f, 16.0735f, 14.3835f, 15.9525f, 14.6847f)
                    curveTo(15.8316f, 14.9858f, 15.6499f, 15.2593f, 15.4187f, 15.4886f)
                    curveTo(15.1875f, 15.7179f, 14.9115f, 15.8982f, 14.6077f, 16.0183f)
                    curveTo(14.3038f, 16.1384f, 13.9785f, 16.196f, 13.6514f, 16.1874f)
                    curveTo(9.9235f, 16.1912f, 6.195f, 16.1912f, 2.4663f, 16.1874f)
                    curveTo(2.1406f, 16.196f, 1.8166f, 16.1387f, 1.5139f, 16.0192f)
                    curveTo(1.2113f, 15.8997f, 0.9364f, 15.7204f, 0.706f, 15.4922f)
                    curveTo(0.4755f, 15.264f, 0.2944f, 14.9917f, 0.1736f, 14.6919f)
                    curveTo(0.0529f, 14.3921f, -0.0051f, 14.0711f, 0.0034f, 13.7484f)
                    curveTo(-0.0011f, 11.2529f, -0.0011f, 8.7568f, 0.0034f, 6.2605f)
                    curveTo(0.0011f, 6.2062f, 0.008f, 6.1529f, 0.0126f, 6.0714f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF07253B)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(0.4171f, 4.4443f)
                    curveTo(0.6475f, 4.1131f, 0.9577f, 3.844f, 1.3194f, 3.6613f)
                    curveTo(1.6812f, 3.4786f, 2.0832f, 3.3881f, 2.4891f, 3.3981f)
                    curveTo(4.2712f, 3.3981f, 6.0537f, 3.3981f, 7.8365f, 3.3981f)
                    horizontalLineTo(13.6183f)
                    curveTo(13.9849f, 3.3886f, 14.349f, 3.4618f, 14.6829f, 3.612f)
                    curveTo(15.0169f, 3.7623f, 15.3121f, 3.9857f, 15.5462f, 4.2654f)
                    curveTo(15.5794f, 4.3028f, 15.6091f, 4.3435f, 15.6377f, 4.3786f)
                    curveTo(15.6535f, 4.4035f, 15.6679f, 4.4292f, 15.6811f, 4.4556f)
                    lineTo(14.2811f, 5.2709f)
                    curveTo(12.2681f, 6.4409f, 10.2556f, 7.6109f, 8.2434f, 8.781f)
                    curveTo(8.1922f, 8.8182f, 8.1303f, 8.8383f, 8.0668f, 8.8383f)
                    curveTo(8.0033f, 8.8383f, 7.9414f, 8.8182f, 7.8902f, 8.781f)
                    curveTo(5.4834f, 7.3762f, 3.0746f, 5.9748f, 0.6639f, 4.5768f)
                    lineTo(0.4171f, 4.4443f)
                    close()
                }
            }
        }
        .build()
        return _email!!
    }

private var _email: ImageVector? = null
