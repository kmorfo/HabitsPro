package es.rlujancreations.habitsapppro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import es.rlujancreations.habitsapppro.authentication.presentation.login.LoginScreen
import es.rlujancreations.habitsapppro.authentication.presentation.signup.SignUpScreen
import es.rlujancreations.habitsapppro.home.presentarion.detail.DetailScreen
import es.rlujancreations.habitsapppro.home.presentarion.home.HomeScreen
import es.rlujancreations.onboarding.presentation.OnboardingScreen

/**
 * Created by Raúl L.C. on 2/4/24.
 */

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    userId: String,
    logout: () -> Unit
) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Onboarding.route) {
            es.rlujancreations.onboarding.presentation.OnboardingScreen(
                onFinish = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Login.route)
                }
            )
        }
        composable(NavigationRoute.Login.route) {
            LoginScreen(
                onLogin = {
                    navHostController.popBackStack()
                    println(it)
                    navHostController.navigate(NavigationRoute.Home.route + "?userId=$it")
                },
                onSignUp = {
                    navHostController.navigate(NavigationRoute.SignUp.route)
                }
            )
        }
        composable(NavigationRoute.SignUp.route) {
            SignUpScreen(
                onSignIn = {
                    //delete all BackStack routes
                    navHostController.navigate(NavigationRoute.Home.route + "?userId=$it") {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onLogin = { navHostController.popBackStack() }
            )
        }
        composable(
            NavigationRoute.Home.route + "?userId={userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.StringType
                nullable = false
                defaultValue = ""
            })
        ) {
            HomeScreen(
                onNewHabit = { userId ->
                    navHostController.navigate(NavigationRoute.Detail.route + "?userId=$userId")
                },
                onSettings = { navHostController.navigate(NavigationRoute.Settings.route) },
                onEditHabit = { habitId, userId ->
                    navHostController.navigate(NavigationRoute.Detail.route + "?userId=$userId&habitId=$habitId")
                }
            )
        }
        composable(
            NavigationRoute.Detail.route + "?userId={userId}&habitId={habitId}",
            arguments = listOf(
                navArgument("habitId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = ""
                }
            )
        ) {
            DetailScreen(
                onBack = { navHostController.popBackStack() },
                onSave = { navHostController.popBackStack() }
            )
        }
        composable(NavigationRoute.LaunchHome.route) {
            LaunchedEffect(Unit) {
                navHostController.navigate(NavigationRoute.Home.route + "?userId=$userId") {
                    popUpTo(navHostController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
        composable(NavigationRoute.Settings.route) {
            es.rlujancreations.settings.presentation.SettingsScreen(onBack = { navHostController.popBackStack() },
                onLogout = {
                    logout()
                    navHostController.navigate(NavigationRoute.Login.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                })
        }
    }
}

