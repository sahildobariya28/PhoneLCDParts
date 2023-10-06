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

val MyIconPack.Selectedcategory: ImageVector
    get() {
        if (_selectedcategory != null) {
            return _selectedcategory!!
        }
        _selectedcategory = Builder(name = "Selectedcategory", defaultWidth = 25.0.dp, defaultHeight
                = 25.0.dp, viewportWidth = 25.0f, viewportHeight = 25.0f).apply {
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
            path(fill = SolidColor(Color(0xFF3669C9)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(8.9138f, 2.0f)
                horizontalLineTo(4.6663f)
                curveTo(3.146f, 2.0116f, 2.0087f, 3.1374f, 2.0087f, 4.6693f)
                curveTo(1.9971f, 6.0852f, 1.9971f, 7.501f, 2.0087f, 8.9169f)
                curveTo(2.0087f, 10.4372f, 3.146f, 11.5746f, 4.6663f, 11.5862f)
                horizontalLineTo(8.9138f)
                curveTo(10.4457f, 11.5746f, 11.5714f, 10.4489f, 11.583f, 8.9169f)
                verticalLineTo(4.6693f)
                curveTo(11.5714f, 3.1374f, 10.4457f, 2.0116f, 8.9138f, 2.0f)
                close()
                moveTo(9.8422f, 8.9169f)
                curveTo(9.8306f, 9.474f, 9.4709f, 9.8338f, 8.9138f, 9.8454f)
                horizontalLineTo(4.6663f)
                curveTo(4.1093f, 9.8338f, 3.7495f, 9.474f, 3.7495f, 8.9169f)
                curveTo(3.7379f, 7.501f, 3.7379f, 6.0736f, 3.7495f, 4.6461f)
                curveTo(3.7495f, 4.1122f, 4.1093f, 3.7524f, 4.6547f, 3.7524f)
                curveTo(5.3742f, 3.7408f, 6.0937f, 3.7408f, 6.8017f, 3.7408f)
                horizontalLineTo(8.9022f)
                curveTo(9.4709f, 3.7524f, 9.8422f, 4.1006f, 9.8422f, 4.6809f)
                verticalLineTo(8.9169f)
                close()
            }
            path(fill = SolidColor(Color(0xFF3669C9)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(8.9166f, 13.4225f)
                curveTo(7.5002f, 13.4109f, 6.0839f, 13.4109f, 4.6675f, 13.4225f)
                curveTo(3.1467f, 13.4225f, 2.0089f, 14.5602f, 2.0089f, 16.0926f)
                curveTo(1.9973f, 16.7892f, 2.0089f, 17.4857f, 2.0089f, 18.1822f)
                curveTo(2.0089f, 18.902f, 1.9973f, 19.6102f, 2.0089f, 20.3299f)
                curveTo(2.0089f, 21.8623f, 3.1467f, 22.9884f, 4.6675f, 23.0f)
                horizontalLineTo(8.9166f)
                curveTo(10.4491f, 22.9884f, 11.5752f, 21.8623f, 11.5868f, 20.3299f)
                verticalLineTo(16.0926f)
                curveTo(11.5752f, 14.5602f, 10.4491f, 13.4225f, 8.9166f, 13.4225f)
                close()
                moveTo(9.8454f, 20.2951f)
                curveTo(9.8454f, 20.9104f, 9.4855f, 21.2587f, 8.8818f, 21.2587f)
                horizontalLineTo(4.714f)
                curveTo(4.0987f, 21.2587f, 3.7504f, 20.9104f, 3.7504f, 20.3067f)
                curveTo(3.7388f, 18.902f, 3.7388f, 17.5089f, 3.7504f, 16.1158f)
                curveTo(3.7504f, 15.5122f, 4.0987f, 15.1639f, 4.6907f, 15.1639f)
                horizontalLineTo(8.8818f)
                curveTo(9.4855f, 15.1639f, 9.8454f, 15.5122f, 9.8454f, 16.1274f)
                verticalLineTo(20.2951f)
                close()
            }
            path(fill = SolidColor(Color(0xFF3669C9)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(20.3493f, 13.4225f)
                curveTo(18.9221f, 13.4109f, 17.5065f, 13.4109f, 16.0793f, 13.4225f)
                curveTo(14.5593f, 13.4225f, 13.4338f, 14.5592f, 13.4221f, 16.0902f)
                verticalLineTo(18.1895f)
                curveTo(13.4221f, 18.897f, 13.4105f, 19.6161f, 13.4221f, 20.3353f)
                curveTo(13.4338f, 21.8431f, 14.5593f, 22.9797f, 16.0677f, 22.9913f)
                curveTo(17.4949f, 23.0029f, 18.9337f, 23.0029f, 20.3609f, 22.9913f)
                curveTo(21.8577f, 22.9797f, 22.9832f, 21.8431f, 22.9948f, 20.3468f)
                curveTo(22.9948f, 18.9202f, 23.0064f, 17.482f, 22.9948f, 16.0554f)
                curveTo(22.9832f, 14.5592f, 21.8461f, 13.4341f, 20.3493f, 13.4225f)
                close()
                moveTo(21.2544f, 20.3121f)
                curveTo(21.2544f, 20.9036f, 20.8947f, 21.2515f, 20.2913f, 21.2515f)
                horizontalLineTo(16.1025f)
                curveTo(15.5223f, 21.2515f, 15.1626f, 20.892f, 15.1626f, 20.3005f)
                verticalLineTo(16.1134f)
                curveTo(15.1626f, 15.5219f, 15.5107f, 15.1623f, 16.1025f, 15.1623f)
                horizontalLineTo(20.3145f)
                curveTo(20.8947f, 15.1623f, 21.2544f, 15.5219f, 21.2544f, 16.1018f)
                verticalLineTo(20.3121f)
                close()
            }
            path(fill = SolidColor(Color(0xFF3669C9)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(22.9965f, 6.4681f)
                verticalLineTo(8.8821f)
                curveTo(22.9849f, 10.4489f, 21.8591f, 11.5746f, 20.3041f, 11.5862f)
                horizontalLineTo(16.0914f)
                curveTo(14.5595f, 11.5746f, 13.4222f, 10.4372f, 13.4222f, 8.9169f)
                curveTo(13.4106f, 7.501f, 13.4106f, 6.0852f, 13.4222f, 4.6693f)
                curveTo(13.4222f, 3.149f, 14.5595f, 2.0116f, 16.0798f, 2.0f)
                horizontalLineTo(18.5633f)
                curveTo(19.1203f, 2.0116f, 19.5033f, 2.3598f, 19.5149f, 2.8704f)
                curveTo(19.5149f, 3.3811f, 19.1203f, 3.7408f, 18.5517f, 3.7408f)
                curveTo(17.7393f, 3.7524f, 16.9385f, 3.7408f, 16.1262f, 3.7408f)
                curveTo(15.5111f, 3.7524f, 15.1629f, 4.089f, 15.1629f, 4.7041f)
                verticalLineTo(8.8937f)
                curveTo(15.1629f, 9.4856f, 15.5227f, 9.8338f, 16.103f, 9.8454f)
                horizontalLineTo(20.2924f)
                curveTo(20.9075f, 9.8338f, 21.2557f, 9.4856f, 21.2557f, 8.8705f)
                verticalLineTo(6.4798f)
                curveTo(21.2557f, 5.8763f, 21.6038f, 5.4817f, 22.1261f, 5.4933f)
                curveTo(22.6483f, 5.4933f, 22.9965f, 5.8879f, 22.9965f, 6.4681f)
                close()
            }
        }
        .build()
        return _selectedcategory!!
    }

private var _selectedcategory: ImageVector? = null
