package com.rati.sikelon.view.loginRegister

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rati.sikelon.navigate.LoginPreferences
import com.rati.sikelon.viewmodel.BuyerAuthViewModel
import com.rati.sikelon.viewmodel.SellerAuthViewModel
import com.rati.sikelon.data.AuthState
import com.rati.sikelon.model.requestResponse.LoginRequest
import com.rati.sikelon.navigate.NavItem
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class UserRole(val displayName: String) {
    BUYER("Buyer"),
    SELLER("Seller"),
    DRIVER("Driver")
}

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: BuyerAuthViewModel
) {
    val context = LocalContext.current
    val authState = viewModel.authState.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign In",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Selamat datang kembali!",
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("masukkan Email Anda disini") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(32.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("masukkan password Anda disini") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            shape = RoundedCornerShape(32.dp)
        )

        Text(
            text = "lupa password?",
            fontSize = 12.sp,
            fontFamily = FontFamily.Default,
            color = Color(0xFF9F2BFF),
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val request = LoginRequest(email, password)
                viewModel.loginBuyer(request)
                Log.d("state", (authState is AuthState.Success).toString())

                coroutineScope.launch {
                    delay(300)
                    when (val state = viewModel.authState.value) {
                        is AuthState.Success -> {
                            LoginPreferences.setLoggedIn(context, true)
                            Toast.makeText(context, "Login berhasil!", Toast.LENGTH_SHORT).show()
                            navController.navigate(NavItem.Home.route) {
                                popUpTo("login/pembeli") { inclusive = true }
                            }
                        }

                        is AuthState.Error -> {
                            Toast.makeText(context, "Login gagal: ${state.error}", Toast.LENGTH_LONG).show()
                        }

                        else -> {
                            Log.d("LoginState", "Belum selesai login.")
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9F2BFF)),
            shape = RoundedCornerShape(32.dp)
        ) {
            Text("Masuk", color = Color.White, fontSize = 18.sp, fontFamily = FontFamily.Default)
        }

        Text(
            text = "Atau masuk dengan",
            fontSize = 14.sp,
            fontFamily = FontFamily.Default,
            color = Color.Gray,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            horizontalArrangement = Arrangement.Center
//        )
//        {
//            listOf(
//                R.drawable.ic_apple to "Apple",
//                R.drawable.ic_google to "Google",
//                R.drawable.ic_facebook to "Facebook"
//            ).forEach { (iconRes, desc) ->
//                IconButton(onClick = { }) {
//                    Image(
//                        painter = painterResource(id = iconRes),
//                        contentDescription = desc,
//                        modifier = Modifier.size(40.dp)
//                    )
//                }
//                Spacer(modifier = Modifier.width(16.dp))
//            }
//        }

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text("Belum punya akun? ", fontSize = 14.sp, color = Color.Black)
            Text(
                text = "Sign Up",
                fontSize = 14.sp,
                color = Color(0xFF9F2BFF),
                modifier = Modifier.clickable {
//                    onSignUpClicked()
                }
            )
        }
    }
}
