package com.rati.sikelon.view.cart

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R
import com.rati.sikelon.view.cart.CartItem
import com.rati.sikelon.viewmodel.UserViewModel

enum class OrderTab(val title: String) {
    KERANJANG("Keranjang"),
    PROSES("Proses"),
    SELESAI("Selesai")
}

data class OrderItemModel(
    val id: String,
    val storeName: String,
    val storeIconResId: Int,
    val productInfo: String,
    val totalPrice: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartStatusScreen(
    navController: NavController,
    CartItem: UserViewModel = viewModel()
) {
    val cartDetails = CartItem.selectedCartDetail.collectAsState()
    LaunchedEffect(Unit) {
        CartItem.loadCartDetailById(1)
    }
    Log.d("det", cartDetails.value.toString())
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = OrderTab.values()

    val processingOrderItems = remember {
        listOf(
            OrderItemModel("101", "Toko Maju Jaya", R.drawable.sate, "Pesanan sedang dikemas", "Rp150.000")
        )
    }
    val completedOrderItems = remember {
        listOf(
            OrderItemModel("201", "Toko Berkah Selalu", R.drawable.sate, "Pesanan telah diterima", "Rp75.000"),
            OrderItemModel("202", "Toko Kurnia", R.drawable.sate, "Beng-Beng Maxx Cokelat 32 g...", "Rp162.000")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Pesanan Saya",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = { Spacer(modifier = Modifier.width(48.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    if (selectedTabIndex < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                            height = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = tab.title,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                }
            }

            when (tabs[selectedTabIndex]) {
                OrderTab.KERANJANG -> {
                    if (cartDetails.value.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Keranjang masih kosong.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        //usage example
                        cartDetails.value.forEach { detail ->
                            CartItem(detail)
                        }
                    }
                }
                OrderTab.PROSES -> {
                    OrderListContent(
                        items = processingOrderItems,
                        currentTab = OrderTab.PROSES,
                        onActionClick = { orderId, actionType ->
                            println("Aksi: $actionType untuk Order ID: $orderId di tab Proses")
                        }
                    )
                }
                OrderTab.SELESAI -> {
                    OrderListContent(
                        items = completedOrderItems,
                        currentTab = OrderTab.SELESAI,
                        onActionClick = { orderId, actionType ->
                            println("Aksi: $actionType untuk Order ID: $orderId di tab Selesai")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun OrderListContent(
    items: List<OrderItemModel>,
    currentTab: OrderTab,
    onActionClick: (orderId: String, actionType: String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                "Tidak ada pesanan di tab '${currentTab.title}'.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items, key = { it.id }) { orderItem ->
                OrderItemCard(
                    orderItem = orderItem,
                    currentTab = currentTab,
                    onActionClick = onActionClick
                )
            }
        }
    }
}

@Composable
fun OrderItemCard(
    orderItem: OrderItemModel,
    currentTab: OrderTab,
    onActionClick: (orderId: String, actionType: String) -> Unit
) {
    val actionButtonText = when (currentTab) {
        OrderTab.KERANJANG -> "Beli"
        OrderTab.PROSES -> "Lacak"
        OrderTab.SELESAI -> "Nilai"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = orderItem.storeIconResId),
                contentDescription = "Ikon ${orderItem.storeName}",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    orderItem.storeName,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    orderItem.productInfo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    orderItem.totalPrice,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { onActionClick(orderItem.id, actionButtonText) },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EA),
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(actionButtonText, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun CartStatusScreenPreview() {
    MaterialTheme {
        CartStatusScreen(navController = rememberNavController())
    }
}
