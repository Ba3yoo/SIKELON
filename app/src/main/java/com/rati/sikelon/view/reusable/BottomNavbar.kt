package com.rati.sikelon.view.reusable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rati.sikelon.navigate.NavItem
import com.rati.sikelon.navigate.BottomNavAware
import androidx.compose.ui.unit.dp

@Composable
fun AppBottomNavigationBar(
    navController: NavController,
    items: List<BottomNavAware> = NavItem.getBottomNavItems()
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (selected) screen.selectedIcon else screen.unselectedIcon),
                        contentDescription = screen.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(text = screen.label)
                },
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up ke start destination
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomNavigationBarPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        AppBottomNavigationBar(navController = navController)
    }
}
