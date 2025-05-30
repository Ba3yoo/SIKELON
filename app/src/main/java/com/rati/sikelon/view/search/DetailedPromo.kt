package com.rati.sikelon.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R
import com.rati.sikelon.view.reusable.Card
import com.rati.sikelon.view.reusable.CardData
import com.rati.sikelon.viewmodel.UserViewModel

@Composable
fun DetailedPromo(
    navController: NavController,
    initialQuery: String = "Beng-beng",
    CartItem: UserViewModel = viewModel()
) {
    val cartDetails = CartItem.selectedCartDetail.collectAsState()
    LaunchedEffect(Unit) {
        CartItem.loadCartDetailById(1)
    }
    val sampleSearchResults = remember {
        listOf(
            StoreSearchResult(
                "s1", R.drawable.ic_launcher_foreground, "SRC Raya", "Sigura-Gura", "50m",
                listOf(
                    ProductResult("p1_1", R.drawable.beng_beng_max, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g")
                )
            ),
            StoreSearchResult(
                "s2", R.drawable.ic_launcher_foreground, "SRC Berkah Selalu", "Sigura-Gura", "150m",
                listOf(
                    ProductResult("p2_1", R.drawable.beng_beng_max, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g"),
                    ProductResult("p2_2", R.drawable.beng_beng_share, "Rp14.900", "Beng-beng Share It 10 x 8.5 g"),
                    ProductResult("p2_3", R.drawable.beng_beng_wafer_rice, "Rp8.700", "Beng-Beng Wafer Rice Crispy Cokelat 3 x 20 g")
                )
            ),
            StoreSearchResult(
                "s3", R.drawable.ic_launcher_foreground, "Toko Kurnia", "Bend. Sutami", "200m",
                listOf(
                    ProductResult("p3_1", R.drawable.beng_beng_nuts, "Rp8.400", "Beng-Beng Nuts Karamel Almond 35 g"),
                    ProductResult("p3_2", R.drawable.beng_beng_max, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g")
                )
            )
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(sampleSearchResults) { storeResult ->
            SectionPromo(storeResult = storeResult, navController = navController)
        }
    }
}

@Composable
fun SectionPromo(storeResult: StoreSearchResult, navController: NavController) {
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
            items(storeResult.products.take(3), key = { it.id }) { product ->
                Card(
                    cardData = CardData(
                        imageUrl = "https://via.placeholder.com/150",
                        price = product.price,
                        description = product.name,
                        buttonText = "Beli"
                    ),
                    modifier = Modifier.width(150.dp),
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun Previewdetailed(){
    DetailedPromo(
        navController = rememberNavController(),
        initialQuery = "ikan"
    )
}