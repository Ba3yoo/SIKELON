package com.rati.sikelon.view.reusable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.rati.sikelon.R
import com.rati.sikelon.model.Item
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

// Data class to hold card data
/*TODO add StoreID, image change from int to Link handler*/
//data class CardData(
//    val imageId: Int,
//    val price: String,
//    val description: String,
//    val buttonText: String,
//    val iconId: Int? = R.drawable.ic_launcher_foreground // Added iconId, default is null
//)

/**
 * A reusable card composable for displaying an image, text, and a button.
 */
@Composable
fun Card(
//    cardData: com.rati.sikelon.view.CardData,
    cardData: Item,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(162.dp)
            .height(200.dp)
            .padding(end = 12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                CoilImage(
                    imageModel = { cardData.img_link },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(24.dp),
                            strokeWidth = 2.dp
                        )
                    },
                    failure = {
                        Image(
                            painter = painterResource(id = R.drawable.sate),
                            contentDescription = "Failed to load image",
                            modifier = Modifier.size(84.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                )
            }
            Text(
                text = "Rp. " + cardData.price,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = cardData.item_name,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReusableCardPreview() {
    val sampleData = Item(
        item_id = 1,
        item_name = "beng beng maxx",
        price = 4900,
        store_id = 1,
        img_link = "https://c.alfagift.id/product/1/1_A7071790001084_20211123141700452_base.jpg"
    )
}
