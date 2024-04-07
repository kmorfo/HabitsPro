package es.rlujancreations.habitsapppro.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.habitsapppro.onboarding.domain.usecase.CompleteOnboardingUseCase
import es.rlujancreations.habitsapppro.onboarding.domain.usecase.HasSeenOnboardingUseCase
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 7/4/24.
 */
@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val completeOnboardingUseCase: CompleteOnboardingUseCase,
) : ViewModel() {
    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    fun completeOnboarding(){
        completeOnboardingUseCase()
        hasSeenOnboarding = true
    }
}