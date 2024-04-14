package es.rlujancreations.habitsapppro

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.habitsapppro.authentication.domain.usecase.GetUserIdUseCase
import es.rlujancreations.habitsapppro.onboarding.domain.usecase.HasSeenOnboardingUseCase
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 7/4/24.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {
    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    var isLoggedIn by mutableStateOf(getUserIdUseCase() != null)
        private set
}