package com.rati.sikelon.view.cart


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R

// Data contoh untuk produk yang diulas
val sampleProductsToReview = listOf(
    ProductToReview(
        id = "1",
        imageResId = R.drawable.sate,
        name = "Beng-Beng Maxx Cokelat 32 g",
        quantityInfo = "x5",
        price = "Rp24.500"
    ),
    ProductToReview(
        id = "2",
        imageResId = R.drawable.sate,
        name = "Beng-Beng Nuts Karamel Almond 35 g",
        quantityInfo = "x3",
        price = "Rp25.200"
    )
)

data class ProductToReview(
    val id: String,
    @DrawableRes val imageResId: Int,
    val name: String,
    val quantityInfo: String,
    val price: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewPage(
    navController: NavController,
    productsToReview: List<ProductToReview> = sampleProductsToReview // Bisa dari argumen navigasi/ViewModel
) {
    var productQualityRating by remember { mutableIntStateOf(0) }
    var sellerServiceRating by remember { mutableIntStateOf(0) }
    var shippingQualityRating by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Penilaian") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
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
                .verticalScroll(rememberScrollState()) // Agar bisa di-scroll jika konten panjang
                .padding(horizontal = 16.dp, vertical = 16.dp), // Padding keseluruhan untuk konten
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Jarak antar Card dan tombol
        ) {
            // Card untuk Rating Bintang
            RatingInputCard(
                productQualityRating = productQualityRating,
                onProductQualityChange = { productQualityRating = it },
                sellerServiceRating = sellerServiceRating,
                onSellerServiceChange = { sellerServiceRating = it },
                shippingQualityRating = shippingQualityRating,
                onShippingQualityChange = { shippingQualityRating = it }
            )

            // Card untuk Daftar Produk yang Diulas
            ReviewedProductsCard(products = productsToReview)

            // Tombol Kirim
            Button(
                onClick = {
                    // TODO: Logika untuk mengirim review
                    // Misalnya: viewModel.submitReview(
                    //     productQualityRating,
                    //     sellerServiceRating,
                    //     shippingQualityRating,
                    //     productsToReview.map { it.id }
                    // )
                    navController.popBackStack() // Kembali setelah submit (contoh)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp), // Sangat rounded seperti gambar
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7E57C2) // Warna ungu (sesuaikan dengan tema)
                )
            ) {
                Text(
                    "Kirim",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun RatingInputCard(
    productQualityRating: Int,
    onProductQualityChange: (Int) -> Unit,
    sellerServiceRating: Int,
    onSellerServiceChange: (Int) -> Unit,
    shippingQualityRating: Int,
    onShippingQualityChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RatingInputRow(
                label = "Kualitas Produk",
                currentRating = productQualityRating,
                onRatingChange = onProductQualityChange
            )
            RatingInputRow(
                label = "Pelayanan Penjual",
                currentRating = sellerServiceRating,
                onRatingChange = onSellerServiceChange
            )
            RatingInputRow(
                label = "Kualitas Pengiriman",
                currentRating = shippingQualityRating,
                onRatingChange = onShippingQualityChange
            )
        }
    }
}

@Composable
fun RatingInputRow(
    label: String,
    currentRating: Int,
    onRatingChange: (Int) -> Unit,
    maxRating: Int = 5
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        StarRatingBar(
            currentRating = currentRating,
            onRatingChange = onRatingChange,
            maxRating = maxRating
        )
    }
}

@Composable
fun StarRatingBar(
    currentRating: Int,
    onRatingChange: (Int) -> Unit,
    maxRating: Int = 5,
    starColor: Color = Color(0xFFFFC107), // Warna bintang terisi (kuning/emas)
    emptyStarColor: Color = MaterialTheme.colorScheme.outlineVariant // Warna bintang kosong (abu-abu)
) {
    Row {
        for (starIndex in 1..maxRating) {
            val isSelected = starIndex <= currentRating
            IconButton(
                onClick = { onRatingChange(starIndex) },
                modifier = Modifier.size(32.dp) // Ukuran ikon bintang
            ) {
                Icon(
                    imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.StarOutline,
                    contentDescription = "Bintang ke-$starIndex",
                    tint = if (isSelected) starColor else emptyStarColor,
                    modifier = Modifier.fillMaxSize() // Agar ikon mengisi IconButton
                )
            }
        }
    }
}

@Composable
fun ReviewedProductsCard(products: List<ProductToReview>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp) // Padding atas & bawah untuk card
        ) {
            products.forEachIndexed { index, product ->
                ReviewedProductItemRow(product = product)
                if (index < products.size - 1) {
                    // Divider tipis jika mau, atau biarkan tanpa divider
                    // HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                    Spacer(modifier = Modifier.height(8.dp)) // Jarak antar item produk
                }
            }
        }
    }
}

@Composable
fun ReviewedProductItemRow(product: ProductToReview) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp), // Padding untuk setiap item produk
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.quantityInfo,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
fun ReviewPagePreview() {
    MaterialTheme {
        val dummyProducts = listOf(
            ProductToReview(
                id = "1",
                imageResId = android.R.drawable.ic_menu_gallery, // Placeholder drawable bawaan Android
                name = "Produk A",
                quantityInfo = "x2",
                price = "Rp10.000"
            ),
            ProductToReview(
                id = "2",
                imageResId = android.R.drawable.ic_menu_gallery,
                name = "Produk B",
                quantityInfo = "x3",
                price = "Rp15.000"
            )
        )
        ReviewPage(navController = rememberNavController(), productsToReview = dummyProducts)
    }
}
