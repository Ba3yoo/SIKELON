package com.rati.sikelon.view.cart

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.layout.*
import com.rati.sikelon.viewmodel.UserViewModel
import com.skydoves.landscapist.*
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Cart(viewModel: UserViewModel) {
    val cartDetails = viewModel.selectedCartDetail.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadCartDetailById(1)
    }
    Log.d("det", cartDetails.value.toString())
    val textField1 = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xFFFFFFFF),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color(0xFFFFFFFF),
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 75.dp,bottom = 51.dp,start = 43.dp,end = 43.dp,)
                    .fillMaxWidth()
                    .padding(end = 92.dp,)
            ){
                CoilImage(
                    imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/9tt7652q_expires_30_days.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(25.dp)
                        .height(26.dp)
                )
                Text("Keranjang Belanja",
                    color = Color(0xFF252525),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 51.dp,start = 35.dp,end = 35.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDDE2E5),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .padding(vertical = 15.dp,)
            ){
                items(cartDetails.value){
                    cartDetail -> CartItem(cartDetail)
                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .padding(bottom = 15.dp,start = 15.dp,end = 15.dp,)
//                        .fillMaxWidth()
//                ){
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .padding(end = 12.dp,)
//                            .weight(1f)
//                    ){
//                        CoilImage(
//                            imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/1pnkw3o8_expires_30_days.png"},
//                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
//                            modifier = Modifier
//                                .padding(end = 8.dp,)
//                                .width(60.dp)
//                                .height(60.dp)
//                        )
//                        Column(
//                        ){
//                            Text("Beng-Beng Maxx  Cokelat 32 g",
//                                color = Color(0xFF252525),
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier
//                                    .padding(bottom = 8.dp,)
//                                    .width(113.dp)
//                            )
//                            Text("x 5",
//                                color = Color(0xFF252525),
//                                fontSize = 10.sp,
//                                modifier = Modifier
//                                    .padding(bottom = 8.dp,)
//                            )
//                            Text("Rp24.500",
//                                color = Color(0xFF252525),
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Bold,
//                            )
//                        }
//                    }
//
//                    CoilImage(
//                        imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/9j3fkp2h_expires_30_days.png"},
//                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
//                        modifier = Modifier
//                            .width(77.dp)
//                            .height(30.dp)
//                    )
//                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .padding(horizontal = 15.dp,)
//                        .fillMaxWidth()
//                ){
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier
//                            .padding(end = 12.dp,)
//                            .weight(1f)
//                    ){
//                        CoilImage(
//                            imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/tmwwt1d9_expires_30_days.png"},
//                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
//                            modifier = Modifier
//                                .padding(end = 8.dp,)
//                                .width(60.dp)
//                                .height(60.dp)
//                        )
//                        Column(
//                        ){
//                            Text("Beng-Beng Nuts Karamel Almond 35 g ",
//                                color = Color(0xFF252525),
//                                fontSize = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier
//                                    .padding(bottom = 8.dp,)
//                                    .width(108.dp)
//                            )
//                            Text("x 3",
//                                color = Color(0xFF252525),
//                                fontSize = 10.sp,
//                                modifier = Modifier
//                                    .padding(bottom = 8.dp,)
//                            )
//                            Text("Rp25.200",
//                                color = Color(0xFF252525),
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Bold,
//                            )
//                        }
//                    }
//                    CoilImage(
//                        imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/vnzt4qlu_expires_30_days.png"},
//                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
//                        modifier = Modifier
//                            .width(77.dp)
//                            .height(30.dp)
//                    )
//                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 51.dp,start = 35.dp,end = 35.dp,)
                    .clip(shape = RoundedCornerShape(25.dp))
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFF8F9FA),
                        shape = RoundedCornerShape(25.dp)
                    )
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                    )
                    .padding(vertical = 10.dp,horizontal = 15.dp,)
            ){
                TextField(
                    value = textField1.value,
                    onValueChange = { newText -> textField1.value = newText },
                    placeholder = { Text("Kode Promo") },
                    textStyle = TextStyle(
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 13.dp,)
                )

                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(25.dp))
                        .background(
                            color = Color(0xFF7E60BF),
                            shape = RoundedCornerShape(25.dp)
                        )
                        .padding(vertical = 12.dp,horizontal = 25.dp,)
                ){
                    Text("Pakai",
                        color = Color(0xFFF8F9FA),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 51.dp,start = 35.dp,end = 35.dp,)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDDE2E5),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .clip(shape = RoundedCornerShape(15.dp))
                    .fillMaxWidth()
                    .padding(vertical = 15.dp,)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 20.dp,start = 15.dp,end = 15.dp,)
                        .fillMaxWidth()
                ){
                    Text("Total",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 4.dp,)
                            .weight(1f)
                    )
                    Text("49.700",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 20.dp,start = 15.dp,end = 15.dp,)
                        .fillMaxWidth()
                ){
                    Text("Biaya Pengiriman",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 4.dp,)
                            .weight(1f)
                    )
                    Text("3000",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 20.dp,start = 15.dp,end = 15.dp,)
                        .fillMaxWidth()
                ){
                    Text("Diskon",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 4.dp,)
                            .weight(1f)
                    )
                    Text("700",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                CoilImage(
                    imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/veo3n1a5_expires_30_days.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(bottom = 21.dp,start = 15.dp,)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .width(295.dp)
                        .height(2.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 15.dp,)
                        .fillMaxWidth()
                ){
                    Text("Total Harga",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(end = 4.dp,)
                            .weight(1f)
                    )
                    Text("52.000",
                        color = Color(0xFF252525),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            OutlinedButton(
                onClick = { println("Pressed!") },
                border = BorderStroke(0.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .padding(bottom = 54.dp,start = 35.dp,end = 35.dp,)
                    .clip(shape = RoundedCornerShape(25.dp))
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF7E60BF),
                        shape = RoundedCornerShape(25.dp)
                    )
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 16.dp,)
                ){
                    Text("Lanjut ke Pembayaran",
                        color = Color(0xFFF8F9FA),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 11.dp,)
                    .height(1.dp)
                    .fillMaxWidth()
            ){
                CoilImage(
                    imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/4qxgp2d6_expires_30_days.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(145.dp)
                        .height(1.dp)
                )
            }
        }
    }
}