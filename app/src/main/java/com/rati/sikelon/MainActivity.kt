package com.example.sikelon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.sikelon.navigate.AppNavHost
import com.example.sikelon.ui.theme.SIKELONTheme
import com.example.sikelon.viewmodel.ViewModel

class MainActivity : ComponentActivity() {
    private val userViewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SIKELONTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val users by userViewModel.users.collectAsState()

                    LaunchedEffect(Unit) {
                        userViewModel.loadUsers()
                    }

//                    AppNavHost(
//                        navController = navController,
//                        userViewModel = userViewModel
//                    )
                }
            }
        }
    }
}
