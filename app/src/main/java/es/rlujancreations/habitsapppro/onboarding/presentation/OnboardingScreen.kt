package es.rlujancreations.habitsapppro.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.onboarding.presentation.components.OnboardingPager

/**
 * Created by Raúl L.C. on 6/4/24.
 */
@ExperimentalPagerApi
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit
) {
    LaunchedEffect(key1 = viewModel.hasSeenOnboarding) {
        if (viewModel.hasSeenOnboarding) {
            onFinish()
        }
    }
    val pages = listOf(
        OnboardingPagerInformation(
            title = R.string.onboarding_title1,
            subtitle = R.string.onboarding_subtitle1,
            image = R.drawable.onboarding1
        ),
        OnboardingPagerInformation(
            title = R.string.onboarding_title2,
            subtitle = R.string.onboarding_subtitle2,
            image = R.drawable.onboarding2
        ),
        OnboardingPagerInformation(
            title = R.string.onboarding_title3,
            subtitle = R.string.onboarding_subtitle3,
            image = R.drawable.onboarding3
        ),
        OnboardingPagerInformation(
            title = R.string.onboarding_title4,
            subtitle = R.string.onboarding_subtitle4,
            image = R.drawable.onboarding4
        )
    )
    OnboardingPager(pages = pages, onFinish = { viewModel.completeOnboarding() })
}