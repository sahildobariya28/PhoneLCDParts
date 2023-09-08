package com.phone.phonelcdparts.ui.screen.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.core.ListDataState
import com.phone.phonelcdparts.domain.model.CategoryItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmall
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.ui.component.shimmerEffect
import com.phone.phonelcdparts.util.toDp
import com.seiko.imageloader.rememberAsyncImagePainter
import io.ktor.http.isSuccess
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(categoryViewModel: CategoryViewModel) {

    val categoryState by categoryViewModel.categoryList.collectAsState()

    Box(Modifier.fillMaxSize().background(Color.White).padding(15.dp)) {

        if (categoryState.isLoading) {
            ShimmerCategoryItemView(false)
        } else if (categoryState.status.isSuccess()) {
            categoryState.data?.let { CategoryItemView(it.filterIsInstance<CategoryItem>()) }
        } else if (!categoryState.status.isSuccess()) {
            ShimmerCategoryItemView(true)
        }


    }
}

@Composable
fun CategoryItemView(categoryList: List<CategoryItem>) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(categoryList.size) { item ->

            var width = remember { mutableStateOf(0) }
            // Replace with your item UI
            Column {
                Box(Modifier.fillMaxWidth().onSizeChanged { width.value = it.width }
                    .height(toDp(width.value)).clip(RoundedCornerShape(15.dp))
                    .background(Colors.text_field_bg_color)) {
                    categoryList[item].customAttributes.forEach {

                        if (it.attributeCode.replace("\"", "").equals("image")) {
                            val painter =
                                rememberAsyncImagePainter(
                                    "${HttpRoutes.CATEGORY_IMG}${
                                        it.value.replace(
                                            "\"",
                                            ""
                                        ).replace("\\", "/")
                                    }",
                                    filterQuality = FilterQuality.None
                                )


                            Box(Modifier.fillMaxSize().clickable { }) {
                                Image(
                                    modifier = Modifier.padding(20.dp).fillMaxSize(),
                                    painter = painter,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.fillMaxWidth().height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = categoryList[item].name,
                    fontWeight = FontWeight.SemiBold,
                    color = Colors.text_theme,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.fillMaxWidth().height(5.dp))
            }

        }
    }
}

@Composable
fun ShimmerCategoryItemView(isError: Boolean) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(12) {
            var width = remember { mutableStateOf(0) }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.fillMaxWidth().onSizeChanged { width.value = it.width }
                    .height(toDp(width.value)).clip(RoundedCornerShape(15.dp))
                    .shimmerEffect(), contentAlignment = Alignment.Center) {

                    if (isError){
                        Image(
                            modifier = Modifier.fillMaxHeight(.5f),
                            imageVector = MyIconPack.Logosmallbw,
                            contentDescription = null
                        )
                    }

                }
                Spacer(Modifier.height(10.dp))
                Box(
                    Modifier.fillMaxWidth(.5f).height(15.dp).clip(CircleShape)
                        .shimmerEffect()
                )
                Spacer(Modifier.fillMaxWidth().height(10.dp))
            }
        }
    }
}