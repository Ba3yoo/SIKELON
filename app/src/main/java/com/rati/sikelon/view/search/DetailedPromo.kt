package com.rati.sikelon.view.search

import android.R.attr.icon
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rati.sikelon.R
import com.rati.sikelon.model.Item
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.viewmodel.UserViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun DetailedPromo(
    navController: NavController,
    viewModel: UserViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        Log.d("DetailedPromo", "Trigger load stores")
        viewModel.loadStores()
    }

    val stores by viewModel.stores.collectAsState()
    val items by viewModel.items.collectAsState()

    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        if (stores.isEmpty()) {
            Text(
                text = "Tidak ada toko yang tersedia.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            stores.forEach { store ->
                StoreSection(
                    storeName = store.store_name,
                    storeAddress = store.address,
                    products = items.filter { it.store_id == store.store_id },
                    onItemClick = { product ->
                        val route = "${NavItem.Payment.route}/${product.item_id}"
                        Log.d("DetailedPromo", "Navigating to: $route")
                        navController.navigate(route)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun StoreSection(
    storeName: String,
    storeAddress: String,
    products: List<Item>,
    onItemClick: (Item) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Store,
                contentDescription = "Store Icon",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "$storeName, $storeAddress",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products, key = { it.item_id }) { item ->
                ProductCard(item = item) {
                    onItemClick(item)
                }
            }
        }
    }
}

@Composable
fun ProductCard(item: Item, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            CoilImage(
                imageModel = { item.img_link },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp),
                        strokeWidth = 2.dp
                    )
                },
                failure = {
                    Image(
                        painter = painterResource(id = R.drawable.sate),
                        contentDescription = "Failed to load image",
                        modifier = Modifier.size(84.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.item_name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Rp ${item.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
