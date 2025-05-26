package com.rati.sikelon.view.search

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R // Update with actual package
import com.rati.sikelon.ui.theme.SIKELONTheme

data class ProductResult(
    val id: String,
    @DrawableRes val imageResId: Int,
    val price: String,
    val name: String
)

data class StoreSearchResult(
    val storeId: String,
    @DrawableRes val storeIconResId: Int,
    val storeName: String,
    val storeLocationHint: String,
    val distance: String,
    val products: List<ProductResult>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    navController: NavController,
    initialQuery: String = "Beng-beng"
) {
    var searchQuery by rememberSaveable { mutableStateOf(initialQuery) }
    val focusManager = LocalFocusManager.current

    val sampleSearchResults = remember {
        listOf(
            StoreSearchResult(
                "s1", R.drawable.ic_launcher_foreground, "SRC Raya", "Sigura-Gura", "50m",
                listOf(
                    ProductResult("p1_1", R.drawable.sate, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g")
                )
            ),
            StoreSearchResult(
                "s2", R.drawable.ic_launcher_foreground, "SRC Berkah Selalu", "Sigura-Gura", "150m",
                listOf(
                    ProductResult("p2_1", R.drawable.sate, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g"),
                    ProductResult("p2_2", R.drawable.sate, "Rp14.900", "Beng-beng Share It 10 x 8.5 g"),
                    ProductResult("p2_3", R.drawable.sate, "Rp8.700", "Beng-Beng Wafer Rice Crispy Cokelat 3 x 20 g")
                )
            ),
            StoreSearchResult(
                "s3", R.drawable.ic_launcher_foreground, "Toko Kurnia", "Bend. Sutami", "200m",
                listOf(
                    ProductResult("p3_1", R.drawable.sate, "Rp8.400", "Beng-Beng Nuts Karamel Almond 35 g"),
                    ProductResult("p3_2", R.drawable.sate, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g")
                )
            )
        )
    }

    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            SearchResultTopAppBar(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it },
                onBackClick = { navController.popBackStack() },
                onClearClick = { searchQuery = "" },
                onSearchAction = {
                    focusManager.clearFocus()
                    println("Searching for: $searchQuery")
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            state = listState
        ) {
            item {
                LocationDisplay(
                    currentLocation = "Jl. Veteran No 8",
                    onLocationClick = { /* TODO */ }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(sampleSearchResults, key = { it.storeId }) { storeResult ->
                StoreResultRow(storeResult = storeResult, navController = navController)
            }
        }
    }
}

@Composable
fun LocationDisplay(
    currentLocation: String,
    onLocationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onLocationClick)
            .padding(horizontal = 32.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Lokasi", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.width(8.dp))
        Text(currentLocation, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}


@Composable
fun StoreResultRow(storeResult: StoreSearchResult, navController: NavController) {
    val rowState = rememberLazyListState()

    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 26.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = storeResult.storeIconResId),
                contentDescription = "${storeResult.storeName} icon",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${storeResult.storeName}, ${storeResult.storeLocationHint}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = storeResult.distance,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            state = rowState
        ) {
            items(storeResult.products, key = { it.id }) { product ->
                ProductResultCard(
                    product = product,
                    onProductClick = { /* TODO */ },
                    onAddToCartClick = { /* TODO */ }
                )
            }
        }
    }
}

@Composable
fun ProductResultCard(
    product: ProductResult,
    onProductClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onProductClick,
        modifier = modifier
            .width(140.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = product.imageResId),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                )
                IconButton(
                    onClick = onAddToCartClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(4.dp)
                        .size(32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_button),
                        contentDescription = product.name,
                        modifier = Modifier.matchParentSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(product.price, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Text(product.name, maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 14.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultTopAppBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onClearClick: () -> Unit,
    onSearchAction: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp) // Tambahan padding agar tidak full width
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                        .height(40.dp), // 30% lebih kecil dari sebelumnya
                    placeholder = {
                        Text(
                            "Cari lagi...",
                            fontSize = 12.sp // perkecil teks
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { onSearchAction() }),
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            OutlinedButton(
                                onClick = onClearClick,
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(2.dp),
                                modifier = Modifier.size(28.dp) // lebih kecil
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Hapus teks",
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(16.dp) // perkecil ikon
                                )
                            }
                        }
                    }
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .padding(start = 32.dp)
                    .size(30.dp)
                    .background(Color.Black, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Kembali",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchResultScreen() {
    // Gunakan tema aplikasi Anda
    SIKELONTheme  {
        // Buat NavController dummy untuk preview
        val navController = rememberNavController()
        SearchResultScreen(navController = navController)
    }
}
