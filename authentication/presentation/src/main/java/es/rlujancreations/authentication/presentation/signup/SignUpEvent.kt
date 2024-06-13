package es.rlujancreations.authentication.presentation.signup

/**
 * Created by Raúl L.C. on 14/4/24.
 */
sealed interface SignUpEvent {
    data class EmailChange(val email: String) : SignUpEvent
    data class PasswordChange(val password: String) : SignUpEvent
    object LogIn : SignUpEvent
    object SignUp : SignUpEvent
}