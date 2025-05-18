package com.rati.sikelon.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign


// Data class for Flash Sale items
data class FlashSaleItem(val imageId: Int, val price: String, val description: String)

// Data class for Quick Buy items
data class QuickBuyItem(val imageId: Int, val price: String, val description: String)

@Composable
fun HomePage() {
//    // Sample data for Flash Sale items
//    val flashSaleItems = listOf(
//        FlashSaleItem(R.drawable.prenagen_lacta_mom_180_gr, "Rp34.000", "Prenagen Lacta Mom 180 Gr"),
//        FlashSaleItem(R.drawable.shinzu_i_sabun_mandi_refill_725_ml, "Rp46.900", "Shinzu'i Sabun Mandi Refill 725 ml"),
//        FlashSaleItem(R.drawable.kopi_kapal_api_350_gr, "Rp52.217", "Kopi Kapal Api 350 Gr"),
//        // Add more items as needed
//    )
//
//    // Sample data for Quick Buy items
//    val quickBuyItems = listOf(
//        QuickBuyItem(R.drawable.le_minerale_air_mineral_botol_600_ml, "Rp3.700", "Le Minerale Air Mineral Botol 600 ml"),
//        QuickBuyItem(R.drawable.beng_beng_maxx_cokelat_32_g, "Rp4.900", "Beng-Beng Maxx Cokelat 32 g"),
//        QuickBuyItem(R.drawable.bear_brand_susu_steril_189_ml, "Rp10.800", "Bear Brand Susu Steril 189 ml"),
//        // Add more items as needed
//    )
//
//    // State for the collage images.  Using rememberSaveable for config changes.
//    val collageImages = remember {
//        listOf(
//            R.drawable.kopi_kapal_api_350_gr,
//            R.drawable.beng_beng_maxx_cokelat_32_g,
//            R.drawable.bear_brand_susu_steril_189_ml,
//            R.drawable.le_minerale_air_mineral_botol_600_ml,
//            R.drawable.shinzu_i_sabun_mandi_refill_725_ml,
//            R.drawable.prenagen_lacta_mom_180_gr,
//        )
//    }

    val scrollState = rememberScrollState()

    // Column to hold the entire home page layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Location and Search Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Location Text and Dropdown
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Lokasi",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
                //painterResource(id = R.drawable.ic_drop_down) // Use your dropdown icon here
            }
            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Adjust the width as needed
                    .height(40.dp)
                    .clip(RoundedCornerShape(20.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            "Cari",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize(),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.Black,
//                        unfocusedTextColor = Color.Black,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        placeholderColor = Color.Gray,
//                        containerColor = Color(0xFFF0F0F0), // Background color of search bar
//                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    shape = RoundedCornerShape(20.dp)
                )
            }
            // Filter Icon Button (Added as per the image)
            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_filter_list_24), //replace with your filter icon
//                    contentDescription = "Filter",
//                    tint = Color.Black
//                )
            }
        }

        // Special Offer Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF44336)), // Red color
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "SPECIAL OFFER",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = "SINGLE'S DAY\n11.11",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    ),
                    lineHeight = 24.sp
                )
                Button(
                    onClick = { /* Handle shop now click */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "SHOP NOW",
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 12.sp,
                            color = Color.Red
                        )
                    )
                }
            }
        }

//        // Flash Sale Section
//        FlashSaleSection(flashSaleItems = flashSaleItems)
//
//        // Collage Section
//        CollageSection(collageImages = collageImages)
//
//        // Quick Buy Section
//        QuickBuySection(quickBuyItems = quickBuyItems)
    }
}

/**
 * Displays the Flash Sale section with a title and a horizontal list of items.
 */
@Composable
fun FlashSaleSection(flashSaleItems: List<FlashSaleItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Section title and "Lihat Semua" (See All) button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Flash Sale",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "Lihat Semua",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color(0xFF9F2BFF) // Purple color
                ),
                modifier = Modifier.clickable { /* Handle lihat semua click */ }
            )
        }
        // Horizontal list of Flash Sale items
        LazyRow(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(flashSaleItems) { item ->
                FlashSaleItemCard(item = item)
            }
        }
    }
}

/**
 * Displays an individual Flash Sale item card.
 */
@Composable
fun FlashSaleItemCard(item: FlashSaleItem) {
    Card(
        modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Item Image
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = item.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),  // Fixed height for the image
                contentScale = ContentScale.Crop,
            )
            // Item Price
            Text(
                text = item.price,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            // Item Description
            Text(
                text = item.description,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            // "Beli Cepat" (Quick Buy) button
            Button(
                onClick = { /* Handle beli cepat click */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Beli Cepat",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}

/**
 * Displays a collage of images.
 */
@Composable
fun CollageSection(collageImages: List<Int>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Section title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "KOLASE",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
        }

        // Use a Box to position images absolutely within the collage container.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp) // Set a fixed height for the collage
                .padding(horizontal = 16.dp)
        ) {
            // Image positions are hardcoded for this specific layout.  For a more
            // general solution, you'd need a more complex layout algorithm.

            Image(
                painter = painterResource(id = collageImages[0]), // Top-left
                contentDescription = "Collage Image 1",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.TopStart),
                contentScale = ContentScale.Crop,
            )

            Image(
                painter = painterResource(id = collageImages[1]), // Top-right
                contentDescription = "Collage Image 2",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.TopEnd),
                contentScale = ContentScale.Crop,
            )

            Image(
                painter = painterResource(id = collageImages[2]), // Middle-left
                contentDescription = "Collage Image 3",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterStart),
                contentScale = ContentScale.Crop,
            )

            Image(
                painter = painterResource(id = collageImages[3]), // Middle-Right
                contentDescription = "Collage Image 4",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterEnd),
                contentScale = ContentScale.Crop,
            )

            Image(
                painter = painterResource(id = collageImages[4]), // Bottom-Left
                contentDescription = "Collage Image 5",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.BottomStart),
                contentScale = ContentScale.Crop,
            )

            Image(
                painter = painterResource(id = collageImages[5]), // Bottom-Right
                contentDescription = "Collage Image 6",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.BottomEnd),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

/**
 * Displays the Quick Buy section with a title and a horizontal list of items.
 */
@Composable
fun QuickBuySection(quickBuyItems: List<QuickBuyItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Section title and "Lihat Semua" (See All) button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Beli Cepat",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "Lihat Semua",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color(0xFF9F2BFF) // Purple color
                ),
                modifier = Modifier.clickable { /* Handle lihat semua click */ }
            )
        }
        // Horizontal list of Quick Buy items
        LazyRow(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(quickBuyItems) { item ->
                QuickBuyItemCard(item = item)
            }
        }
    }
}

/**
 * Displays an individual Quick Buy item card.
 */
@Composable
fun QuickBuyItemCard(item: QuickBuyItem) {
    Card(
        modifier = Modifier
            .width(150.dp) // Adjust as needed
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Item Image
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = item.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),  // Fixed height for the image
                contentScale = ContentScale.Crop,
            )
            // Item Price
            Text(
                text = item.price,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            // Item Description
            Text(
                text = item.description,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            // "Beli Cepat" (Quick Buy) button
            Button(
                onClick = { /* Handle beli cepat click */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Beli Cepat",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage()
}
