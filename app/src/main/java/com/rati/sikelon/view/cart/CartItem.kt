package com.rati.sikelon.view.cart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rati.sikelon.model.CartDetail
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CartItem(detail: CartDetail) {
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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 15.dp,start = 15.dp,end = 15.dp,)
                .fillMaxWidth()
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(end = 12.dp,)
                    .weight(1f)
            ){
                CoilImage(
                    imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/1pnkw3o8_expires_30_days.png"},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 8.dp,)
                        .width(60.dp)
                        .height(60.dp)
                )
                Column(
                ){
                    Text("Beng-Beng Maxx  Cokelat 32 g",
                        color = Color(0xFF252525),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 8.dp,)
                            .width(113.dp)
                    )
                    Text("x" + detail.quantity,
                        color = Color(0xFF252525),
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp,)
                    )
                    Text("Rp. 22,000",
                        color = Color(0xFF252525),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            CoilImage(
                imageModel = {"https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/9j3fkp2h_expires_30_days.png"},
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .width(77.dp)
                    .height(30.dp)
            )
        }
    }
}