package es.rlujancreations.habitsapppro.onboarding.domain.usecase

import es.rlujancreations.habitsapppro.onboarding.domain.repository.OnboardingRepository

/**
 * Created by Raúl L.C. on 7/4/24.
 */
class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke() {
        repository.completeOnboarding()
    }
}