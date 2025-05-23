package com.rati.sikelon.navigate

import androidx.annotation.DrawableRes
import com.rati.sikelon.R

enum class State {
    HOME,
    SEARCHED,
    STORE_DETAIL,
    PAYMENT,
    PAYMENT_STATUS,
    STATUS,
    TRACK_STATUS,
    CART,
    CHAT,
    PROFILE

}

sealed class NavItem(val route: String, @DrawableRes val iconRes: Int, val label: String) {
    object Home : NavItem(State.HOME.name, R.drawable.home_sikelon, "Home")
    object Searched : NavItem(State.SEARCHED.name, R.drawable.search_sikelon, "Search")
    object Store_Detail : NavItem(State.STORE_DETAIL.name, R.drawable.sate, "Store Detail")
    object Payment : NavItem(State.PAYMENT.name, R.drawable.sate, "Payment")
    object Status : NavItem(State.STATUS.name, R.drawable.sate, "Status")
    object TrackStatus : NavItem(State.TRACK_STATUS.name, R.drawable.sate, "Track Status")
    object PaymentSuccess : NavItem(State.PAYMENT_STATUS.name, R.drawable.sate, "Payment Success")
    object Cart : NavItem(State.CART.name, R.drawable.cart_sikelon, "Cart")
    object Chat : NavItem(State.CHAT.name, R.drawable.chat_sikelon, "Chat")
    object Profile: NavItem(State.PROFILE.name, R.drawable.profile_sikelon, "Profile")
}