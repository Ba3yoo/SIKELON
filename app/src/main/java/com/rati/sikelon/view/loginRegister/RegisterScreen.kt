package com.rati.sikelon.view.loginRegister

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.clickable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction


@Composable
fun RegisterScreen() {
    // State variables for input fields
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var agreeTerms by rememberSaveable { mutableStateOf(false) }

    // Column to hold the entire layout
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title of the screen
        Text(
            text = "Daftar",
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
            text = "Isi data diri Anda untuk membuat akun dan mulai menikmati semua fitur. Gunakan email aktif dan kata sandi yang aman.",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )

        // Name TextField
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama") },
            placeholder = { Text("masukkan nama Anda disini") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
//            colors = TextFieldDefaults.colors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color(0xFF9F2BFF),
//                unfocusedIndicatorColor = Color.Gray,
//                placeholderColor = Color.Gray,
//                containerColor = Color.White,
//            ),
            shape = RoundedCornerShape(32.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
        )

        // Email TextField
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("masukkan Email Anda disini") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            /* TODO: Handle dependencies usage */
//            colors = TextFieldDefaults.colors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color(0xFF9F2BFF),
//                unfocusedIndicatorColor = Color.Gray,
//                placeholderColor = Color.Gray,
//                containerColor = Color.White,
//            ),
            shape = RoundedCornerShape(32.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("masukkan password Anda disini") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(
//                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
//                        contentDescription = if (passwordVisible) "Show password" else "Hide password",
//                        tint = Color.Gray
//                    )
                }
            },
//            colors = TextFieldDefaults.colors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color(0xFF9F2BFF),
//                unfocusedIndicatorColor = Color.Gray,
//                placeholderColor = Color.Gray,
//                containerColor = Color.White,
//            ),
            shape = RoundedCornerShape(32.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        // Terms and Conditions Checkbox
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = agreeTerms,
                onCheckedChange = { agreeTerms = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF9F2BFF), // Purple color when checked
                    uncheckedColor = Color.Gray, // Gray color when unchecked
                ),
            )
            Text(
                text = "Agree with Term and Condition",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                modifier = Modifier.clickable { agreeTerms = !agreeTerms }
            )
        }

        // Sign Up Button
        Button(
            onClick = {
                // Handle sign up logic here
                println(
                    "Sign Up button clicked with name: $name, email: $email, password: $password, " +
                            "agreeTerms: $agreeTerms"
                )
                // You would typically validate the input fields and create a new account.
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9F2BFF) // Purple color for the button
            ),
            shape = RoundedCornerShape(32.dp),

            ) {
            Text(
                text = "Daftar",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }

        // Or sign up with divider
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = Color.Gray,
                modifier = Modifier.weight(1f),
                thickness = 1.dp
            )
            Text(
                text = "Or sign up with",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Divider(
                color = Color.Gray,
                modifier = Modifier.weight(1f),
                thickness = 1.dp
            )
        }

        // Social login icons
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // Apple Icon
            IconButton(onClick = { /* Handle Apple login */ }) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_apple), // Use the correct ID for Apple icon
//                    contentDescription = "Apple Login",
//                    modifier = Modifier.size(40.dp)
//                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Add space between icons

            // Google Icon
            IconButton(onClick = { /* Handle Google login */ }) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_google), // Use the correct ID for Google icon
//                    contentDescription = "Google Login",
//                    modifier = Modifier.size(40.dp)
//                )
            }

            Spacer(modifier = Modifier.width(16.dp)) // Add space between icons

            // Facebook Icon
            IconButton(onClick = { /* Handle Facebook login */ }) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_facebook), // Use the correct ID for Facebook icon
//                    contentDescription = "Facebook Login",
//                    modifier = Modifier.size(40.dp)
//                )
            }
        }

        // Already have an account? Sign In
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Already have an account? ",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "Sign Up", // Should be Sign In
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 14.sp,
                    color = Color(0xFF9F2BFF) // Purple color
                ),
                // Add a Clickable modifier here to navigate to the sign-in screen
                modifier = Modifier.clickable {
                    // TODO: Navigate to sign in
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
