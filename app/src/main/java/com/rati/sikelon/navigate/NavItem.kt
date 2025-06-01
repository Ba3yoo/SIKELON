package com.rati.sikelon.navigate

//import androidx.compose.ui.graphics.vector.ImageVector
import com.rati.sikelon.R

enum class State {
    ONBOARDING,
    HOMEPAGE,

    // Login/Register
    LOGIN,
    BUYERREGISTER,
    SELLERREGISTER,
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
    EDIT_PROFILE,
    PROFILE_HELPDESK,
    PROFILE_PAYMENT_METHOD,
    PROFILE_SETTINGS,

    // Search
    SEARCH,
    SEARCH_RESULT,

    // Main Tabs
    MAIN_HOME,
    DETAILED_PROMO,
    MAIN_CART,
    MAIN_CHAT,
    MAIN_PROFILE,
    DASHBOARD,

    //Penjual
    TREND_SALE

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
    object BuyerRegister : NavItem(State.BUYERREGISTER.name)
    object SellerRegister : NavItem(State.SELLERREGISTER.name)
    object VerifyCode : NavItem(State.VERIFY_CODE.name)
    object NewPassword : NavItem(State.NEW_PASSWORD.name)

    // Cart
    object CartDetail : NavItem(State.CART_DETAIL.name)
    object CartItem : NavItem(State.CART_ITEM.name){fun withArgs(orderId: String): String = "$route/$orderId"}
    object Review : NavItem(State.REVIEW.name){fun withArgs(orderId: String): String = "$route/$orderId"}
    object TrackStatus : NavItem(State.TRACK_STATUS.name){fun withArgs(orderId: String): String = "$route/$orderId"}

    // Message
    object MessageList : NavItem(State.MESSAGE_LIST.name)
    object MessageDetail : NavItem(State.MESSAGE_DETAIL.name)

    // Payment
    object Payment : NavItem(State.PAYMENT.name)
    object EditDetails : NavItem(State.EDIT_DETAILS_PAYMENT.name)
    object PaymentSuccess : NavItem(State.PAYMENT_STATUS.name)

    // Search
    object Search : NavItem(State.SEARCH.name)
    object SearchResult : NavItem(State.SEARCH_RESULT.name){fun withArgs(query: String): String = "$route/$query"}

    // Profile
    object EditProfile : NavItem(State.EDIT_PROFILE.name)
    object ProfileHelpDesk : NavItem(State.PROFILE_HELPDESK.name)
    object ProfilePaymentMethod : NavItem(State.PROFILE_PAYMENT_METHOD.name)
    object ProfileSettings : NavItem(State.PROFILE_SETTINGS.name)

    // Main Tabs (Bottom Navigation)
    object MainHome : NavItem(State.MAIN_HOME.name), BottomNavAware {
        override val selectedIcon: Int = R.drawable.selected_home
        override val unselectedIcon: Int = R.drawable.home_unselected
        override val label: String = "Home"
    }

    object MainCart : NavItem(State.MAIN_CART.name), BottomNavAware {
        override val selectedIcon: Int = R.drawable.selected_cart
        override val unselectedIcon: Int = R.drawable.cart_unselected
        override val label: String = "Cart"
    }

    object MainChat : NavItem(State.MAIN_CHAT.name), BottomNavAware {
        override val selectedIcon: Int = R.drawable.selected_chat
        override val unselectedIcon: Int = R.drawable.chat_unselected
        override val label: String = "Chat"
    }

    object MainProfile : NavItem(State.MAIN_PROFILE.name), BottomNavAware {
        override val selectedIcon: Int = R.drawable.selected_profile
        override val unselectedIcon: Int = R.drawable.profile_unselected
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

    object  DetailedPromo : NavItem(State.DETAILED_PROMO.name)

    //Penjual side
    object TrendSale : NavItem(State.TREND_SALE.name)
}

interface BottomNavAware {
    val route: String
    val selectedIcon: Int
    val unselectedIcon: Int
    val label: String
}


