package com.rati.sikelon.view.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.rati.sikelon.model.CartDetail
import com.rati.sikelon.viewmodel.UserViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CartItem(detail: CartDetail) {
    Column(
        modifier = Modifier
            .padding(bottom = 51.dp, start = 35.dp, end = 35.dp)
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
                    imageModel = { detail.img_link },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(60.dp)
                        .height(60.dp)
                )
                Column {
                    Text(
                        detail.item_name,
                        color = Color(0xFF252525),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .width(113.dp)
                    )
                    Text(
                        "x" + detail.quantity,
                        color = Color(0xFF252525),
                        fontSize = 10.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        "Rp. " + (detail.price * detail.quantity),
                        color = Color(0xFF252525),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            CoilImage(
                imageModel = { "https://storage.googleapis.com/tagjs-prod.appspot.com/v1/cgc6UUl9Ff/9j3fkp2h_expires_30_days.png" },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .width(77.dp)
                    .height(30.dp)
            )
        }
    }
}
