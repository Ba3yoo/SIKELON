package com.rati.sikelon.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavHost(
    navController: NavHostController,
//    userViewModel: UserViewModel,
    startDestination: String = NavItem.Home.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(NavItem.Home.route) {
//            HomeScreen(
//                onNavigateToSearch = { navController.navigate(NavItem.Searched.route) }
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

        composable(NavItem.Payment.route) {
//            PaymentScreen(
//                onPaymentComplete = { navController.navigate(NavItem.Status.route) }
//            )
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
    }
}
