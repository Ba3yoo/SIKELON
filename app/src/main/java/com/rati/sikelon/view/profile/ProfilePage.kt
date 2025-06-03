package com.rati.sikelon.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.R
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.view.reusable.AppBottomNavigationBar
import com.rati.sikelon.view.reusable.EmptyPopOutDialog

@Composable
fun ProfilePage(navController: NavController) {
    val showLogOutDialog = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.avatar_image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // User Name
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "User",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Profile Options
            ProfileOptionItem(text = "Profil", onClick = { navController.navigate(NavItem.EditProfile.route) })
            ProfileOptionItem(text = "Ubah Password", onClick = { navController.navigate(NavItem.ProfileSettings.route) })
            ProfileOptionItem(text = "Metode Pembayaran", onClick = { navController.navigate(NavItem.ProfilePaymentMethod.route) })
            ProfileOptionItem(text = "Pusat Bantuan", onClick = { navController.navigate(NavItem.ProfileHelpDesk.route) })
            ProfileOptionItem(text = "Keluar", onClick = { showLogOutDialog.value = true })
        }
    }

    if (showLogOutDialog.value) {
        EmptyPopOutDialog(
            title = "Keluar",
            message = "Apakah Anda yakin ingin keluar dari akun Anda sekarang?",
            onDismiss = { showLogOutDialog.value = false },
            onConfirm = {
                showLogOutDialog.value = true
                navController.navigate(NavItem.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        )
    }
}

@Composable
fun ProfileOptionItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(32.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { onClick() },
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
            painter = painterResource(id = R.drawable.enter),
            contentDescription = "Right Arrow",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage(navController = rememberNavController())
}

