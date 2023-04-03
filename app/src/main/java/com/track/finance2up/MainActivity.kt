package com.track.finance2up

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.finance2up.authentication.presentation.ui.login.LoginScreen
import com.finance2up.authentication.presentation.ui.otp.OTPScreen
import com.finance2up.authentication.presentation.ui.otp.PreOTPScreen
import com.track.finance2up.ui.theme.Finance2UpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Finance2UpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MainScreen()
//                    LoginScreen()
                    NavHost(navController = navController, startDestination = "PreOTPScreen") {
                        composable(route = "PreOTPScreen") {
                            PreOTPScreen(navController = navController)
                        }
                        composable(route = "OTPScreen") {
                            OTPScreen(navController = navController)
                        }
                        composable("LoginScreen") {
                            LoginScreen()
                        }
                    }
                }
            }
        }
    }
}
