package com.rati.sikelon.view.cart

import android.R.attr.padding
import android.R.attr.spacing
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import java.util.UUID

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
    LaunchedEffect(Unit) {
        CartItem.loadCartDetails()
        CartItem.loadPaidDetails()
    }

    // Ambil data dari StateFlow
    val cartDetails by CartItem.cartDetails.collectAsState()
    val paidDetails by CartItem.paidDetails.collectAsState()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = OrderTab.entries.toTypedArray()

    val processingOrderItems = remember {
        listOf(
            OrderItemModel("101", "Toko Maju Jaya", "https://c.alfagift.id/product/1/1_A7071790001084_20211123141700452_base.jpg", "Pesanan sedang dikemas", "Rp150.000")
        )
    }


    val completedOrderItems = remember {
        listOf(
            OrderItemModel("201", "Toko Berkah Selalu", "https://c.alfagift.id/product/1/1_A7071790001084_20211123141700452_base.jpg", "Pesanan telah diterima", "Rp75.000"),
            OrderItemModel("202", "Toko Kurnia", "https://c.alfagift.id/product/1/1_A7071790001084_20211123141700452_base.jpg", "Beng-Beng Maxx Cokelat 32 g...", "Rp162.000")
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
            val context = LocalContext.current
            OrderTabContent(
                selectedTab = tabs[selectedTabIndex],
                cartDetails = cartDetails,
                processingOrderItems = paidDetails,
                completedOrderItems = completedOrderItems,
                onActionClick = { orderId, actionType ->
                    try {
                        val route = when (actionType) {
                            "Beli" -> {
                                val itemId = orderId.toString()
                                if (true) {
                                    "CartItem/$itemId"
                                } else {
                                    Log.e("NavigationError", "ID tidak valid untuk pembayaran: $orderId")
                                    Toast.makeText(context, "ID item tidak valid", Toast.LENGTH_SHORT).show()
                                    return@OrderTabContent
                                }
                            }
                            "Lacak" -> "trackStatus/$orderId"
                            "Nilai" -> "review/$orderId"
                            else -> {
                                Log.e("NavigationError", "Aksi tidak dikenal: $actionType")
                                Toast.makeText(context, "Aksi tidak dikenali", Toast.LENGTH_SHORT).show()
                                return@OrderTabContent
                            }
                        }

                        navController.navigate(route)
                    } catch (e: Exception) {
                        Log.e("NavigationError", "Terjadi error saat navigasi ke $actionType dengan ID: $orderId", e)
                        Toast.makeText(context, "Terjadi error tidak terduga", Toast.LENGTH_SHORT).show()
                    }
                }
            )
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
            CoilImage(
                imageModel = { orderItem.storeIconResId },
//                contentDescription = "Ikon ${orderItem.storeName}",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                ),            )
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
    processingOrderItems: List<CartDetail>,
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
                    items(
                        items = cartDetails,
                        key = { it.cartDetail_id }
                    ) { detail ->
                        // Log detail item
                        Log.d("OrderTabContent", "Menampilkan cartDetail: id=${detail.cartDetail_id}, name=${detail.item_name}, qty=${detail.quantity}, price=${detail.price}")

                        val total = detail.price * detail.quantity
                        val orderItem = OrderItemModel(
                            id = detail.item_id.toString(),
                            storeIconResId = detail.img_link,
                            storeName = detail.store_name,
                            productInfo = "Jumlah: ${detail.quantity}",
                            totalPrice = "Rp${total}"
                        )
                        OrderItemCard(
                            orderItem = orderItem,
                            currentTab = OrderTab.KERANJANG,
                            onActionClick = onActionClick
                        )
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
                    items(processingOrderItems) {
                        detail ->
                        // Log detail item
                        Log.d("OrderTabContent", "Menampilkan cartDetail: id=${detail.cartDetail_id}, name=${detail.item_name}, qty=${detail.quantity}, price=${detail.price}")

                        val total = detail.price * detail.quantity
                        val orderItem = OrderItemModel(
                            id = detail.item_id.toString(),
                            storeIconResId = detail.img_link,
                            storeName = detail.store_name,
                            productInfo = "Jumlah: ${detail.quantity}",
                            totalPrice = "Rp${total}"
                        )
                        OrderItemCard(
                            orderItem = orderItem,
                            currentTab = OrderTab.KERANJANG,
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
