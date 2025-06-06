package com.rati.sikelon.view

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rati.sikelon.R
import com.rati.sikelon.model.Item
import com.rati.sikelon.view.search.DetailedPromo
import com.rati.sikelon.model.User
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.view.reusable.AppBottomNavigationBar
import com.rati.sikelon.view.reusable.Card
import com.rati.sikelon.viewmodel.UserViewModel

//data class CardData(
//    val imageId: Int,
//    val price: String,
//    val description: String,
//    val iconId: Int? = null
//)

@Composable
fun HomePage(navController: NavHostController, viewModel: UserViewModel) {
    val frontItems = viewModel.items.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadItems()
    }
    Log.d("item", frontItems.value.toString())


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
                        navController.navigate(NavItem.DetailedPromo.route)
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
                            onClick = {
                                navController.navigate(NavItem.DetailedPromo.route)
                            },
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
                items = frontItems.value.take(3),
                navController = navController,
                onSeeAllClick = {
                    navController.navigate(NavItem.DetailedPromo.route)
                }
            )

            ProductSection(
                title = "Beli Cepat",
                items = frontItems.value.take(5),
                navController = navController,
                onSeeAllClick = {
                    navController.navigate(NavItem.DetailedPromo.route)
                }
            )
        }
    }
}

@Composable
fun ProductSection(
    title: String,
    items: List<Item>,
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
                    val route = "${NavItem.Payment.route}/${item.item_id}"
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
                navController.navigate(NavItem.Search.route)
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
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = "Filter"
            )
        }
    }
}

// --- Data Classes for Sales Trend ---
data class SalesTrendItem(
    val id: Int,
    val name: String,
    val imageUrl: Int, // Using drawable resource ID
    val soldCount: Int
)

// --- Mock Data ---
val mockSalesTrendItems = listOf(
    SalesTrendItem(1, "Le Minerale Air Mineral Botol 600 ml", R.drawable.sate, 109),
    SalesTrendItem(2, "Indomie Goreng Special Mie Instan 85gram", R.drawable.sate, 80),
    SalesTrendItem(
        3,
        "Nuvo Family Sabun Mandi Batang Anti Bakteri Total Protect 100 g",
        R.drawable.sate,
        35
    ),
    SalesTrendItem(4, "TONG TJI Teh Celup Melati 25 Kantung Celup", R.drawable.sate, 25),
    SalesTrendItem(
        5,
        "Sunlight Anti Bau Sabun Cuci Piring Lime & Mint Refill 600 ml",
        R.drawable.sate,
        20
    )
)

@Composable
fun DashboardScreen(navController: NavHostController, viewModel: UserViewModel) {
    val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("seller")
    val years = listOf("2023", "2024", "2025")
    var selectedYear by remember { mutableStateOf(years.last()) }
    var expanded by remember { mutableStateOf(false) }

    Log.d("dashseller", user?.name ?: "no")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Welcome
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(user?.name ?: "", fontSize = 18.sp, color = Color.Black)
                Text(
                    "${user?.username}!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }

        // Revenue
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.purple_500)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {
                    Text(
                        "Jumlah Pendapatan",
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Rp4.384.000,00",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        // Sales Statistics
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Statistik Penjualan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    Box {
                        Button(
                            onClick = { expanded = true },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(selectedYear, fontSize = 14.sp)
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Pilih Tahun")
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            years.forEach { year ->
                                DropdownMenuItem(
                                    text = { Text(year) },
                                    onClick = {
                                        selectedYear = year
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    // Placeholder for chart
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("10", "8", "6", "4", "2", "0").forEach {
                            Text(it, fontSize = 10.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }

        // Sales Trend
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Tren Penjualan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    TextButton(
                        onClick = {
                            navController.navigate(NavItem.TrendSale.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.purple_500),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text("Lihat Semua", fontWeight = FontWeight.Medium)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (mockSalesTrendItems.isEmpty()) {
                    Text(
                        "Belum ada tren penjualan.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                } else {
                    mockSalesTrendItems.forEachIndexed { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = item.imageUrl),
                                contentDescription = item.name,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(colorResource(id = R.color.purple_300)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    maxLines = 2
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Terjual: ${item.soldCount}",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                        if (index < mockSalesTrendItems.lastIndex) {
                            HorizontalDivider(thickness = 1.dp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DashboardPreview() {
//    DashboardScreen(navController = )
//}

//@Preview(showBackground = true)
//@Composable
//fun HomePagePreview() {
//    val navController = rememberNavController()
//    HomePage(navController = navController)
//}
