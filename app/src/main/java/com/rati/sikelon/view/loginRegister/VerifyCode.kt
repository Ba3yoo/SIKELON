package com.example.mvvm2.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun VerifyCodeScreen(navController: NavHostController) {
    // State for the OTP code.  Using rememberSaveable to survive config changes.
    var otpCode by rememberSaveable { mutableStateOf("") }
    // State for managing focus.
    val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
    // State to track if the resend code can be clicked
    var isResendEnabled by remember { mutableStateOf(false) }
    // State to hold the countdown time
    var resendSeconds by remember { mutableIntStateOf(60) }

    // Effect to start the countdown when the screen loads or resend is clicked
    LaunchedEffect(isResendEnabled) {
        if (!isResendEnabled) {
            resendSeconds = 60
            while (resendSeconds > 0) {
                kotlinx.coroutines.delay(1000)
                resendSeconds--
            }
            isResendEnabled = true
        }
    }

    // Column to hold the entire layout
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
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        // Title of the screen
        Text(
            text = "Verifikasi Kode",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Description text
        Text(
            text = "Kami telah mengirimkan kode ke email Anda.\nMasukkan kode tersebut untuk verifikasi.",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // OTP Code Input Fields
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            for (i in 0 until 4) {
                OutlinedTextField( // Changed to OutlinedTextField
                    value = otpCode.getOrNull(i)?.toString() ?: "",
                    onValueChange = {
                        if (it.length <= 1) {
                            val newOtpCode = otpCode.toMutableList()
                            if (i < otpCode.length) {
                                newOtpCode[i] = it.firstOrNull() ?: ' '
                            } else {
                                newOtpCode.add(it.firstOrNull() ?: ' ')
                            }
                            otpCode = newOtpCode.joinToString("").trim()
                            if (otpCode.length < 4) {
                                //Request focus to the next box
                            }
                        }
                    },
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (i < 3) ImeAction.Next else ImeAction.Done // change action
                    ),
                    /* TODO: Handle dependencies usage */
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.Black,
//                        unfocusedTextColor = Color.Black,
//                        focusedIndicatorColor = Color(0xFF9F2BFF),
//                        unfocusedIndicatorColor = Color.Gray,
//                        containerColor = Color.White,
//                    ),
                    shape = RoundedCornerShape(8.dp),
                )
            }
        }

        // Resend Code Text
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Didn't receive OTP? ",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
            )
            Text(
                text = if (isResendEnabled) "Resend Code" else "Resend Code in ${resendSeconds}s",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = if (isResendEnabled) Color(0xFF9F2BFF) else Color.Gray // Purple when enabled
                ),
                modifier = if (isResendEnabled) Modifier.clickable {
                    if (isResendEnabled) {
                        isResendEnabled = false
                    }
                } else Modifier,
            )
        }

        // Submit Button
        Button(
            onClick = {
                // Handle OTP verification logic here
                println("Verifying OTP: $otpCode")
                // You would typically validate the OTP code and proceed to the next screen.
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9F2BFF) // Purple color
            ),

            shape = RoundedCornerShape(32.dp),
            enabled = otpCode.length == 4, // Button is enabled only when OTP is 4 digits

        ) {
            Text(
                text = "Kirim Ulang",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyCodeScreenPreview() {
    VerifyCodeScreen(navController = rememberNavController())
}

