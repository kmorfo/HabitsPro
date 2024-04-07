package es.rlujancreations.habitsapppro.onboarding.domain.repository

/**
 * Created by Raúl L.C. on 7/4/24.
 */
interface OnboardingRepository {
    fun hasSeenOnboarding(): Boolean
    fun completeOnboarding()
}