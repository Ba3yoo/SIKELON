package com.rati.sikelon.navigate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class State {
    ONBOARDING,
    HOMEPAGE,

    // Login/Register
    LOGIN,
    REGISTER,
    VERIFY_CODE,
    NEW_PASSWORD,

    // Cart
    CART_DETAIL,
    CART_ITEM,
    CART_STATUS,
    REVIEW,
    TRACK_STATUS,

    // Message
    MESSAGE_LIST,
    MESSAGE_DETAIL,

    // Payment
    PAYMENT,
    EDIT_DETAILS_PAYMENT,
    PAYMENT_STATUS,

    // Profile
    PROFILE,

    // Search
    SEARCH,
    SEARCH_RESULT,

    // Main Tabs
    MAIN_HOME,
    MAIN_CART,
    MAIN_CHAT,
    MAIN_PROFILE,
    DASHBOARD,

}

sealed class NavItem(val route: String) {

    // General Navigation
    object Onboarding : NavItem(State.ONBOARDING.name)
    object Home : NavItem(State.HOMEPAGE.name)
    object Dashboard : NavItem(State.DASHBOARD.name)

    // Login/Register
    object Login : NavItem("${State.LOGIN.name}/{userRole}") {
        fun createRoute(userRole: String) = "${State.LOGIN.name}/$userRole"
    }
    object Register : NavItem(State.REGISTER.name)
    object VerifyCode : NavItem(State.VERIFY_CODE.name)
    object NewPassword : NavItem(State.NEW_PASSWORD.name)

    // Cart
    object CartDetail : NavItem(State.CART_DETAIL.name)
    object CartItem : NavItem(State.CART_ITEM.name)
    object CartStatus : NavItem(State.CART_STATUS.name)
    object Review : NavItem(State.REVIEW.name)
    object TrackStatus : NavItem(State.TRACK_STATUS.name)

    // Message
    object MessageList : NavItem(State.MESSAGE_LIST.name)
    object MessageDetail : NavItem(State.MESSAGE_DETAIL.name)

    // Payment
    object Payment : NavItem(State.PAYMENT.name)
    object EditDetails : NavItem(State.EDIT_DETAILS_PAYMENT.name)
    object PaymentSuccess : NavItem(State.PAYMENT_STATUS.name)

    // Profile
    object Profile : NavItem(State.PROFILE.name)

    // Search
    object Search : NavItem(State.SEARCH.name)
    object SearchResult : NavItem(State.SEARCH_RESULT.name)

    // Main Tabs (Bottom Navigation)
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

interface BottomNavAware {
    val route: String
    val icon: ImageVector
    val label: String
}
