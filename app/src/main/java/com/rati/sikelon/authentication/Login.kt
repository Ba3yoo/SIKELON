package com.rati.sikelon.authentication

import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
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
import com.skydoves.landscapist.*
import com.skydoves.landscapist.coil.CoilImage

//import com.skydoves.landscapist.coil3.*

@Composable
fun Login() {
    val textField1 = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xFFFFFFFF),
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color(0xFFFFFFFF),
                )
                .padding(horizontal = 35.dp,)
                .verticalScroll(rememberScrollState())
        ){
            Column(
                modifier = Modifier
                    .padding(top = 127.dp,bottom = 69.dp,)
                    .fillMaxWidth()
            ){
                Text("Sign In",
                    color = Color(0xFF252525),
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 10.dp,)
                )
                Text("Selamat datang kembali!",
                    color = Color(0xFF495057),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 69.dp,)
                    .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .padding(bottom = 33.dp,)
                        .fillMaxWidth()
                ){
                    Text("Email",
                        color = Color(0xFF7E60BF),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 4.dp,)
                    )
                    TextField(
                        value = textField1.value,
                        onValueChange = { newText -> textField1.value = newText },
                        placeholder = {
                            Text("masukkan Email Anda disini", color = Color.Gray, fontSize = 12.sp)
                        },
                        textStyle = TextStyle(
                            color = Color(0xFFE4B1F0),
                            fontSize = 12.sp,
                        ),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFF7E60BF),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .clip(shape = RoundedCornerShape(25.dp))
                            .fillMaxWidth()
                            .padding(top = 18.dp,bottom = 18.dp,start = 27.dp,end = 54.dp,)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 33.dp,)
                        .fillMaxWidth()
                ){
                    Text("Password",
                        color = Color(0xFF7E60BF),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 4.dp,)
                    )
                    Column(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFF7E60BF),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .clip(shape = RoundedCornerShape(25.dp))
                            .fillMaxWidth()
                            .padding(top = 13.dp,)
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(bottom = 17.dp,start = 27.dp,end = 27.dp,)
                                .fillMaxWidth()
                        ){
                            Text("masukkan password Anda disini",
                                color = Color(0xFFE4B1F0),
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            CoilImage(
                                imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/uqbu95u5_expires_30_days.png"},
                                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                modifier = Modifier
                                    .width(26.dp)
                                    .height(24.dp)
                            )
                        }
                        Text("lupa password?",
                            color = Color(0xFF433878),
                            fontSize = 10.sp,
                        )
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                        }
                    }
                }
                OutlinedButton(
                    onClick = { println("Pressed!") },
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
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
                        Text("Masuk",
                            color = Color(0xFFF8F9FA),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 116.dp,)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 47.dp,)
                ){
                    Column(
                        modifier = Modifier
                            .padding(end = 10.dp,)
                    ){
                        Text("Atau masuk dengan",
                            color = Color(0xFF777777),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 91.dp,)
                        )
                        CoilImage(
                            imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/he4do7qo_expires_30_days.png"},
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .width(95.dp)
                                .height(1.dp)
                        )
                    }
                    CoilImage(
                        imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/2t427eu7_expires_30_days.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(84.dp)
                            .height(1.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(bottom = 47.dp,start = 25.dp,end = 25.dp,)
                        .height(70.dp)
                ){
                    CoilImage(
                        imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/o3q96698_expires_30_days.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                    )
                    Column(
                        modifier = Modifier
                            .width(70.dp)
                    ){
                    }
                    CoilImage(
                        imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/xotxt83y_expires_30_days.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .padding(end = 24.dp,)
                            .width(70.dp)
                            .height(70.dp)
                    )
                    CoilImage(
                        imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/yx56ki5a_expires_30_days.png"},
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                    )
                }
                Text("Belum punya akun? Sign Up",
                    color = Color(0xFF000000),
                    fontSize = 14.sp,
                )
            }
            CoilImage(
                imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/ygej3gx6_expires_30_days.png"},
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(bottom = 10.dp,)
                    .width(145.dp)
                    .height(1.dp)
            )
        }
    }
}