package com.rati.sikelon.view.loginRegister

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight


@Composable
fun ForgetPasswordPage() {
    // State variables to hold the password.
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }


    // Column to hold all the UI elements vertically.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Back button (Top-left)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: Handle back button click */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        // Title of the screen
        Text(
            text = "Password Baru",
            style = TextStyle(
                fontFamily = FontFamily.Default, // Anda dapat menggunakan font kustom di sini
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black // Set warna teks menjadi hitam
            ),
            modifier = Modifier.padding(bottom = 8.dp) // Memberikan padding bawah
        )

        // Teks "Buat Password baru Anda disini !"
        Text(
            text = "Buat Password baru Anda disini !",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 14.sp,
                color = Color.Gray // Set warna teks menjadi abu-abu
            ),
            modifier = Modifier.padding(bottom = 32.dp) // Memberikan padding bawah
        )

        // TextField untuk Password
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("masukkan password Anda disini") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(32.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), // Transformasi visual untuk menyembunyikan/menampilkan password
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(
//                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, // Ikon mata
//                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
//                        tint = Color.Gray // Set warna ikon
//                    )
                }
            },
        )

        // TextField untuk Confirm Password
        TextField(
            value = confirmPassword, // Nilai TextField
            onValueChange = { confirmPassword = it }, // Fungsi yang dipanggil saat nilai berubah
            label = { Text("Confirm Password") }, // Teks label
            placeholder = { Text("masukkan password Anda disini") }, // Teks placeholder
            modifier = Modifier
                .fillMaxWidth() // Mengisi lebar maksimum
                .padding(bottom = 16.dp), // Memberikan padding bawah
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.White,
//                focusedContainerColor = Color.White,
//                focusedIndicatorColor = Color(0xFF9F2BFF), // Set warna indikator saat fokus
//                unfocusedIndicatorColor = Color.Gray, // Set warna indikator saat tidak fokus
//                textColor = Color.Black,
//                placeholderColor = Color.Gray,
//                labelColor = Color.Black
//            ),
            shape = RoundedCornerShape(32.dp), // Bentuk sudut TextField
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(), // Transformasi visual untuk menyembunyikan/menampilkan password
            trailingIcon = {
                // Tombol untuk menampilkan/menyembunyikan password
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
//                    Icon(
//                        imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, // Ikon mata
//                        contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password",
//                        tint = Color.Gray // Set warna ikon
//                    )
                }
            },
        )


        // Tombol "Kirim Ulang"
        Button(
            onClick = {
                // Logika untuk mengirim ulang password di sini
                println("Tombol Kirim Ulang ditekan dengan password: $password dan konfirmasi password: $confirmPassword")
                // Anda biasanya akan memvalidasi password dan konfirmasi password di sini
                // dan mengirim permintaan untuk mengubah password.
            },
            modifier = Modifier
                .fillMaxWidth() // Mengisi lebar maksimum
                .padding(top = 32.dp), // Memberikan padding atas
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9F2BFF) // Set ke warna ungu
            ),
            shape = RoundedCornerShape(32.dp) // Bentuk sudut tombol

        ) {
            Text(
                "Kirim Ulang",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }
    }
}

// Pratinjau Composable
@Preview(showBackground = true)
@Composable
fun BuatPasswordBaruScreenPreview() {
    ForgetPasswordPage()
}
