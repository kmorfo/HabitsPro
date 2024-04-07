package es.rlujancreations.habitsapppro.onboarding.data.repository

import android.content.SharedPreferences
import es.rlujancreations.habitsapppro.onboarding.domain.repository.OnboardingRepository

/**
 * Created by Raúl L.C. on 7/4/24.
 */
class OnboardingRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : OnboardingRepository {
    companion object {
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenOnboarding(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }

    override fun completeOnboarding() {
        sharedPreferences.edit().putBoolean(HAS_SEEN_ONBOARDING, true).apply()
    }
}