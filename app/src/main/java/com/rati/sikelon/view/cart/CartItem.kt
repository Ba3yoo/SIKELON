package com.rati.sikelon.view.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.rati.sikelon.data.UserRepository
import com.rati.sikelon.model.CartDetail
import com.rati.sikelon.viewmodel.UserViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CartItem(
    viewModel: UserViewModel = viewModel(),
    orderId: String
) {
    val selectedDetails by viewModel.selectedCartDetail.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadCartDetailById(orderId.toInt())
    }

    val detail = selectedDetails.firstOrNull()

    if (detail != null) {
        // Tampilkan detail pembayaran
        Text("Bayar untuk: ${detail.item_name} - Rp${detail.price * detail.quantity}")
    } else {
        Text("Data item tidak ditemukan")
    }

    Column(
        modifier = Modifier
            .padding(bottom = 51.dp, start = 2.dp, end = 2.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFDDE2E5),
                shape = RoundedCornerShape(15.dp)
            )
            .clip(shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 15.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .weight(1f)
            ) {
                CoilImage(
                    imageModel = { detail?.img_link },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(60.dp)
                        .height(60.dp)
                )
                Column {
                    detail?.let {
                        Text(
                            it.item_name,
                            color = Color(0xFF252525),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .width(113.dp)
                        )
                    }
                    Text(
                        "x" + detail?.quantity,
                        color = Color(0xFF252525),
                        fontSize = 10.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    detail?.let {
                        Text(
                            "Rp. " + (detail.price.times(it.quantity)),
                            color = Color(0xFF252525),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            CoilImage(
                imageModel = { "https://cdn.discordapp.com/attachments/1007173535749382167/1379324294999314442/image.png?ex=683fd35a&is=683e81da&hm=cc4550a4819af9dc27fa491d2b237ebebc017cba876497477f12ca23a5cca28a&" },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .width(77.dp)
                    .height(30.dp)
                    .clickable {
                        val request = UserRepository.StatusUpdate(detail?.cartDetail_id ?: 1, "paid")
                        viewModel.updStatus(request)
                    }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CartItemPreview() {
//    val dummyCartDetail = CartDetail(
//        cartDetail_id = 1,
//        cart_id = 1,
//        store_id = 101,
//        item_id = 1001,
//        quantity = 2,
//        store_name = "Toko Tani Sejahtera",
//        address = "Jl. Pertanian No. 5",
//        item_name = "Bibit Cabai Rawit",
//        price = 15000,
//        img_link = "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/image_dummy_bibit.png"
//    )
//
//    CartItem(detail = dummyCartDetail)
//}
