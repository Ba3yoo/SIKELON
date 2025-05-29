package com.rati.sikelon.navigate

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.rati.sikelon.data.AuthRepository
import com.rati.sikelon.view.*
import com.rati.sikelon.view.cart.CartStatusScreen
import com.rati.sikelon.view.cart.TrackStatus
import com.rati.sikelon.view.loginRegister.LoginScreen
import com.rati.sikelon.view.loginRegister.RegisterScreen
import com.rati.sikelon.view.message.MessageScreen
import com.rati.sikelon.view.payment.PaymentScreen
import com.rati.sikelon.view.payment.PaymentSuccessScreen
import com.rati.sikelon.view.payment.ProductItem
import com.rati.sikelon.view.profile.ProfilePage
import com.rati.sikelon.view.payment.*
import com.rati.sikelon.view.profile.ProfilePage
import com.rati.sikelon.view.search.SearchPage
import com.rati.sikelon.viewmodel.BuyerAuthViewModel
import com.rati.sikelon.viewmodel.SellerAuthViewModel
import com.rati.sikelon.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import kotlin.math.log

enum class DetailType {
    ADDRESS, SHIPPING, PAYMENT
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var startDestination by remember { mutableStateOf("onboarding1") }
    val buyerViewModel = BuyerAuthViewModel()
    val userViewModel = UserViewModel()

    // Tentukan start destination sekali saat komposisi pertama
    LaunchedEffect(Unit) {
        val isOnboardingShown = OnboardingPreferences.isOnboardingShown(context)
        val isLoggedIn = LoginPreferences.isLoggedIn(context)

        Log.d("NAV_DEBUG", "Onboarding shown: $isOnboardingShown, Logged in: $isLoggedIn")

        startDestination = when {
            !isOnboardingShown -> "onboarding1"
            !isLoggedIn -> NavItem.Login.route
            else -> NavItem.Home.route
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
            val scope = rememberCoroutineScope()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            Log.d("NAV_DEBUG", "Current page: $currentRoute")

            OnboardingPage2(
                context = context,
                onMasukClicked = { userRole ->
                    scope.launch {
                        Log.d("NAV_DEBUG", "Masuk clicked with userType: $userRole navigating to login/$userRole")
                        OnboardingPreferences.setOnboardingShown(context)
                        navController.navigate("login/$userRole") {
                            popUpTo("onboarding2") { inclusive = true }
                        }
                    }
                }
            )
        }

        // LOGIN
        composable(
            route = "login/{userRole}",
            arguments = listOf(navArgument("userRole") { type = NavType.StringType })
        ) { backStackEntry ->
            val userRole = backStackEntry.arguments?.getString("userRole") ?: "pembeli"
            val viewModelStoreOwner = LocalViewModelStoreOwner.current!!
            val application = LocalContext.current.applicationContext as Application

            Log.d("LoginNav", "Navigated to login with userRole: $userRole")

            // Contoh membuat ViewModel sesuai role (jika sudah punya repository):
            // val viewModel = if (userRole == "pembeli") {
            //     Log.d("LoginNav", "Creating BuyerAuthViewModel")
            //     ViewModelProvider(viewModelStoreOwner, BuyerAuthViewModelFactory(repository, application))
            //         .get(BuyerAuthViewModel::class.java)
            // } else {
            //     Log.d("LoginNav", "Creating SellerAuthViewModel")
            //     ViewModelProvider(viewModelStoreOwner, SellerAuthViewModelFactory(repository, application))
            //         .get(SellerAuthViewModel::class.java)
            // }

            LoginScreen(
                navController = navController,
                viewModel = buyerViewModel
            )
        }

        // REGISTER
        composable(NavItem.Register.route) {
            RegisterScreen(
                navController = navController,
                viewModel = buyerViewModel
            )
        }

        // HOME
        composable(NavItem.Home.route) {
            HomePage(navController = navController, viewModel = userViewModel)
        }

        // SEARCH
        composable(NavItem.Search.route) {
            SearchPage(navController = navController, initialQuery = "")
        }

        // PAYMENT
        composable(
            "${NavItem.Payment.route}/{name}/{quantity}/{price}/{imageId}",
            arguments = listOf(
//                navArgument("name") { type = NavType.StringType },
//                navArgument("quantity") { type = NavType.IntType },
//                navArgument("price") { type = NavType.StringType },
//                navArgument("imageId") { type = NavType.IntType }
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

        // BOTTOM NAVIGATION
        composable(NavItem.MainHome.route) {
            HomePage(navController = navController, viewModel = userViewModel)
        }

        composable(NavItem.MainCart.route) {
            CartStatusScreen(navController = navController)
        }

        composable(NavItem.MainChat.route) {
            MessageScreen(navController = navController)
        }

        composable(NavItem.MainProfile.route) {
            ProfilePage(navController = navController)
        }
    }
}