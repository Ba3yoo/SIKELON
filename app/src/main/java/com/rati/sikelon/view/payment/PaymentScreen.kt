package com.rati.sikelon.view.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.rati.sikelon.R
import com.rati.sikelon.model.Item
import com.rati.sikelon.navigate.DetailType
import com.rati.sikelon.navigate.NavItem

// Data class untuk produk yang akan ditampilkan
data class ProductItem(val name: String, val quantity: Int, val price: String, val imageId: Int)

// Contoh daftar produk
val productItems = listOf(
    ProductItem("Beng-Beng Maxx Cokelat 32 g", 5, "Rp24.500", R.drawable.beng_beng_max),
    ProductItem("Teh Pucuk Harum 350ml", 2, "Rp10.000", R.drawable.teh_pucuk_harum),
    ProductItem("Indomie Goreng Spesial", 3, "Rp9.000", R.drawable.indomie_goreng)
)

@Composable
fun PaymentScreen(
    item: Item,
    navController: NavController,
    onPaymentComplete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header Row dengan ikon back dan judul "Checkout"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Checkout",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Card untuk menampilkan detail alamat, pengiriman, dan pembayaran
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                // SectionRow: Bagian alamat
                SectionRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Alamat",
                            tint = Color(0xFF7E60BF),
                            modifier = Modifier.size(36.dp)
                        )
                    },
                    title = "Alamat",
                    actionText = "Ubah",
                    onActionClick = { navController.navigate("${NavItem.EditDetails.route}/${DetailType.ADDRESS}") }
                ) {
                    Text(text = "Rumah", style = sectionContentStyle())
                    Text(
                        text = "Jl. Kaliurang RT 1/RW 4, Kecamatan Lowokwaru",
                        style = sectionSubContentStyle()
                    )
                }

                Divider(modifier = Modifier.padding(horizontal = 16.dp))

                // SectionRow: Bagian pengiriman
                SectionRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Pengiriman",
                            tint = Color(0xFF7E60BF),
                            modifier = Modifier.size(36.dp)
                        )
                    },
                    title = "Pengiriman",
                    actionText = "Ubah",
                    onActionClick = { navController.navigate("${NavItem.EditDetails.route}/${DetailType.SHIPPING}") }
                ) {
                    Text(text = "Ojek Online", style = sectionContentStyle())
                    Text(text = "Estimasi datang pukul 20.00", style = sectionSubContentStyle())
                }

                Divider(modifier = Modifier.padding(horizontal = 16.dp))

                // SectionRow: Bagian pembayaran
                SectionRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Pembayaran",
                            tint = Color(0xFF7E60BF),
                            modifier = Modifier.size(36.dp)
                        )
                    },
                    title = "Pembayaran",
                    actionText = "Ubah",
                    onActionClick = { navController.navigate("${NavItem.EditDetails.route}/${DetailType.PAYMENT}") }
                ) {
                    Text(text = "COD (cash on delivery)", style = sectionContentStyle())
                    Text(text = "bayar sesuai total belanjaan Anda", style = sectionSubContentStyle())
                }
            }
        }


        // Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(listOf(item)) { singleItem ->
                    PaymentProductItem(singleItem)
                }
            }
        }

        // Tombol Bayar
        Button(
            onClick = { onPaymentComplete() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E60BF)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Bayar",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun PaymentProductItem(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.img_link,
            contentDescription = item.item_name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = item.item_name, style = sectionContentStyle())
            Text(text = "Jumlah: 1", style = sectionSubContentStyle())
            Text(text = "Rp${item.price}", style = sectionContentStyle())
        }
    }
}

fun sectionContentStyle() = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 14.sp,
    color = Color.Black
)

fun sectionSubContentStyle() = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 14.sp,
    color = Color.Gray
)

//@Preview(showBackground = true)
//@Composable
//fun PaymentScreenPreview() {
//    PaymentScreen(
//        item = ProductItem(
//            name = "he",
//            quantity = 10,
//            price = "he",
//            imageId = R.drawable.ic_launcher_foreground
//        ),
//        navController = rememberNavController(),
//        onPaymentComplete = {}
//    )
//}

// Composable SectionRow
@Composable
fun SectionRow(
    icon: @Composable () -> Unit,
    title: String,
    actionText: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        icon()

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                OutlinedButton(
                    onClick = onActionClick,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                    modifier = Modifier.height(32.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF7E60BF),
                    ),
                    border = BorderStroke(1.dp, Color(0xFF7E60BF)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = actionText, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Konten section yang bisa berisi detail alamat, pengiriman, atau pembayaran
            content()
        }
    }
}
