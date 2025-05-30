package com.rati.sikelon.view.reusable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rati.sikelon.R

// Data class to hold card data
/*TODO add StoreID, image change from int to Link handler*/
data class CardData(
    val imageUrl: String,
    val price: String,
    val description: String,
    val buttonText: String,
    val iconUrl: String? = null
)



/**
 * A reusable card composable for displaying an image, text, and a button.
 */
@Composable
fun Card(
    cardData: CardData,
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
                AsyncImage(
                    model = cardData.imageUrl,
                    contentDescription = cardData.description,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                cardData.iconUrl?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    )
                }
            }

            Text(
                text = cardData.price,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = cardData.description,
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
    val sampleData = CardData(
        imageUrl = "https://via.placeholder.com/150",
        price = "Rp4.900",
        description = "Beng-Beng Maxx Cokelat 32 g",
        buttonText = "Beli Cepat",
        iconUrl = "https://via.placeholder.com/32"
    )

    Card(cardData = sampleData)
}
