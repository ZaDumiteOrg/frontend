package com.example.zadumite_frontend.navigation
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zadumite_frontend.ui.signup.SignUpScreen
import com.example.zadumite_frontend.StartScreen
import com.example.zadumite_frontend.session.SessionViewModel
import com.example.zadumite_frontend.ui.login.LogInScreen
import com.example.zadumite_frontend.ui.scaffold.ZaDumiteScaffold
import com.example.zadumite_frontend.ui.user_words.UserWordsScreen
import com.example.zadumite_frontend.ui.word.WordOfTheWeekScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationStack(sessionViewModel: SessionViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val userIdState = sessionViewModel.userId.observeAsState()
    val userId = userIdState.value

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(route = Screen.Start.route) {
            StartScreen(
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) },
                onNavigateToLogIn = { navController.navigate(Screen.LogIn.route) }
            )
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToWordScreen = { navController.navigate(Screen.Word.route) }
            )
        }
        composable(route = Screen.LogIn.route) {
            LogInScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToWordScreen = { navController.navigate(Screen.Word.route) }
            )
        }
        composable(route = Screen.Word.route) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute in listOf(Screen.Word.route, Screen.UserWords.route)) {
                ZaDumiteScaffold(
                    currentRoute = currentRoute,
                    onNavigateToWords = {
                        if (userId != null) {
                            navController.navigate(Screen.UserWords.route.replace("{userId}", userId.toString())) {
                                popUpTo(Screen.Word.route) { inclusive = false }
                            }
                        } else {
                            Log.e("NavigationStack", "User ID is null, cannot navigate to UserWordsScreen")
                        }
                    },
                    onNavigateToHome = {
                        navController.navigate(Screen.Word.route) {
                            popUpTo(Screen.Word.route) { inclusive = false }
                        }
                    }
                ) {
                    WordOfTheWeekScreen(navController)
                }
            } else {
                WordOfTheWeekScreen(navController)
            }
        }
        composable(
            route = Screen.UserWords.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) {
            if (userId != null) {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                ZaDumiteScaffold(
                    currentRoute = currentRoute,
                    onNavigateToWords = {
                        navController.navigate(Screen.UserWords.route.replace("{userId}", userId.toString())) {
                            popUpTo(Screen.Word.route) { inclusive = false }
                        }
                    },
                    onNavigateToHome = {
                        navController.navigate(Screen.Word.route) {
                            popUpTo(Screen.Word.route) { inclusive = false }
                        }
                    }
                ) {
                    UserWordsScreen(userId = userId)
                }
            } else {
                Log.e("NavigationStack", "User ID is null, cannot navigate to UserWordsScreen")
            }
        }

    }
}
