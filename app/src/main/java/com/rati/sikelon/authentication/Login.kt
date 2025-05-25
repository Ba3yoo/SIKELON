package com.rati.sikelon.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import com.rati.sikelon.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.navigate.NavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 128.dp, start = 16.dp, end = 16.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign In",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Selamat datang kembali!"
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Email",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Masukkan Email Anda di sini") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF7E60BF),
                unfocusedBorderColor = Color(0xFFBDBDBD)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Password",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Masukkan Password Anda di sini") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    val iconRes = if (passwordVisible) R.drawable.visible else R.drawable.hide
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = if (passwordVisible) "Sembunyikan Password" else "Tampilkan password",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF7E60BF),
                unfocusedBorderColor = Color(0xFFBDBDBD)
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Lupa password?",
                color = Color(0XFF7E60BF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {}
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(NavItem.Home.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Masuk")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}