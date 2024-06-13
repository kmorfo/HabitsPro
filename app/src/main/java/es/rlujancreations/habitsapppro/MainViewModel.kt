package es.rlujancreations.habitsapppro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.habitsapppro.authentication.domain.usecase.GetUserIdUseCase
import es.rlujancreations.habitsapppro.authentication.domain.usecase.LogoutUseCase
import es.rlujancreations.onboarding.domain.usecase.HasSeenOnboardingUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 7/4/24.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: es.rlujancreations.onboarding.domain.usecase.HasSeenOnboardingUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    var isLoggedIn by mutableStateOf(getUserIdUseCase() ?: "")
        private set

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}