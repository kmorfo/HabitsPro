package es.rlujancreations.authentication.presentation.login

import androidx.annotation.StringRes

/**
 * Created by Raúl L.C. on 13/4/24.
 */
data class LoginState(
    val email: String = "",
    val userId: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val responseError: String? = null
)
