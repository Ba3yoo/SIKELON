package com.rati.sikelon.view

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R

// Data class to represent a trending product
data class TrendProduct(
    val id: String,
    val name: String,
    val price: String,
    val soldCount: Int,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendDetailScreen(navController: NavController) {
    // Sample data for trending products, replace with actual data from ViewModel/Repository
    val trendingProducts = listOf(
        TrendProduct("1", "Le Minerale Air Mineral Botol 600 ml", "Rp3,500,00", 89, R.drawable.sate),
        TrendProduct("2", "Indomie Goreng Special Mie Instan 85gram", "Rp3,500,00", 80, R.drawable.sate),
        TrendProduct("3", "Nuvo Family Sabun Mandi Batang Anti Bakteri Total Protect 100 g", "Rp3,500,00", 35, R.drawable.sate),
        TrendProduct("4", "TONG TJI Teh Celup Melati 25 Kantung Celup", "Rp12,000,00", 25, R.drawable.sate),
        TrendProduct("5", "Sunlight Anti Bau Sabun Cuci Piring Lime & Mint Refill 600 ml", "Rp15,000,00", 20, R.drawable.sate),
        TrendProduct("6", "Gulaku Gula Pasir Tebu Premium 1 kg", "Rp17,500,00", 19, R.drawable.sate),
        TrendProduct("7", "Indomie Mi Instan Goreng Rendang 91 g", "Rp3,500,00", 19, R.drawable.sate),
        TrendProduct("8", "Frisian Flag Susu Kental Manis Bendera Putih 560 gr", "Rp16,000,00", 0, R.drawable.sate)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Judul di kiri
                        Text(
                            text = "Tren Penjualan",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )

                        // "Teratas" + ikon filter di kanan atas
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .clip(RoundedCornerShape(8.dp))
                                .background(colorResource(id = R.color.purple_500))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Teratas", fontSize = 14.sp, color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Filter",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(trendingProducts) { product ->
                ProductTrendItem(product = product)
            }
        }
    }
}

@Composable
fun ProductTrendItem(product: TrendProduct) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Product Name
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Product Price
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            // Sold Count
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Terjual :",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = product.soldCount.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrendDetailScreenPreview() {
    TrendDetailScreen(navController = rememberNavController())
}