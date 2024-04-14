package es.rlujancreations.habitsapppro.authentication.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.authentication.domain.repository.AuthenticationRepository
import es.rlujancreations.habitsapppro.authentication.domain.usecase.LoginUseCases
import es.rlujancreations.habitsapppro.authentication.domain.usecase.LoginWithEmailUseCase
import es.rlujancreations.habitsapppro.authentication.domain.usecase.PasswordResult
import es.rlujancreations.habitsapppro.authentication.domain.usecase.ValidateEmailUseCase
import es.rlujancreations.habitsapppro.authentication.domain.usecase.ValidatePasswordUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 13/4/24.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> {
                state = state.copy(email = event.email)
            }

            LoginEvent.Login -> login()
            is LoginEvent.PasswordChange -> {
                state = state.copy(password = event.password)
            }
        }
    }

    private fun login() {
        state = state.copy(
            emailError = null,
            passwordError = null
        )
        if (!loginUseCases.validateEmailUseCase(state.email))
            state = state.copy(emailError = R.string.emailError)

        val passwordResult = loginUseCases.validatePasswordUseCase(state.password)
        if (passwordResult is PasswordResult.Invalid)
            state = state.copy(passwordError = passwordResult.errorMessage)

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(isLoading = true)

            viewModelScope.launch {
                loginUseCases.loginWithEmailUseCase(email = state.email, password = state.password)
                    .onSuccess {
                        state = state.copy(isLoggedIn = true)
                    }.onFailure {
                        val error = it.message
                        println("La jodimos tia paca $error")
                        //Para mostrar al usuario
                        //TODO Crear un snackbar con el error
//                        state = state.copy(emailError = it.message)
                    }
                state = state.copy(isLoading = false)
            }
        }
    }
}