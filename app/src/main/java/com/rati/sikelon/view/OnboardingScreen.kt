package com.rati.sikelon.view

import android.content.Context
import androidx.annotation.ColorInt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rati.sikelon.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.rati.sikelon.ui.theme.Purple500
import kotlinx.coroutines.launch
import com.rati.sikelon.ui.theme.White

private val Context.appDataStore by preferencesDataStore("sikelon_prefs_multistep")

enum class OnboardingStep {
    WELCOME,
    USER_TYPE_SELECTION
}

@Composable
fun OnboardingScreen() {
    var currentPage by remember { mutableIntStateOf(1) }

    when (currentPage) {
        1 -> OnboardingPage1(
            onNextClicked = { currentPage = 2 }
        )

        2 -> OnboardingPage2(
            onMasukClicked = { userType ->
                println("User selected type: $userType")
            },
            context = LocalContext.current
        )
    }
}

@Composable
fun OnboardingPage1(onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 32.dp, vertical = 48.dp)
            .clickable { onNextClicked() },
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            painter = painterResource(id = R.drawable.sikelon_logo),
            contentDescription = "Sikelon Logo",
            modifier = Modifier
                .size(300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(0.8f))
    }
}

@Composable
fun OnboardingPage2(
    onMasukClicked: (userType: String) -> Unit,
    context: Context
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var selectedUserType by remember { mutableStateOf("pembeli") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 32.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selamat Datang\nSobat Kelontong!",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            color = colorResource(id = R.color.purple_500),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.sikelon_logo),
            contentDescription = "Sikelon Logo with Application Name",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Ingin masuk sebagai apa?",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp
            ),
            color = Color(0xFF043860)
        )

        Spacer(modifier = Modifier.height(16.dp))

        UserTypeSwitchComponent(
            selectedType = selectedUserType,
            onTypeSelected = { newType -> selectedUserType = newType.lowercase() }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    SikelonOnboardingDataStore.saveUserType(context, selectedUserType)
                    SikelonOnboardingDataStore.markOnboardingCompleted(context)
                    onMasukClicked(selectedUserType)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple500,
                contentColor = White
            ),
        ) {
            Text("Masuk")
        }
    }
}

@Composable
fun UserTypeSwitchComponent(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    val buyerText = "Pembeli"
    val sellerText = "Penjual"

    val selectedButtonColor = Color.White
    val selectedContentColor = Color.Black
    val unselectedButtonColor = Color.Transparent
    val unselectedContentColor = Color.White
    val backgroundTrackColor = colorResource(id = R.color.purple_500)

    Row(
        modifier = Modifier
            .width(300.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(backgroundTrackColor)
            .padding(4.dp)
    ) {
        // Buyer Box (posisi kiri)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(25.dp))
                .background(
                    if (selectedType.equals(buyerText, ignoreCase = true))
                        selectedButtonColor else unselectedButtonColor
                )
                .clickable { onTypeSelected(buyerText) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buyerText,
                color = if (selectedType.equals(buyerText, ignoreCase = true))
                    selectedContentColor else unselectedContentColor,
                fontWeight = if (selectedType.equals(buyerText, ignoreCase = true))
                    FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Seller Box (posisi kanan)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(25.dp))
                .background(
                    if (selectedType.equals(sellerText, ignoreCase = true))
                        selectedButtonColor else unselectedButtonColor
                )
                .clickable { onTypeSelected(sellerText) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = sellerText,
                color = if (selectedType.equals(sellerText, ignoreCase = true))
                    selectedContentColor else unselectedContentColor,
                fontWeight = if (selectedType.equals(sellerText, ignoreCase = true))
                    FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }

}

object SikelonOnboardingDataStore {
    private val USER_TYPE_KEY = stringPreferencesKey("sikelon_user_type")
    private val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey("sikelon_onboarding_completed")

    fun getUserType(context: Context): Flow<String?> {
        return context.appDataStore.data.map { preferences ->
            preferences[USER_TYPE_KEY]
        }
    }

    suspend fun saveUserType(context: Context, userType: String) {
        context.appDataStore.edit { preferences ->
            preferences[USER_TYPE_KEY] = userType
        }
    }

    fun isOnboardingCompleted(context: Context): Flow<Boolean> {
        return context.appDataStore.data.map { preferences ->
            preferences[ONBOARDING_COMPLETED_KEY] ?: false
        }
    }

    suspend fun markOnboardingCompleted(context: Context) {
        context.appDataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED_KEY] = true
        }
    }

    suspend fun clearOnboardingData(context: Context) {
        context.appDataStore.edit { preferences ->
            preferences.remove(USER_TYPE_KEY)
            preferences.remove(ONBOARDING_COMPLETED_KEY)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    var currentPage by remember { mutableIntStateOf(1) }

    when (currentPage) {
        1 -> OnboardingPage1(onNextClicked = { currentPage = 2 })
        2 -> OnboardingPage2(
            onMasukClicked = { userType ->
                println("User selected type: $userType")
            },
            context = LocalContext.current
        )
    }
}

