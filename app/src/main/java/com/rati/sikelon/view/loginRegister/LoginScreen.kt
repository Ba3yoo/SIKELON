import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun LoginScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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
//            colors = TextFieldDefaults.colors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color(0xFF9F2BFF),
//                unfocusedIndicatorColor = Color.Gray,
//                placeholderColor = Color.Gray,
//                containerColor = Color.White,
//            ),
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
//                    Icon(
//                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
//                        contentDescription = null
//                    )
                }
            },/* TODO: Handle dependencies usage */
//            colors = TextFieldDefaults.colors(
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedIndicatorColor = Color(0xFF9F2BFF),
//                unfocusedIndicatorColor = Color.Gray,
//                placeholderColor = Color.Gray,
//                containerColor = Color.White,
//            ),
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
            onClick = { /* handle login */ },
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
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
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
            Text("Sign Up", fontSize = 14.sp, color = Color(0xFF9F2BFF))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen()
    }
}
