package es.rlujancreations.habitsapppro.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.rlujancreations.habitsapppro.authentication.presentation.login.LoginScreen
import es.rlujancreations.habitsapppro.authentication.presentation.signup.SignUpScreen
import es.rlujancreations.habitsapppro.onboarding.presentation.OnboardingScreen

/**
 * Created by Raúl L.C. on 2/4/24.
 */

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
) {
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Onboarding.route) {
            OnboardingScreen(
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
                    navHostController.navigate(NavigationRoute.Home.route)
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
                    navHostController.navigate(NavigationRoute.Home.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onLogin = { navHostController.popBackStack() }
            )
        }
        composable(NavigationRoute.Home.route) {
            Text(text = "Esta es la Home")
        }
    }
}

