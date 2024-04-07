package es.rlujancreations.habitsapppro.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import es.rlujancreations.habitsapppro.onboarding.domain.usecase.CompleteOnboardingUseCase
import es.rlujancreations.habitsapppro.onboarding.domain.usecase.HasSeenOnboardingUseCase
import es.rlujancreations.habitsapppro.onboarding.presentation.OnboardingScreen
import es.rlujancreations.habitsapppro.onboarding.presentation.OnboardingViewModel

/**
 * Created by Raúl L.C. on 2/4/24.
 */
@ExperimentalPagerApi
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
            Text(text = "Login Screen")
        }
    }
}

