package com.rati.sikelon.navigate

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rati.sikelon.view.cart.Cart
import com.rati.sikelon.viewmodel.UserViewModel
import com.rati.sikelon.view.HomePage
import com.rati.sikelon.view.cart.TrackStatus
import com.rati.sikelon.view.payment.EditDetailsScreen
import com.rati.sikelon.view.payment.PaymentScreen
import com.rati.sikelon.view.payment.PaymentSuccessScreen
import com.rati.sikelon.view.payment.ProductItem

// Enum to define different detail types for navigation
// This should ideally be in its own file (e.g., DetailType.kt in a common package)
enum class DetailType {
    ADDRESS, SHIPPING, PAYMENT
}

@Composable fun HomeScreen() { Text("Home Screen") }
@Composable fun CartScreen() { Text("Cart Screen") }
@Composable fun ChatScreen() { Text("Chat Screen") }
@Composable fun ProfileScreen() { Text("Profile Screen") }


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(
//    userViewModel: UserViewModel,
    startDestination: String = NavItem.Home.route
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        composable(NavItem.Home.route) {
//            Cart(viewModel = UserViewModel())
//            HomePage(
//                navController = navController
//            )

        }

        composable(NavItem.Searched.route) {
//            SearchScreen(
//                onStoreClick = { storeId ->
//                    navController.navigate("${NavItem.Store_Detail.route}/$storeId")
//                }
//            )
        }

        composable(
            "${NavItem.Store_Detail.route}/{storeId}",
            arguments = listOf(navArgument("storeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val storeId = backStackEntry.arguments?.getInt("storeId") ?: 0
//            StoreDetailScreen(
//                storeId = storeId,
//                onPayClick = { navController.navigate(NavItem.Payment.route) }
//            )
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
            TrackStatus(

            )
        }

        composable(
            route = NavItem.EditDetails.route,
            arguments = listOf(navArgument("detailType") { type = NavType.StringType })
        ) { backStackEntry ->
            // Extract the 'detailType' string from the navigation arguments
            val detailTypeString = backStackEntry.arguments?.getString("detailType")
            // Convert the string to the DetailType enum
            val detailType = detailTypeString?.let { DetailType.valueOf(it) }

            if (detailType != null) {
                // If detailType is valid, call EditDetailsScreen and pass the navController and detailType
                EditDetailsScreen(navController = navController, detailType = detailType)
            } else {
                // Null for now
                Text("Error: Detail type not found or invalid.", modifier = Modifier.padding(16.dp))
            }
        }

        composable(NavItem.Status.route) {
//            StatusScreen(
//                onBackToHome = {
//                    navController.navigate(NavItem.Home.route) {
//                        popUpTo(NavItem.Home.route) { inclusive = true }
//                    }
//                }
//            )
        }

        composable(NavItem.MainHome.route) {
            HomeScreen()
        }
        composable(NavItem.MainCart.route) {
            CartScreen()
        }
        composable(NavItem.MainChat.route) {
            ChatScreen()
        }
        composable(NavItem.MainProfile.route) {
            ProfileScreen()
        }
//        composable(NavItem.Login.route) {
//            LoginScreen(navController = navController)
//        }
    }
}
