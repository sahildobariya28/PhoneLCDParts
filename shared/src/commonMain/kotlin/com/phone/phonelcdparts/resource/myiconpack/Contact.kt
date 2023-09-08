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

public val MyIconPack.Contact: ImageVector
    get() {
        if (_contact != null) {
            return _contact!!
        }
        _contact = Builder(name = "Contact", defaultWidth = 20.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 20.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF07253B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.1099f, 13.7462f)
                curveTo(15.1587f, 13.5615f, 14.2432f, 13.2259f, 13.3981f, 12.752f)
                curveTo(13.1991f, 12.6419f, 12.9749f, 12.5856f, 12.7475f, 12.5888f)
                curveTo(12.5202f, 12.5921f, 12.2976f, 12.6547f, 12.1019f, 12.7705f)
                lineTo(10.0757f, 13.7572f)
                curveTo(8.3604f, 12.4392f, 7.1219f, 10.5976f, 6.5479f, 8.512f)
                lineTo(8.1785f, 7.0424f)
                curveTo(8.3753f, 6.9101f, 8.5318f, 6.7262f, 8.6311f, 6.5108f)
                curveTo(8.7304f, 6.2955f, 8.7685f, 6.057f, 8.7414f, 5.8214f)
                curveTo(8.6207f, 4.8582f, 8.6549f, 3.8819f, 8.8428f, 2.9295f)
                curveTo(8.9079f, 2.5955f, 8.8379f, 2.2494f, 8.6482f, 1.967f)
                curveTo(8.4585f, 1.6846f, 8.1645f, 1.4889f, 7.8307f, 1.423f)
                lineTo(4.9468f, 0.8581f)
                curveTo(4.6127f, 0.7929f, 4.2663f, 0.863f, 3.9839f, 1.053f)
                curveTo(3.7014f, 1.2429f, 3.5058f, 1.5372f, 3.4401f, 1.8712f)
                curveTo(2.6944f, 5.7009f, 3.4983f, 9.67f, 5.6754f, 12.9078f)
                curveTo(7.8525f, 16.1456f, 11.225f, 18.3875f, 15.053f, 19.1418f)
                curveTo(15.3871f, 19.2069f, 15.7334f, 19.1368f, 16.0159f, 18.9469f)
                curveTo(16.2984f, 18.757f, 16.494f, 18.4627f, 16.5597f, 18.1287f)
                lineTo(17.123f, 15.2528f)
                curveTo(17.1882f, 14.9187f, 17.1181f, 14.5724f, 16.9281f, 14.2899f)
                curveTo(16.7382f, 14.0074f, 16.4439f, 13.8119f, 16.1099f, 13.7462f)
                close()
            }
        }
        .build()
        return _contact!!
    }

private var _contact: ImageVector? = null
