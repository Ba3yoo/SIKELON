package com.rati.sikelon.view.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R
import com.rati.sikelon.view.reusable.SectionRow

data class ProductItem(val name: String, val quantity: Int, val price: String, val imageId: Int)

val productItems = listOf(
    ProductItem("Beng-Beng Maxx Cokelat 32 g", 5, "Rp24.500", R.drawable.sate),
    ProductItem("Teh Botol Sosro 350ml", 2, "Rp10.000", R.drawable.sate),
    ProductItem("Indomie Goreng Spesial", 3, "Rp9.000", R.drawable.sate)
)

@Composable
fun PaymentScreen(
    item: ProductItem,
    navController: NavController,
    onPaymentComplete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Checkout",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Alamat Section
        SectionRow(
            icon = {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Alamat",
                    tint = Color(0xFF9F2BFF),
                    modifier = Modifier.size(36.dp)
                )
            },
            title = "Alamat",
            actionText = "Ubah",
            onActionClick = { /* TODO: handle click */ }
        ) {
            Text(text = "Rumah", style = sectionContentStyle())
            Text(
                text = "Jl. Kaliurang RT 1/RW 4, Kecamatan Lowokwaru",
                style = sectionSubContentStyle()
            )
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

// Pengiriman Section
        SectionRow(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Pengiriman",
                    tint = Color(0xFF9F2BFF),
                    modifier = Modifier.size(36.dp)
                )
            },
            title = "Pengiriman",
            actionText = "Ubah",
            onActionClick = { /* TODO: handle click */ }
        ) {
            Text(text = "Ojek Online", style = sectionContentStyle())
            Text(text = "Estimasi datang pukul 20.00", style = sectionSubContentStyle())
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

// Pembayaran Section
        SectionRow(
            icon = {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Pembayaran",
                    tint = Color(0xFF9F2BFF),
                    modifier = Modifier.size(36.dp)
                )
            },
            title = "Pembayaran",
            actionText = "Ubah",
            onActionClick = { /* TODO: handle click */ }
        ) {
            Text(text = "COD (cash on delivery)", style = sectionContentStyle())
            Text(text = "bayar sesuai total belanjaan Anda", style = sectionSubContentStyle())
        }


        // Daftar produk
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(productItems) { item ->
                    PaymentProductItem(item)
                }
            }
        }

        // Tombol Bayar
        Button(
            onClick = { onPaymentComplete() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Bayar",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun PaymentProductItem(item: ProductItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = item.name, style = sectionContentStyle())
            Text(text = "Jumlah: ${item.quantity}", style = sectionSubContentStyle())
            Text(text = item.price, style = sectionContentStyle())
        }
    }
}

fun sectionContentStyle() = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 14.sp,
    color = Color.Black
)

fun sectionSubContentStyle() = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 14.sp,
    color = Color.Gray
)

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(
        item = ProductItem(
            name = "he",
            quantity = 10,
            price = "he",
            imageId = R.drawable.ic_launcher_foreground
        ),
        navController = rememberNavController(),
        onPaymentComplete = {}
    )
}
