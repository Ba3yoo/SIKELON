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
import com.rati.sikelon.model.Item
import com.rati.sikelon.model.StoreSearchResult
import com.rati.sikelon.viewmodel.UserViewModel
import com.rati.sikelon.ui.theme.SIKELONTheme
import com.rati.sikelon.viewmodel.UserViewModel.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultScreen(
    navController: NavController,
    viewModel: SearchViewModel
) {
    var searchQuery by rememberSaveable { mutableStateOf("Beng-beng") }

    val stores by viewModel.stores.collectAsState()
    val items by viewModel.items.collectAsState()

    // Load data when composable is first shown
    LaunchedEffect(Unit) {
        viewModel.loadStores()
        viewModel.loadItems()
    }

    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            SearchResultTopAppBar(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it },
                onBackClick = { /* TODO: handle back */ },
                onClearClick = { searchQuery = "" },
                onSearchAction = { /* TODO: maybe filter or reload */ }
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

            items(stores, key = { it.storeId }) { store ->
                // Filter items that belong to this store by storeId
                val filteredItems = items.filter { it.store_id.toString() == store.storeId }
                StoreResultRow(store.copy(products = filteredItems))
            }
        }
    }
}

@Composable
fun StoreResultRow(storeResult: StoreSearchResult) {
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
                    .clip(MaterialTheme.shapes.small),
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
            items(storeResult.products, key = { it.item_id }) { product ->
                ProductResultCard(product)
            }
        }
    }
}

@Composable
fun ProductResultCard(item: Item) {
    Card(
        modifier = Modifier.size(width = 160.dp, height = 200.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // Placeholder image, replace with Coil or similar for img_link
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Image",
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.item_name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rp ${item.price}",
                style = MaterialTheme.typography.bodySmall
            )
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
    onSearchAction: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            TextField(
                value = searchQuery,
                onValueChange = onQueryChange,
                singleLine = true,
                placeholder = { Text("Search...") },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = onClearClick) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_previous),
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchAction) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_search),
                    contentDescription = "Search"
                )
            }
        }
    )
}

@Composable
fun LocationDisplay(
    currentLocation: String,
    onLocationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currentLocation,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        TextButton(onClick = onLocationClick) {
            Text(text = "Change")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSearchResultScreen() {
    // Gunakan tema aplikasi Anda
    SIKELONTheme  {
        // Buat NavController dummy untuk preview
        val navController = rememberNavController()
        SearchResultScreen(
            navController = navController,
            viewModel = TODO()
        )
    }
}
