package es.rlujancreations.habitsapppro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import es.rlujancreations.habitsapppro.navigation.NavigationHost
import es.rlujancreations.habitsapppro.navigation.NavigationRoute
import es.rlujancreations.habitsapppro.ui.theme.HabitsAppProTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsAppProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = getStartDestination(),
                        userId = viewModel.isLoggedIn,
                        logout = { viewModel.logout() }
                    )
                }
            }
        }
    }

    private fun getStartDestination(): NavigationRoute {
        if (viewModel.isLoggedIn.isNotBlank()) return NavigationRoute.LaunchHome

        return if (viewModel.hasSeenOnboarding) NavigationRoute.Login else NavigationRoute.Onboarding
    }
}

