package com.rati.sikelon.navigate

import com.rati.sikelon.view.loginRegister.LoginScreen
import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rati.sikelon.view.OnboardingPage1
import com.rati.sikelon.view.OnboardingPage2
import com.rati.sikelon.view.HomePage
import com.rati.sikelon.view.cart.TrackStatus
import com.rati.sikelon.view.payment.PaymentScreen
import com.rati.sikelon.view.payment.PaymentSuccessScreen
import com.rati.sikelon.view.payment.ProductItem
import com.rati.sikelon.view.search.SearchPage
import kotlinx.coroutines.launch

enum class DetailType {
    ADDRESS, SHIPPING, PAYMENT
}

@Composable fun HomeScreen() { Text("Home Screen") }
@Composable fun CartScreen() { Text("Cart Screen") }
@Composable fun ChatScreen() { Text("Chat Screen") }
@Composable fun ProfileScreen() { Text("Profile Screen") }
@Composable fun SearchScreen() { Text("Search Screen") }

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var startDestination by remember { mutableStateOf("onboarding1") }
    val scope = rememberCoroutineScope()

    // Dijalankan saat pertama kali
    LaunchedEffect(Unit) {
        val isOnboardingShown = OnboardingPreferences.isOnboardingShown(context)
        val isLoggedIn = LoginPreferences.isLoggedIn(context)

        startDestination = when {
            !isOnboardingShown -> "onboarding1"
            !isLoggedIn -> NavItem.Login.route
            else -> NavItem.MainHome.route
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        // ONBOARDING
        composable("onboarding1") {
            OnboardingPage1(
                onNextClicked = {
                    navController.navigate("onboarding2")
                }
            )
        }

        composable("onboarding2") {
            OnboardingPage2(
                context = context,
                onMasukClicked = { userType ->
                    println("User selected type: $userType")
                    scope.launch {
                        OnboardingPreferences.setOnboardingShown(context)
                    }

                    navController.navigate(NavItem.Login.route) {
                        popUpTo("onboarding1") { inclusive = true }
                    }
                }
            )
        }

        // LOGIN
        composable(NavItem.Login.route) {
            val context = LocalContext.current

            LoginScreen(
                onLoginSuccess = {
                    LoginPreferences.setLoggedIn(context, true)
                    navController.navigate(NavItem.Home.route) {
                        popUpTo(NavItem.Login.route) { inclusive = true }
                    }
                },
                onSignUpClicked = {
                    navController.navigate(NavItem.Register.route)
                }
            )
        }

        composable(NavItem.Home.route) {
            HomePage(navController = navController)
        }

        composable(NavItem.Search.route) {
            SearchPage(
                navController = navController,
                initialQuery = ""
            )
        }

        composable(
            "${NavItem.Payment.route}/{name}/{quantity}/{price}/{imageId}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("quantity") { type = NavType.IntType },
                navArgument("price") { type = NavType.StringType },
                navArgument("imageId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val quantity = backStackEntry.arguments?.getInt("quantity") ?: 0
            val price = backStackEntry.arguments?.getString("price") ?: ""
            val imageId = backStackEntry.arguments?.getInt("imageId") ?: 0

            val product = ProductItem(name, quantity, price, imageId)

            PaymentScreen(
                item = product,
                navController = navController,
                onPaymentComplete = {
                    navController.navigate(NavItem.PaymentSuccess.route)
                }
            )
        }

        composable(NavItem.PaymentSuccess.route) {
            PaymentSuccessScreen(
                navController = navController,
                onPaymentComplete = {
                    navController.navigate(NavItem.TrackStatus.route)
                }
            )
        }

        composable(NavItem.TrackStatus.route) {
            TrackStatus()
        }

        // Bottom Nav Pages
        composable(NavItem.MainHome.route) { HomeScreen() }
        composable(NavItem.MainCart.route) { CartScreen() }
        composable(NavItem.MainChat.route) { ChatScreen() }
        composable(NavItem.MainProfile.route) { ProfileScreen() }
    }
}
