package com.example.zadumite_frontend.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zadumite_frontend.data.model.session.LogoutNotifier
import com.example.zadumite_frontend.ui.signup.SignUpScreen
import com.example.zadumite_frontend.ui.start_screen.StartScreen
import com.example.zadumite_frontend.ui.add_word.AddWordScreen
import com.example.zadumite_frontend.ui.login.LogInScreen
import com.example.zadumite_frontend.ui.profile.ProfileScreen
import com.example.zadumite_frontend.ui.scaffold.ZaDumiteScaffold
import com.example.zadumite_frontend.ui.user_words.UserWordsScreen
import com.example.zadumite_frontend.ui.word.WordOfTheWeekScreen
import org.koin.compose.getKoin

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    val logoutNotifier: LogoutNotifier = getKoin().get()

    LaunchedEffect(Unit) {
        logoutNotifier.logoutFlow.collect {
            navController.navigate(Screen.Start.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

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
                onNavigateBack = { navController.navigate(Screen.Start.route) },
                onNavigateToWordScreen = { navController.navigate(Screen.Word.route) }
            )
        }
        composable(route = Screen.LogIn.route) {
            LogInScreen(
                onNavigateBack = { navController.navigate(Screen.Start.route) },
                onNavigateToWordScreen = { navController.navigate(Screen.Word.route) },
                onNavigateToAddWordScreen = { navController.navigate(Screen.AddWord.route) }
            )
        }
        composable(route = Screen.AddWord.route) {
            AddWordScreen(
                onNavigateBack = { navController.navigate(Screen.LogIn.route) }
            )
        }
        composable(route = Screen.Word.route) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute in listOf(Screen.Word.route, Screen.UserWords.route, Screen.Profile.route)) {
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
            if(currentRoute in listOf(Screen.Word.route, Screen.UserWords.route, Screen.Profile.route)) {
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
            } else {
                UserWordsScreen()
            }
        }
        composable(route = Screen.Profile.route) {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if(currentRoute in listOf(Screen.Word.route, Screen.UserWords.route, Screen.Profile.route)) {
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
            } else {
                ProfileScreen(
                    onNavigateToStartPage = { navController.navigate(Screen.Start.route) }
                )
            }
        }
    }
}
