package com.phone.phonelcdparts.resource.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.phone.phonelcdparts.resource.MyIconPack

public val MyIconPack.Unselectedorder: ImageVector
    get() {
        if (_unselectedorder != null) {
            return _unselectedorder!!
        }
        _unselectedorder = Builder(name = "Unselectedorder", defaultWidth = 25.0.dp, defaultHeight =
                25.0.dp, viewportWidth = 25.0f, viewportHeight = 25.0f).apply {
            path(fill = SolidColor(Color(0xFF0C1A30)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(17.977f, 7.7259f)
                curveTo(17.6927f, 5.1407f, 15.3806f, 3.1251f, 12.5705f, 3.1251f)
                curveTo(11.1282f, 3.1193f, 9.7396f, 3.6581f, 8.7152f, 4.6224f)
                curveTo(7.826f, 5.4593f, 7.2748f, 6.557f, 7.1448f, 7.7259f)
                horizontalLineTo(7.1439f)
                curveTo(5.1749f, 7.7259f, 3.5207f, 9.0921f, 3.056f, 11.4643f)
                lineTo(2.215f, 17.6279f)
                curveTo(1.4992f, 22.141f, 3.7332f, 24.1249f, 8.1055f, 24.1249f)
                horizontalLineTo(17.0552f)
                curveTo(21.0985f, 24.1249f, 23.5153f, 21.8887f, 23.019f, 17.8755f)
                lineTo(22.1508f, 11.5057f)
                curveTo(21.5943f, 9.1039f, 19.9826f, 7.7259f, 18.0561f, 7.7259f)
                horizontalLineTo(17.977f)
                close()
                moveTo(16.3563f, 7.7259f)
                curveTo(16.0816f, 5.9825f, 14.491f, 4.6452f, 12.5671f, 4.6452f)
                curveTo(11.5491f, 4.6411f, 10.5713f, 5.0205f, 9.85f, 5.6995f)
                curveTo(9.2643f, 6.2508f, 8.8869f, 6.9626f, 8.7656f, 7.7259f)
                horizontalLineTo(16.3563f)
                close()
                moveTo(7.1438f, 9.2462f)
                horizontalLineTo(18.0561f)
                curveTo(19.1859f, 9.2462f, 20.1784f, 10.0949f, 20.5681f, 11.7664f)
                lineTo(21.3899f, 17.8222f)
                curveTo(21.879f, 21.044f, 20.251f, 22.6049f, 17.0551f, 22.6049f)
                horizontalLineTo(8.1055f)
                curveTo(4.6449f, 22.6049f, 3.2483f, 21.3647f, 3.8076f, 17.8378f)
                lineTo(4.644f, 11.7f)
                curveTo(4.9637f, 10.0748f, 5.967f, 9.2462f, 7.1438f, 9.2462f)
                close()
                moveTo(15.75f, 12.0672f)
                curveTo(16.1941f, 12.0672f, 16.5541f, 12.4075f, 16.5541f, 12.8272f)
                curveTo(16.5541f, 13.212f, 16.2516f, 13.53f, 15.8591f, 13.5804f)
                lineTo(15.75f, 13.5873f)
                horizontalLineTo(15.7009f)
                curveTo(15.2568f, 13.5873f, 14.8969f, 13.247f, 14.8969f, 12.8272f)
                curveTo(14.8969f, 12.4424f, 15.1993f, 12.1244f, 15.5918f, 12.0741f)
                lineTo(15.7009f, 12.0672f)
                horizontalLineTo(15.75f)
                close()
                moveTo(10.3031f, 12.8272f)
                curveTo(10.3031f, 12.4075f, 9.9431f, 12.0672f, 9.499f, 12.0672f)
                horizontalLineTo(9.4499f)
                lineTo(9.3408f, 12.0741f)
                curveTo(8.9484f, 12.1244f, 8.6459f, 12.4424f, 8.6459f, 12.8272f)
                curveTo(8.6459f, 13.247f, 9.0059f, 13.5873f, 9.4499f, 13.5873f)
                horizontalLineTo(9.499f)
                lineTo(9.6081f, 13.5804f)
                curveTo(10.0006f, 13.53f, 10.3031f, 13.212f, 10.3031f, 12.8272f)
                close()
            }
        }
        .build()
        return _unselectedorder!!
    }

private var _unselectedorder: ImageVector? = null
