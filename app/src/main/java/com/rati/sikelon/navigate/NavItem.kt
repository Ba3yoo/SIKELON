package com.rati.sikelon.navigate

enum class State {
    HOME,
    SEARCHED,
    STORE_DETAIL,
    PAYMENT,
    PAYMENT_STATUS,
    STATUS,
    TRACK_STATUS

}

sealed class NavItem(val route: String) {
    object Home : NavItem(State.HOME.name)
    object Searched : NavItem(State.SEARCHED.name)
    object Store_Detail : NavItem(State.STORE_DETAIL.name)
    object Payment : NavItem(State.PAYMENT.name)
    object Status : NavItem(State.STATUS.name)
    object TrackStatus : NavItem(State.TRACK_STATUS.name)
    object PaymentSuccess : NavItem(State.PAYMENT_STATUS.name)
}