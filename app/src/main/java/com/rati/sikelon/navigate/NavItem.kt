package com.rati.sikelon.navigate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class State {
    HOME,
    SEARCHED,
    STORE_DETAIL,
    PAYMENT,
    EDIT_DETAILS_PAYMENT,
    PAYMENT_STATUS,
    STATUS,
    TRACK_STATUS,
    MAIN_HOME,       // Rute untuk tab Home
    MAIN_CART,       // Rute untuk tab Cart
    MAIN_CHAT,       // Rute untuk tab Chat
    MAIN_PROFILE    // Rute untuk tab Profile
}

interface BottomNavAware {
    val route: String
    val icon: ImageVector
    val label: String
}

sealed class NavItem(val route: String) {
    object Home : NavItem(State.HOME.name)
    object Searched : NavItem(State.SEARCHED.name)
    object Store_Detail : NavItem(State.STORE_DETAIL.name)
    object Payment : NavItem(State.PAYMENT.name)
    object Status : NavItem(State.STATUS.name)
    object TrackStatus : NavItem(State.TRACK_STATUS.name)
    object PaymentSuccess : NavItem(State.PAYMENT_STATUS.name)
    object EditDetails : NavItem(State.EDIT_DETAILS_PAYMENT.name)
    object MainHome : NavItem(State.MAIN_HOME.name), BottomNavAware {
        override val icon: ImageVector = Icons.Filled.Home
        override val label: String = "Home"
    }

    object MainCart : NavItem(State.MAIN_CART.name), BottomNavAware {
        override val icon: ImageVector = Icons.Filled.ShoppingCart
        override val label: String = "Cart"
    }

    object MainChat : NavItem(State.MAIN_CHAT.name), BottomNavAware {
        override val icon: ImageVector = Icons.Filled.Email
        override val label: String = "Chat"
    }

    object MainProfile : NavItem(State.MAIN_PROFILE.name), BottomNavAware {
        override val icon: ImageVector = Icons.Filled.Person
        override val label: String = "Profile"
    }

    companion object {
        fun getBottomNavItems(): List<BottomNavAware> {
            return listOf(
                MainHome,
                MainCart,
                MainChat,
                MainProfile
            )
        }
    }
}