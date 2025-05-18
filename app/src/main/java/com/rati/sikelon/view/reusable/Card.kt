package com.rati.sikelon.view.reusable

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.rati.sikelon.R

// Data class to hold card data
/*TODO add StoreID, image change from int to Link handler*/
data class CardData(
    val imageId: Int,
    val price: String,
    val description: String,
    val buttonText: String,
    val iconId: Int? = R.drawable.ic_launcher_foreground // Added iconId, default is null
)

/**
 * A reusable card composable for displaying an image, text, and a button.
 */
@Composable
fun Card(cardData: CardData,
         onButtonClick: () -> Unit
         ) {
    Card(
        modifier = Modifier
            .width(162.dp) // Adjust as needed
            .height(200.dp)
            .padding(end = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Use a Box to layer the image and icon
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.TopCenter // Align the icon to the top center
            ) {
                // Item Image
                Image(
                    painter = painterResource(id = cardData.imageId),
                    contentDescription = cardData.description,
                    modifier = Modifier
                        .fillMaxSize(), // Image fills the entire Box
                    contentScale = ContentScale.FillBounds,
                )
                // Icon on top of the image, if provided
                cardData.iconId?.let {
                    Image(
                        painter = painterResource(id = it), // Use the iconId from CardData
                        contentDescription = "Icon", // Provide a content description
                        modifier = Modifier
                            .size(32.dp) // Adjust the size as needed
                            .align(Alignment.BottomEnd), // Position the icon
                    )
                }
            }
            // Item Price
            Text(
                text = cardData.price,
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
                text = cardData.description,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReusableCardPreview() {
    val sampleData = CardData(
        imageId = R.drawable.sate,
        price = "Rp4.900",
        description = "Beng-Beng Maxx Cokelat 32 g",
        buttonText = "Beli Cepat"
    )
    Card(cardData = sampleData, onButtonClick = { /* Handle beli cepat click */ })
}
