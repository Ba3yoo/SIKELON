package com.rati.sikelon.view.search

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rati.sikelon.R
import com.rati.sikelon.model.Item
import com.rati.sikelon.model.StoreSearchResult
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.viewmodel.UserViewModel.SearchViewModel


private val mockStores = listOf(
    StoreSearchResult(
        storeId = "1",
        storeIconResId = R.drawable.toko_kurnia,
        storeName = "Toko Sumber Rejeki",
        storeLocationHint = "Jl. Merdeka No. 10",
        distance = "1.2 km",
        products = listOf(
            Item(
                item_id = 101,
                item_name = "Beras Pandan Wangi 5kg",
                price = 65000,
                store_id = 1,
                img_link = "https://example.com/images/beras.jpg"
            ),
            Item(
                item_id = 102,
                item_name = "Minyak Goreng 2L",
                price = 28000,
                store_id = 1,
                img_link = "https://example.com/images/minyak.jpg"
            )
        )
    ),
    StoreSearchResult(
        storeId = "2",
        storeIconResId = R.drawable.toko_kurnia,
        storeName = "Warung Makmur",
        storeLocationHint = "Jl. Sudirman No. 25",
        distance = "2.5 km",
        products = listOf(
            Item(
                item_id = 201,
                item_name = "Gula Pasir 1kg",
                price = 12000,
                store_id = 2,
                img_link = "https://example.com/images/gula.jpg"
            ),
            Item(
                item_id = 202,
                item_name = "Teh Celup 50s",
                price = 15000,
                store_id = 2,
                img_link = "https://example.com/images/teh.jpg"
            )
        )
    )
)


/**
 * Menampilkan seluruh halaman hasil pencarian.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    navController: NavController,
    viewModel: SearchViewModel,
    initialQuery: String = ""
) {
    var searchQuery by rememberSaveable { mutableStateOf(initialQuery) }
    val focusManager = LocalFocusManager.current
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()



    val allStores by viewModel.stores.collectAsState()
    val allItems by viewModel.items.collectAsState()

    val storesWithProducts = remember(allStores, allItems) {
        if (allStores.isNotEmpty() && allItems.isNotEmpty()) {
            allStores.map { store ->
                val storeProducts = allItems.filter { item ->
                    item.store_id.toString() == store.storeId
                }
                store.copy(products = storeProducts)
            }
        } else {
            mockStores
        }
    }

    // Filter stores and products based on search query
    val filteredStores = remember(storesWithProducts, searchQuery) {
        if (searchQuery.isBlank()) {
            storesWithProducts
        } else {
            storesWithProducts.mapNotNull { store ->
                val filteredProducts = store.products.filter { product ->
                    product.item_name.contains(searchQuery, ignoreCase = true)
                }

                val storeNameMatches = store.storeName.contains(searchQuery, ignoreCase = true)

                if (filteredProducts.isNotEmpty() || storeNameMatches) {
                    store.copy(products = if (storeNameMatches) store.products else filteredProducts)
                } else {
                    null
                }
            }
        }
    }

    // Initialize search query on first composition
    LaunchedEffect(initialQuery) {
        if (initialQuery.isNotBlank() && searchQuery != initialQuery) {
            searchQuery = initialQuery
        }
    }

    LaunchedEffect(storesWithProducts) {
        Log.d("SearchResultScreen", "Combined stores with products: ${storesWithProducts.size} stores")
        storesWithProducts.forEach { store ->
            Log.d("SearchResultScreen", "Store: ${store.storeName} with ${store.products.size} products")
        }
    }

    LaunchedEffect(filteredStores) {
        Log.d("SearchResultScreen", "Filtered results for query '$searchQuery': ${filteredStores.size} stores")
        filteredStores.forEach { store ->
            Log.d("SearchResultScreen", "Store: ${store.storeName} with ${store.products.size} products")
            store.products.forEach { product ->
                Log.d("SearchResultScreen", "- ${product.item_name}: Rp${product.price}")
            }
        }
    }

    Scaffold(
        topBar = {
            SearchResultTopAppBar(
                searchQuery = searchQuery,
                onQueryChange = { newQuery ->
                    searchQuery = newQuery
                    Log.d("SearchResultScreen", "Search query changed to: $newQuery")
                },
                onBackClick = { navController.popBackStack() },
                onClearClick = {
                    searchQuery = ""
                    Log.d("SearchResultScreen", "Search query cleared")
                },
                onSearchAction = {
                    focusManager.clearFocus()
                    Log.d("SearchResultScreen", "Search action performed with query: $searchQuery")
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        // Show error message if there's an error
        error?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                Log.e("SearchResultScreen", "Error: $errorMessage")
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    LocationDisplay(currentLocation = "Jl. Veteran No 8")
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Show search results summary
                if (searchQuery.isNotBlank()) {
                    item {
                        SearchResultSummary(
                            query = searchQuery,
                            storeCount = filteredStores.size,
                            productCount = filteredStores.sumOf { it.products.size }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                filteredStores.forEach { store ->
                    item {
                        StoreHeader(
                            storeName = store.storeName,
                            storeLocationHint = store.storeLocationHint,
                            distance = store.distance,
                            storeIconResId = store.storeIconResId
                        )
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(store.products, key = { it.item_id }) { product ->
                                ProductResultCard(
                                    product = product,
                                    searchQuery = searchQuery,
                                    onAddToCartClick = {
                                        Log.d("SearchResultScreen", "Add to cart clicked for: ${product.item_name}")
//                                        viewModel.addToCart()
                                        navController.navigate("${NavItem.Payment.route}/${product.item_id}")
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                if (filteredStores.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (searchQuery.isBlank()) {
                                        "Mulai pencarian untuk melihat hasil"
                                    } else {
                                        "Tidak ada hasil untuk \"$searchQuery\""
                                    },
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                if (searchQuery.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Coba kata kunci yang berbeda",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
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

/**
 * Menampilkan ringkasan hasil pencarian
 */
