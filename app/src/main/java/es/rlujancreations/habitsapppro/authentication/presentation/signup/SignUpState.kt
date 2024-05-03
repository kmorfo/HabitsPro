package es.rlujancreations.habitsapppro.authentication.presentation.signup

import androidx.annotation.StringRes

/**
 * Created by Raúl L.C. on 14/4/24.
 */
data class SignUpState(
    val email: String = "",
    val userId: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null,
    val isSignedIn: Boolean = false,
    val isLoading: Boolean = false,
    val login: Boolean = false
)
