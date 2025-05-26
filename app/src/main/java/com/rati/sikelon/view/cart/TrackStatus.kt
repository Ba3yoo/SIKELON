package com.rati.sikelon.view.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.CardDefaults
import com.rati.sikelon.R // Import file R

// Data class untuk item produk
data class OrderItem(val name: String, val quantity: Int, val price: String, val imageId: Int)

// Data class untuk status pesanan
data class OrderStatus(val status: String, val time: String, val isCompleted: Boolean)

@Composable
fun TrackStatus() {
    // Contoh data item produk
    val orderItems = listOf(
        OrderItem(
            "Beng-Beng Maxx Cokelat 32 g",
            5,
            "Rp24.500",
            R.drawable.beng_beng_max
        ), // Ganti dengan ID gambar yang sesuai
        OrderItem(
            "Beng-Beng Nuts Karamel Almond 35 g",
            3,
            "Rp25.200",
            R.drawable.beng_beng_nuts
        )  // Ganti dengan ID gambar yang sesuai
    )

    // Contoh data status pesanan
    val orderStatusList = listOf(
        OrderStatus("Pesanan dibuat", "23 Juni 2025, 15.25 WIB", true),
        OrderStatus("Pesanan diantar", "Perkiraan 23 Juni 2025, 16.00 WIB", true),
        OrderStatus("Pesanan sampai", "Perkiraan 23 Juni 2025, 18.00 WIB", false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button (Simplified)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace dengan ikon back Anda
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Pelacakan Pesanan",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Daftar Item Produk
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Item yang Dipesan",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyColumn {
                    items(orderItems) { item ->
                        OrderItemCard(item = item)
                    }
                }
            }
        }

        // Detail Pesanan
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Detail Pesanan",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Perkiraan Sampai: 30 Mei 2025, pukul 20.00", // Ganti dengan data yang sesuai
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
                Text(
                    text = "Tracking ID: ABC1234DEF", // Ganti dengan data yang sesuai
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }
        }

        // Status Pesanan
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Status Pesanan",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    orderStatusList.forEach { orderStatus ->
                        OrderStatusItem(orderStatus = orderStatus)
                    }
                }
            }

        }
    }
}

@Composable
fun OrderItemCard(item: OrderItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = item.imageId), // Gunakan ID gambar dari data
                contentDescription = item.name,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Column(modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(0.7f)
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Text(
                    text = "x${item.quantity}",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }
        }
        Text(
            text = item.price,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        )
    }
}

@Composable
fun OrderStatusItem(orderStatus: OrderStatus) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp) // spasi antar status
        ) {
            // Ikon status
            val icon = if (orderStatus.isCompleted) R.drawable.make_order else R.drawable.unmake_order
            val description = if (orderStatus.isCompleted) "Completed" else "In Progress"

            Image(
                painter = painterResource(id = icon),
                contentDescription = description,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Kolom untuk status dan waktu
            Column {
                Text(
                    text = orderStatus.status,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 16.sp,
                        color = if (orderStatus.isCompleted) Color.Black else Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = orderStatus.time,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TrackStatusPagePreview() {
    TrackStatus()
}
