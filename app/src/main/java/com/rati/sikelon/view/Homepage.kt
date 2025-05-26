package com.rati.sikelon.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.view.reusable.AppBottomNavigationBar
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
        CardData(R.drawable.sate, "Rp34.000", "Prenagen Lacta Mom 180 Gr", R.drawable.add_button),
        CardData(R.drawable.sate, "Rp46.900", "Shinzu'i Sabun Mandi Refill 725 ml"),
        CardData(R.drawable.sate, "Rp52.217", "Kopi Kapal Api 350 Gr")
    )

    val quickBuyItems = listOf(
        CardData(R.drawable.sate, "Rp3.700", "Le Minerale Air Mineral Botol 600 ml"),
        CardData(R.drawable.sate, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g"),
        CardData(R.drawable.sate, "Rp10.800", "Bear Brand Susu Steril 189 ml")
    )

    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationBar()

            TopSearchAndFilterBar(
                navController = navController,
                onFilterClick = {
                    // Aksi klik filter (opsional)
                }
            )

            // Banner Promo
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        navController.navigate("payment/PromoSpecial/1/11000/111")
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.height(180.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.sate), //default photo😭😭
                        contentDescription = "Promo Banner",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            "SPECIAL OFFER",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        )
                        Text(
                            "SINGLE'S DAY\n11.11",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color.White
                            ),
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
            }

            ProductSection(
                title = "Flash Sale",
                items = flashSaleItems,
                navController = navController,
                onSeeAllClick = { /* navigasi lihat semua flash sale */ }
            )

            ProductSection(
                title = "Beli Cepat",
                items = quickBuyItems,
                navController = navController,
                onSeeAllClick = { /* navigasi lihat semua quick buy */ }
            )
        }
    }
}

@Composable
fun ProductSection(
    title: String,
    items: List<CardData>,
    navController: NavHostController,
    onSeeAllClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(
                "Lihat Semua",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF9F2BFF))
                    .clickable { onSeeAllClick() }
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }

        LazyRow(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                Card(cardData = item, modifier = Modifier, onClick = {
                    val priceCleaned = item.price.replace("Rp", "").replace(".", "")
                    val route = "${NavItem.Payment.route}/${item.description}/1/$priceCleaned/${item.imageId}"
                    navController.navigate(route)
                })
            }
        }
    }
}


@Composable
fun LocationBar(
    modifier: Modifier = Modifier,
    currentLocation: String = "Jl. Veteran No 8",
    onLocationClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onLocationClick)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "Ikon Lokasi",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Text(
                text = "Lokasi",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = currentLocation,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Pilih Lokasi",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDisplayField(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        readOnly = true,
        placeholder = { Text("Cari") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari") },
        modifier = modifier
            .height(50.dp)
            .clickable {
                navController.navigate(NavItem.Searched.route)
            },
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            disabledBorderColor = Color.Transparent,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        shape = RoundedCornerShape(25.dp),
        enabled = false
    )
}

@Composable
fun TopSearchAndFilterBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onFilterClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SearchDisplayField(
            modifier = Modifier.weight(1f),
            navController = navController
        )
        IconButton(
            onClick = onFilterClick,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "Filter"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    val navController = rememberNavController()
    HomePage(navController = navController)
}
