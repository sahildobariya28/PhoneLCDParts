package com.phone.phonelcdparts.resource.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack

public val MyIconPack.Unselectedaccount: ImageVector
    get() {
        if (_unselectedaccount != null) {
            return _unselectedaccount!!
        }
        _unselectedaccount = Builder(name = "Unselectedaccount", defaultWidth = 25.0.dp,
                defaultHeight = 25.0.dp, viewportWidth = 25.0f, viewportHeight = 25.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF0C1A30)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(21.82f, 22.9999f)
                verticalLineTo(20.7333f)
                curveTo(21.82f, 19.5309f, 21.3243f, 18.3779f, 20.4419f, 17.5277f)
                curveTo(19.5596f, 16.6776f, 18.3628f, 16.2f, 17.115f, 16.2f)
                horizontalLineTo(7.705f)
                curveTo(6.4572f, 16.2f, 5.2604f, 16.6776f, 4.3781f, 17.5277f)
                curveTo(3.4957f, 18.3779f, 3.0f, 19.5309f, 3.0f, 20.7333f)
                verticalLineTo(22.9999f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF0C1A30)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(12.6453f, 12.5999f)
                curveTo(15.3738f, 12.5999f, 17.5856f, 10.4509f, 17.5856f, 7.7999f)
                curveTo(17.5856f, 5.149f, 15.3738f, 3.0f, 12.6453f, 3.0f)
                curveTo(9.9169f, 3.0f, 7.7051f, 5.149f, 7.7051f, 7.7999f)
                curveTo(7.7051f, 10.4509f, 9.9169f, 12.5999f, 12.6453f, 12.5999f)
                close()
            }
        }
        .build()
        return _unselectedaccount!!
    }

private var _unselectedaccount: ImageVector? = null
