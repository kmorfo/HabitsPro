package es.rlujancreations.habitsapppro.onboarding.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Raúl L.C. on 6/4/24.
 */
data class OnboardingPagerInformation(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @DrawableRes val image: Int
)
