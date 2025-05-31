package com.rati.sikelon.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import com.rati.sikelon.R
import com.rati.sikelon.model.Item
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.viewmodel.UserViewModel

@Composable
fun DetailedPromo(
    navController: NavController,
    viewModel: UserViewModel = viewModel()
) {
    val selectedStore by viewModel.selectedStore.collectAsState()
    val items by viewModel.items.collectAsState()

    // Load items when selected store changes
    LaunchedEffect(selectedStore) {
        selectedStore?.store_id?.let { storeId ->
            viewModel.loadItemById(storeId)
        }
    }

    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        selectedStore?.let { store ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_button),
                    contentDescription = "Store Icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${store.store_name}, ${store.address}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                state = rememberLazyListState(),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items, key = { it.item_id }) { product ->
                    ProductCard(item = product) {
                        val route = "${NavItem.Payment.route}/${product.item_name}/1/${product.price}/"
                        navController.navigate(route)
                    }
                }
            }
        } ?: Text(
            text = "Tidak ada toko yang dipilih.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ProductCard(item: Item, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(162.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = item.img_link,
                contentDescription = item.item_name,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.item_name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Rp ${item.price}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
