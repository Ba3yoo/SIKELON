package com.rati.sikelon.view.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R

@Composable
fun PaymentSuccessScreen(
    navController: NavController,
    onPaymentComplete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Centered Image (Success Icon)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Pembayaran Berhasil",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Success Title
        Text(
            text = "Pembayaran Berhasil!",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF008000)
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Success Message
        Text(
            text = "Terima kasih telah berbelanja di toko kami!",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 16.sp,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Button to View Order
        Button(
            onClick = { onPaymentComplete() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Lihat Order Barang Belanjaan",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to View Receipt
        Button(
            onClick = { /* Handle lihat struk click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Lihat Struk Belanja",
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

@Preview(showBackground = true)
@Composable
fun PaymentSuccessScreenPreview() {
    PaymentSuccessScreen(
        navController = rememberNavController(),
        onPaymentComplete = {}
    )
}
