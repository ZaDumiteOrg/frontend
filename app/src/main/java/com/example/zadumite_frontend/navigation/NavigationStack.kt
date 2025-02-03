package com.example.zadumite_frontend.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zadumite_frontend.ui.signup.SignUpScreen
import com.example.zadumite_frontend.StartScreen
import com.example.zadumite_frontend.ui.add_word.AddWordScreen
import com.example.zadumite_frontend.ui.login.LogInScreen
import com.example.zadumite_frontend.ui.profile.ProfileScreen
import com.example.zadumite_frontend.ui.scaffold.ZaDumiteScaffold
import com.example.zadumite_frontend.ui.user_words.UserWordsScreen
import com.example.zadumite_frontend.ui.word.WordOfTheWeekScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
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
                onNavigateToWordScreen = { navController.navigate(Screen.Word.route) },
                onNavigateToAddWordScreen = { navController.navigate(Screen.AddWord.route) }
            )
        }
        composable(route = Screen.AddWord.route) {
            AddWordScreen()
        }
        composable(route = Screen.Word.route) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute in listOf(Screen.Word.route, Screen.UserWords.route)) {
                ZaDumiteScaffold(
                    currentRoute = currentRoute,
                    onNavigateToWords = {
                        navController.navigate(Screen.UserWords.route) {
                            popUpTo(Screen.Word.route) { inclusive = false }
                        }
                    },
                    onNavigateToHome = {
                        navController.navigate(Screen.Word.route) {
                            popUpTo(Screen.Word.route) { inclusive = false }
                        }
                    },
                    onNavigateToProfile = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Profile.route) { inclusive = false }
                        }
                    }
                ) {
                    WordOfTheWeekScreen()
                }
            } else {
                WordOfTheWeekScreen()
            }
        }
        composable(route = Screen.UserWords.route) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            ZaDumiteScaffold(
                currentRoute = currentRoute,
                onNavigateToWords = {
                    navController.navigate(Screen.UserWords.route) {
                        popUpTo(Screen.Word.route) { inclusive = false }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Word.route) {
                        popUpTo(Screen.Word.route) { inclusive = false }
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Profile.route) { inclusive = false }
                    }
                }
            ) {
                UserWordsScreen()
            }
        }
        composable(route = Screen.Profile.route) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            ZaDumiteScaffold(
                currentRoute = currentRoute,
                onNavigateToWords = {
                    navController.navigate(Screen.UserWords.route) {
                    popUpTo(Screen.Word.route) { inclusive = false }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Word.route) {
                    popUpTo(Screen.Word.route) { inclusive = false }
                    }
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Profile.route) { inclusive = false }
                    }
                }
            ) {
                ProfileScreen(
                    onNavigateToStartPage = { navController.navigate(Screen.Start.route) }
                )
            }
        }
    }
}
