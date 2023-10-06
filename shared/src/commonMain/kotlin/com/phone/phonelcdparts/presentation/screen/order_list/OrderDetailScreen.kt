package com.phone.phonelcdparts.presentation.screen.order_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phone.phonelcdparts.domain.model.OrderItem
import com.phone.phonelcdparts.resource.Colors
import com.phone.phonelcdparts.resource.MyIconPack
import com.phone.phonelcdparts.resource.myiconpack.Logosmall

@Composable
fun OrderDetailScreen(orderItem: OrderItem) {

    Column(
        Modifier.fillMaxSize().padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(Modifier.fillMaxWidth()) {
            Text(
                "Order Id",
                fontSize = 16.sp,
                color = Colors.text_theme,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = orderItem.incrementId,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                color = Colors.text_hint,
                fontWeight = FontWeight.Medium
            )
        }

        Row(Modifier.fillMaxWidth()) {
            Text(
                "Customer Name",
                fontSize = 16.sp,
                color = Colors.text_theme,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = orderItem.customerFirstname,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                color = Colors.text_hint,
                fontWeight = FontWeight.Medium
            )
        }
        Row(Modifier.fillMaxWidth()) {
            Text(
                "Placed On",
                fontSize = 16.sp,
                color = Colors.text_theme,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = orderItem.createdAt,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                color = Colors.text_hint,
                fontWeight = FontWeight.Medium
            )
        }

        Row(Modifier.fillMaxWidth()) {
            Text(
                "Payment Method",
                fontSize = 16.sp,
                color = Colors.text_theme,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = orderItem.payment.paymentMethod,
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                color = Colors.text_hint,
                fontWeight = FontWeight.Medium
            )
        }
        Row(Modifier.fillMaxWidth()) {
            Text(
                "Total Amount",
                fontSize = 16.sp,
                color = Colors.text_theme,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "$${orderItem.grandTotal}",
                textAlign = TextAlign.End,
                fontSize = 16.sp,
                color = Colors.green,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(10.dp))
        Box(Modifier.fillMaxWidth().height(1.5.dp).background(Color.LightGray.copy(alpha = .5f)))
        Spacer(Modifier.height(10.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {

            items(orderItem.items.size) {

                val firstItem = orderItem.items[it]
                Box(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp))
                        .background(Colors.text_field_bg_color).padding(10.dp)
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
            }
        }
    }
}

