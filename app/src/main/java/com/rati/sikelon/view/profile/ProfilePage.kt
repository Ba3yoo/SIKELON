package com.rati.sikelon.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rati.sikelon.R

@Composable
fun ProfilePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.avatar_image), // Replace with your actual profile image resource
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Name
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "User", // Replace with actual user name
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )
            )
            // You can add an edit icon here if needed
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Profile Options
        ProfileOptionItem(text = "Profil")
        ProfileOptionItem(text = "Ubah Password")
        ProfileOptionItem(text = "Metode Pembayaran")
        ProfileOptionItem(text = "Pusat Bantuan")
        ProfileOptionItem(text = "Keluar")

        Spacer(modifier = Modifier.weight(1f)) // Pushes bottom navigation to the bottom

        // Bottom Navigation (Simplified for demonstration)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Height of bottom navigation
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // In a real app, you would use a BottomNavigation composable
            // and BottomNavigationItem for each item.  For this example,
            // we'll just use simple Text elements.
            Text("Home")
            Text("Cart")
            Text("Chat")
            Text("Profile")
        }
    }
}

@Composable
fun ProfileOptionItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(32.dp)) // Tambahkan shadow di sini
            .clip(RoundedCornerShape(12.dp))         // Setelah shadow
            .background(Color.White)            // Warna latar
            .padding(horizontal = 16.dp, vertical = 12.dp), // Padding dalam
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 18.sp,
                color = Color.Black
            )
        )
        Image(
            painter = painterResource(id = R.drawable.enter), // Ganti dengan icon arrow kanan kamu
            contentDescription = "Right Arrow",
            modifier = Modifier.size(24.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}

