package es.rlujancreations.habitsapppro.home

/**
 * Created by Raúl L.C. on 11/6/24.
 */

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import es.rlujancreations.habitsapppro.MainActivity
import es.rlujancreations.habitsapppro.home.data.repository.FakeHomeRepository
import es.rlujancreations.habitsapppro.navigation.NavigationRoute
import es.rlujancreations.home.domain.detail.usecases.DetailUseCases
import es.rlujancreations.home.domain.detail.usecases.GetHabitByIdUseCase
import es.rlujancreations.home.domain.detail.usecases.InsertHabitUseCase
import es.rlujancreations.home.domain.home.usecases.CompleteHabitUseCase
import es.rlujancreations.home.domain.home.usecases.GetAllHabitsForDateUseCase
import es.rlujancreations.home.domain.home.usecases.HomeUseCases
import es.rlujancreations.home.domain.home.usecases.SyncHabitUseCase
import es.rlujancreations.home.presentation.detail.DetailScreen
import es.rlujancreations.home.presentation.detail.DetailViewModel
import es.rlujancreations.home.presentation.home.HomeScreen
import es.rlujancreations.home.presentation.home.HomeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CreateHabitE2E {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var homeRepository: FakeHomeRepository
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var navController: NavHostController

    @Before
    fun init() {
        hiltRule.inject()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)

        homeRepository = FakeHomeRepository()

        val homeUseCases = HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(
                homeRepository
            ),
            getAllHabitsForDateUseCase = GetAllHabitsForDateUseCase(
                homeRepository
            ),
            syncHabitUseCase = SyncHabitUseCase(
                homeRepository
            ),
        )
        homeViewModel = HomeViewModel(SavedStateHandle(), homeUseCases)

        val detailUseCases = DetailUseCases(
            getHabitByIdUseCase = GetHabitByIdUseCase(
                homeRepository
            ),
            insertHabitUseCase = InsertHabitUseCase(
                homeRepository
            )
        )
        detailViewModel = DetailViewModel(SavedStateHandle(), detailUseCases)

        composeRule.activity.setContent {
            navController = rememberNavController()
            NavHost(navController = navController, startDestination = NavigationRoute.Home.route) {
                composable(NavigationRoute.Home.route) {
                    HomeScreen(
                        onNewHabit = { navController.navigate(NavigationRoute.Detail.route) },
                        onSettings = { navController.navigate(NavigationRoute.Settings.route) },
                        onEditHabit = { habitId, userId ->
                            navController.navigate(NavigationRoute.Detail.route + "?userId=$userId&habitId=$habitId")
                        },
                        viewModel = homeViewModel
                    )
                }

                composable(
                    NavigationRoute.Detail.route + "?habitId={habitId}",
                    arguments = listOf(
                        navArgument("habitId") {
                            type = NavType.StringType
                            nullable = true
                            defaultValue = null
                        }
                    )
                ) {
                    DetailScreen(
                        onBack = { navController.popBackStack() },
                        onSave = { navController.popBackStack() },
                        viewModel = detailViewModel
                    )
                }
            }
        }

    }

    @Test
    fun createHabit() {
        val habitToCreate = "Vamos al Gym"
        composeRule.onNodeWithText("Home").assertIsDisplayed()
        composeRule.onNodeWithText(habitToCreate).assertDoesNotExist()
        composeRule.onNodeWithContentDescription("Add a new habit").performClick()
        assert(navController.currentDestination?.route?.startsWith(NavigationRoute.Detail.route) == true)

        composeRule.onNodeWithText("New Habit").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Enter habit name").performClick()
            .performTextInput(habitToCreate)

        val today = LocalDate.now().dayOfWeek
        composeRule.onNodeWithContentDescription(today.name).performClick()
        composeRule.onNodeWithContentDescription("Enter habit name").performImeAction()
        composeRule.onNodeWithText("Home").assertIsDisplayed()

        assert(navController.currentDestination?.route?.startsWith(NavigationRoute.Home.route) == true)
        composeRule.onNodeWithText(habitToCreate).assertIsDisplayed()
    }
}