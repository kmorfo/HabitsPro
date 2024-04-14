package es.rlujancreations.habitsapppro.authentication.presentation.login

/**
 * Created by Raúl L.C. on 13/4/24.
 */
sealed interface LoginEvent {
    data class EmailChange(val email: String) : LoginEvent
    data class PasswordChange(val password: String) : LoginEvent
    object Login : LoginEvent

}
