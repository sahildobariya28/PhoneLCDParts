package com.phone.phonelcdparts.resource.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack

val MyIconPack.Selectedaccount: ImageVector
    get() {
        if (_selectedaccount != null) {
            return _selectedaccount!!
        }
        _selectedaccount = Builder(name = "Selectedaccount", defaultWidth = 25.0.dp, defaultHeight =
                25.0.dp, viewportWidth = 25.0f, viewportHeight = 25.0f).apply {
            path(fill = SolidColor(Color(0xFFEFF5FB)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.0f, 0.0f)
                lineTo(17.0f, 0.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 25.0f, 8.0f)
                lineTo(25.0f, 8.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 17.0f, 16.0f)
                lineTo(17.0f, 16.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 9.0f, 8.0f)
                lineTo(9.0f, 8.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 17.0f, 0.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF3669C9)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(21.8234f, 23.0002f)
                verticalLineTo(20.6473f)
                curveTo(21.8234f, 19.3992f, 21.3276f, 18.2022f, 20.4451f, 17.3197f)
                curveTo(19.5626f, 16.4372f, 18.3656f, 15.9414f, 17.1176f, 15.9414f)
                horizontalLineTo(7.7059f)
                curveTo(6.4578f, 15.9414f, 5.2608f, 16.4372f, 4.3783f, 17.3197f)
                curveTo(3.4958f, 18.2022f, 3.0f, 19.3992f, 3.0f, 20.6473f)
                verticalLineTo(23.0002f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF3669C9)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(12.4119f, 12.4117f)
                curveTo(15.0109f, 12.4117f, 17.1178f, 10.3048f, 17.1178f, 7.7059f)
                curveTo(17.1178f, 5.1069f, 15.0109f, 3.0f, 12.4119f, 3.0f)
                curveTo(9.8129f, 3.0f, 7.706f, 5.1069f, 7.706f, 7.7059f)
                curveTo(7.706f, 10.3048f, 9.8129f, 12.4117f, 12.4119f, 12.4117f)
                close()
            }
        }
        .build()
        return _selectedaccount!!
    }

private var _selectedaccount: ImageVector? = null
