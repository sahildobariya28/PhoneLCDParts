package com.phone.phonelcdparts.resource.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack

public val MyIconPack.Selectedhome: ImageVector
    get() {
        if (_selectedhome != null) {
            return _selectedhome!!
        }
        _selectedhome = Builder(name = "Selectedhome", defaultWidth = 25.0.dp, defaultHeight =
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
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(9.6572f, 21.7714f)
                verticalLineTo(18.7047f)
                curveTo(9.6572f, 17.9246f, 10.2931f, 17.2908f, 11.081f, 17.2856f)
                horizontalLineTo(13.9671f)
                curveTo(14.7587f, 17.2856f, 15.4005f, 17.9209f, 15.4005f, 18.7047f)
                verticalLineTo(18.7047f)
                verticalLineTo(21.7809f)
                curveTo(15.4003f, 22.4432f, 15.9343f, 22.9845f, 16.603f, 23.0f)
                horizontalLineTo(18.5271f)
                curveTo(20.4451f, 23.0f, 22.0f, 21.4607f, 22.0f, 19.5618f)
                verticalLineTo(19.5618f)
                verticalLineTo(10.8378f)
                curveTo(21.9898f, 10.0908f, 21.6355f, 9.3893f, 21.038f, 8.933f)
                lineTo(14.4577f, 3.6853f)
                curveTo(13.3049f, 2.7716f, 11.6662f, 2.7716f, 10.5134f, 3.6853f)
                lineTo(3.962f, 8.9426f)
                curveTo(3.3623f, 9.397f, 3.0074f, 10.0997f, 3.0f, 10.8474f)
                verticalLineTo(19.5618f)
                curveTo(3.0f, 21.4607f, 4.5549f, 23.0f, 6.4729f, 23.0f)
                horizontalLineTo(8.397f)
                curveTo(9.0823f, 23.0f, 9.638f, 22.4499f, 9.638f, 21.7714f)
                verticalLineTo(21.7714f)
            }
        }
        .build()
        return _selectedhome!!
    }

private var _selectedhome: ImageVector? = null
