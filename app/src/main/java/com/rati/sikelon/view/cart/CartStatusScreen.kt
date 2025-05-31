package com.rati.sikelon.view.cart

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rati.sikelon.R
import com.rati.sikelon.model.CartDetail
import com.rati.sikelon.model.OrderItemModel
import com.rati.sikelon.view.reusable.AppBottomNavigationBar
import com.rati.sikelon.viewmodel.UserViewModel

enum class OrderTab(val title: String) {
    KERANJANG("Keranjang"),
    PROSES("Proses"),
    SELESAI("Selesai")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartStatusScreen(
    navController: NavController,
    CartItem: UserViewModel = viewModel(),
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
        },
        bottomBar = {
            AppBottomNavigationBar(navController = navController)
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

//            OrderTabContent(
//                selectedTab = tabs[selectedTabIndex],
//                cartDetails = cartDetails.value,
//                processingOrderItems = processingOrderItems, /*TODO add the VM*/
//                completedOrderItems = completedOrderItems,
//                onActionClick = { orderId, actionType ->
//                    navController.navigate(
//                        when (actionType) {
//                            "Beli" -> "checkout/$orderId" /*TODO add a UX floating cart tracker or add to cart itself*/
//                            "Lacak" -> "${NavItem.TrackStatus.route}/$orderId"
//                            "Nilai" -> "${NavItem.Review.route}/$orderId"
//                            else -> return@OrderTabContent
//                        }
//                    )
//                }
//            )
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
                onClick = {
                    onActionClick(orderItem.id, actionButtonText)
                },
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

@Composable
fun OrderTabContent(
    selectedTab: OrderTab,
    cartDetails: List<CartDetail>,
    processingOrderItems: List<OrderItemModel>,
    completedOrderItems: List<OrderItemModel>,
    onActionClick: (orderId: String, actionType: String) -> Unit
) {
    when (selectedTab) {
        OrderTab.KERANJANG -> {
            if (cartDetails.isEmpty()) {
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
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    cartDetails.forEach { detail ->
                        CartItem(detail)
                    }
                }
            }
        }

        OrderTab.PROSES -> {
            if (processingOrderItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tidak ada pesanan yang sedang diproses.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(processingOrderItems) { orderItem ->
                        OrderItemCard(
                            orderItem = orderItem,
                            currentTab = OrderTab.PROSES,
                            onActionClick = onActionClick
                        )
                    }
                }
            }
        }

        OrderTab.SELESAI -> {
            if (completedOrderItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Belum ada pesanan yang selesai.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(completedOrderItems) { orderItem ->
                        OrderItemCard(
                            orderItem = orderItem,
                            currentTab = OrderTab.SELESAI,
                            onActionClick = onActionClick
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
//@Composable
//fun CartStatusScreenPreview() {
//    MaterialTheme {
//        CartStatusScreen(navController = rememberNavController()
//    }
//}
