package com.phone.phonelcdparts.ui.screen.order_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.domain.model.OrderItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logobigdark
import com.phone.phonelcdparts.resource.myiconpack.Logosmall
import com.phone.phonelcdparts.resource.myiconpack.Logosmallbw
import com.phone.phonelcdparts.ui.component.shimmerEffect

@Composable
fun OrderItemView(orderItem: OrderItem, onViewMoreClicked: (incrementId: OrderItem) -> Unit) {
    Box(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
            .background(Colors.text_field_bg_color).padding(15.dp)
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Row {
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Order Id: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Colors.text_theme
                        )
                        Text(
                            text = "${orderItem.entity_id}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Placed On: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Colors.text_theme
                        )
                        Text(
                            text = orderItem.createdAt,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                        )
                    }
                }
                Text(
                    text = "$${orderItem.grandTotal}",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Colors.text_theme
                )

            }


            val firstItem = orderItem.items[0]
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                    .background(Colors.white).padding(10.dp)
            ) {

                Row(Modifier.fillMaxWidth()) {
                    Image(
                        modifier = Modifier.size(100.dp).padding(20.dp),
                        imageVector = MyIconPack.Logosmall,
                        contentDescription = null
                    )

                    Spacer(Modifier.width(10.dp))

                    Column(
                        Modifier.weight(1f).height(100.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = firstItem.name,
                            maxLines = 2,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Colors.text_theme
                        )
                        Spacer(Modifier.height(15.dp))

                        Row {

                            Box(
                                Modifier.weight(70f),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row {
                                    Text(
                                        text = "SKU: ",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = Colors.text_theme
                                    )
                                    Text(
                                        text = firstItem.sku,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 12.sp,
                                    )
                                }

                            }


                            Box(
                                Modifier.weight(30f),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(
                                    text = "$${firstItem.price_incl_tax}",
                                    fontSize = 14.sp,
                                    color = Colors.green,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

            }

            Box(
                Modifier.fillMaxWidth().height(1.5.dp)
                    .background(Color.LightGray.copy(alpha = .5f))
            )

            Row {

                Box(
                    Modifier.weight(50f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Items (${orderItem.items.size})",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Colors.text_hint
                    )

                }


                Box(Modifier.weight(50f), contentAlignment = Alignment.CenterEnd) {
                    Text(
                        modifier = Modifier.clickable {
                            onViewMoreClicked(orderItem)
                        },
                        text = "View More >>",
                        fontSize = 14.sp,
                        color = Colors.text_theme,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

@Composable
fun ShimmerOrderItemView(isError: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
            .background(Colors.text_field_bg_color).padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row {
            Column(Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.height(15.dp).fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect()
                )
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier.height(15.dp).fillMaxWidth(.5f)
                        .clip(RoundedCornerShape(10.dp)).shimmerEffect()
                )
            }

            Spacer(Modifier.width(15.dp))
            Box(Modifier.width(100.dp)) {
                Box(
                    modifier = Modifier.width(80.dp).height(25.dp)
                        .clip(RoundedCornerShape(10.dp)).shimmerEffect()
                )
            }
        }
        Row(
            Modifier.fillMaxWidth().height(120.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ).background(Colors.white).padding(10.dp)
        ) {
            Box(
                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(10.dp))
                    .shimmerEffect(),
                contentAlignment = Alignment.Center
            ){
                if (isError){
                    Image(
                        modifier = Modifier.fillMaxHeight(.5f),
                        imageVector = MyIconPack.Logosmallbw,
                        contentDescription = null
                    )
                }
            }
            Column(
                Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Column {
                    Box(
                        modifier = Modifier.height(15.dp).fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp)).shimmerEffect()
                    )
                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier.height(15.dp).fillMaxWidth(.3f)
                            .clip(RoundedCornerShape(10.dp)).shimmerEffect()
                    )
                }
                Spacer(Modifier.fillMaxHeight().weight(1f))
                Row {
                    Box(Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier.height(15.dp).fillMaxWidth(.7f)
                                .clip(RoundedCornerShape(10.dp))
                                .shimmerEffect()
                        )
                    }
                    Box(
                        Modifier.weight(1f),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Box(
                            modifier = Modifier.height(15.dp).fillMaxWidth(.5f)
                                .clip(RoundedCornerShape(10.dp)).shimmerEffect()
                        )
                    }

                }

            }
        }

        Row {
            Box(Modifier.weight(1f)) {
                Box(
                    modifier = Modifier.height(15.dp).fillMaxWidth(.5f)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect()
                )
            }
            Box(Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                Box(
                    modifier = Modifier.height(15.dp).fillMaxWidth(.6f)
                        .clip(RoundedCornerShape(10.dp)).shimmerEffect()
                )
            }
        }
    }
}