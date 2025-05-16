package com.rati.sikelon.navigate

enum class State {
    HOME,
    SEARCHED,
    STORE_DETAIL,
    PAYMENT,
    STATUS

}

sealed class NavItem(val route: String) {
    object Home : NavItem(State.HOME.name)
    object Searched : NavItem(State.SEARCHED.name)
    object Store_Detail : NavItem(State.STORE_DETAIL.name)
    object Payment : NavItem(State.PAYMENT.name)
    object Status : NavItem(State.STATUS.name)
}