@Composable
fun SearchResultSummary(
    query: String,
    storeCount: Int,
    productCount: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Hasil pencarian untuk \"$query\"",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Ditemukan $productCount produk dari $storeCount toko",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * AppBar dengan field pencarian.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultTopAppBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onClearClick: () -> Unit,
    onSearchAction: () -> Unit,
) {
    TopAppBar(
        title = {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(end = 8.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(
                        text = if (searchQuery.isEmpty()) "Cari produk atau toko..." else searchQuery,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearchAction() }),
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = onClearClick) {
                            Icon(Icons.Filled.Close, contentDescription = "Clear search")
                        }
                    }
                }
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
    )
}

/**
 * Menampilkan lokasi pengguna.
 */
@Composable
fun LocationDisplay(currentLocation: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Lokasi",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.back_sikelon),
                contentDescription = "Location Icon",
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = currentLocation,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

/**
 * Header informasi toko.
 */
@Composable
fun StoreHeader(
    storeName: String,
    storeLocationHint: String,
    distance: String,
    @DrawableRes storeIconResId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = storeIconResId),
            contentDescription = "$storeName icon",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = storeName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = storeLocationHint,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = distance,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
    }
}

/**
 * Kartu produk individual dengan highlight pencarian.
 */
@Composable
fun ProductResultCard(
    product: Item,
    searchQuery: String = "",
    onAddToCartClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(190.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                val context = LocalContext.current

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(product.img_link)
                        .crossfade(true)
                        .placeholder(R.drawable.stock_beng_beng)
                        .error(R.drawable.stock_beng_beng)
                        .build(),
                    contentDescription = product.item_name,
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                    fallback = painterResource(id = R.drawable.stock_beng_beng)
                )

                Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                    Text(
                        text = product.item_name,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Normal,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        minLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Rp${product.price}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            IconButton(
                onClick = onAddToCartClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f))
                    .size(30.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add to cart",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}