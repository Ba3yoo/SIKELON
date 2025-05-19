package com.rati.sikelon.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.view.reusable.Card


data class CardData(
    val imageId: Int,
    val price: String,
    val description: String,
    val iconId: Int? = null
)

@Composable
fun HomePage(navController: NavHostController) {
    val flashSaleItems = listOf(
        CardData(R.drawable.sate, "Rp34.000", "Prenagen Lacta Mom 180 Gr", iconId = R.drawable.ic_launcher_foreground),
        CardData(R.drawable.sate, "Rp46.900", "Shinzu'i Sabun Mandi Refill 725 ml"),
        CardData(R.drawable.sate, "Rp52.217", "Kopi Kapal Api 350 Gr")
    )

    val quickBuyItems = listOf(
        CardData(R.drawable.sate, "Rp3.700", "Le Minerale Air Mineral Botol 600 ml"),
        CardData(R.drawable.sate, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g"),
        CardData(R.drawable.sate, "Rp10.800", "Bear Brand Susu Steril 189 ml")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Banner tetap pakai material Card biasa
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navController.navigate("payment/Produk1/1/10000/123")
                },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF44336))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("SPECIAL OFFER", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White))
                Text(
                    "SINGLE'S DAY\n11.11",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White),
                    lineHeight = 24.sp
                )
                Button(
                    onClick = { /* aksi promo */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("SHOP NOW", style = TextStyle(fontSize = 12.sp, color = Color.Red))
                }
            }
        }

        // Flash Sale Section
        FlashSaleSection(flashSaleItems, navController)

        // Quick Buy Section
        QuickBuySection(quickBuyItems, navController)
    }
}

@Composable
fun FlashSaleSection(items: List<CardData>, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Flash Sale", style = MaterialTheme.typography.titleMedium)
            Text(
                "Lihat Semua",
                modifier = Modifier.clickable { /* TODO navigasi lihat semua */ },
                color = Color(0xFF9F2BFF)
            )
        }

        LazyRow(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                Card(
                    cardData = item,
                    modifier = Modifier,
                    onClick = {
                        val priceCleaned = item.price.replace("Rp", "").replace(".", "")
                        val route = "${NavItem.Payment.route}/${item.description}/1/$priceCleaned/${item.imageId}"
                        navController.navigate(route)
                    }
                )
            }
        }
    }
}

@Composable
fun QuickBuySection(items: List<CardData>, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Beli Cepat", style = MaterialTheme.typography.titleMedium)
            Text(
                "Lihat Semua",
                modifier = Modifier.clickable { /* TODO navigasi lihat semua */ },
                color = Color(0xFF9F2BFF)
            )
        }

        LazyRow(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                Card(
                    cardData = item,
                    modifier = Modifier,
                    onClick = {
                        val priceCleaned = item.price.replace("Rp", "").replace(".", "")
                        val route = "${NavItem.Payment.route}/${item.description}/1/$priceCleaned/${item.imageId}"
                        navController.navigate(route)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    val navController = rememberNavController()
    HomePage(navController = navController)
}
