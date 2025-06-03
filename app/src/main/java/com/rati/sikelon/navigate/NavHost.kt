package com.rati.sikelon.navigate

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.mvvm2.view.VerifyCodeScreen
import com.rati.sikelon.model.Item
import com.rati.sikelon.model.User
import com.rati.sikelon.view.*
import com.rati.sikelon.view.cart.*
import com.rati.sikelon.view.loginRegister.ForgetPasswordPage
import com.rati.sikelon.view.loginRegister.LoginScreen
import com.rati.sikelon.view.loginRegister.LoginScreenPenjual
import com.rati.sikelon.view.loginRegister.RegisterScreen
import com.rati.sikelon.view.message.MessageDetailScreen
import com.rati.sikelon.view.message.MessageScreen
import com.rati.sikelon.view.payment.*
import com.rati.sikelon.view.profile.ProfilePage
import com.rati.sikelon.view.search.SearchPage
import com.rati.sikelon.viewmodel.BuyerAuthViewModel
import com.rati.sikelon.viewmodel.SellerAuthViewModel
import com.rati.sikelon.viewmodel.UserViewModel
import kotlinx.coroutines.launch

enum class DetailType {
    ADDRESS, SHIPPING, PAYMENT
}

@SuppressLint("ViewModelConstructorInComposable", "ComposableDestinationInComposeScope")
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var startDestination by remember { mutableStateOf("onboarding1") }
    val buyerViewModel = BuyerAuthViewModel()
    val sellerViewModel = SellerAuthViewModel()
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
                        Log.d(
                            "NAV_DEBUG",
                            "Masuk clicked with userType: $userRole navigating to login/$userRole"
                        )
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
            when (userRole) {
                "pembeli" -> LoginScreen(
                    navController = navController,
                    viewModel = buyerViewModel
                )

                "penjual" -> LoginScreenPenjual(
                    navController = navController,
                    viewModel = sellerViewModel
                )

                else -> {
                    // Bisa juga tampilkan screen error
                    OnboardingPage1(
                        onNextClicked = {
                            navController.navigate("onboarding2")
                        }
                    )
                }
            }
        }

            // REGISTER
            composable(NavItem.Register.route) {
                RegisterScreen(
                    navController = navController,
                    viewModel = buyerViewModel
                )
            }
            composable(NavItem.VerifyCode.route) {
                VerifyCodeScreen(navController = navController)
            }
            composable(NavItem.NewPassword.route) {
                ForgetPasswordPage(navController = navController)
            }


            // ===== HOME & DASHBOARD =====
            composable(NavItem.Home.route) {
                HomePage(navController = navController, viewModel = userViewModel)
            }
            composable(NavItem.Dashboard.route) {
//                val sellerObject = navController.previousBackStackEntry?.savedStateHandle?.get<User>("seller") // new
//                Log.d("seller", sellerObject?.email ?: "none")
//                if (sellerObject != null) {
                    DashboardScreen(navController = navController, viewModel = userViewModel)
//                }
            }

            // ===== CART =====
            composable(NavItem.CartDetail.route) {
                CartStatusScreen(navController = navController)
            }
            composable(
                "${NavItem.Review.route}/{orderId}",
                arguments = listOf(navArgument("orderId") { type = NavType.StringType })
            ) { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                ReviewPage(navController = navController, orderId = orderId)
            }
            composable(
                "${NavItem.TrackStatus.route}/{orderId}",
                arguments = listOf(navArgument("orderId") { type = NavType.StringType })
            ) { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                TrackStatus(orderId = orderId)
            }

            // SEARCH
            composable(NavItem.Search.route) {
                SearchPage(navController = navController, initialQuery = "")
            }

            // ===== MESSAGE =====
            composable(NavItem.MessageList.route) {
                MessageScreen(navController = navController)
            }
            composable(NavItem.MessageDetail.route) {
                MessageDetailScreen(
                    navController = navController,
                    storeInfo = TODO(),
                    initialMessages = TODO()
                )
            }

            // PAYMENT
            composable(
                "${NavItem.Payment.route}/{name}/{quantity}/{price}",
                arguments = listOf(
//                navArgument("name") { type = NavType.StringType },
//                navArgument("quantity") { type = NavType.IntType },
//                navArgument("price") { type = NavType.StringType },
//                navArgument("imageId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("item_id") ?: 0
                val name = backStackEntry.arguments?.getString("item_name") ?: ""
                val quantity = backStackEntry.arguments?.getInt("quantity") ?: 0
                val price = backStackEntry.arguments?.getInt("price") ?: 0
                val store_id = backStackEntry.arguments?.getInt("store_id") ?: 0
                val image = backStackEntry.arguments?.getString("img_link") ?: ""
                val product = Item(
                    item_id = id,
                    item_name = name,
                    price = price,
                    store_id = store_id,
                    img_link = image
                )

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

            composable(NavItem.TrendSale.route){
                TrendDetailScreen(
                    navController = navController
                )
            }
        }
    }
