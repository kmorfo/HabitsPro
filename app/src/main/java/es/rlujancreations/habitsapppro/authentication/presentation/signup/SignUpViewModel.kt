package es.rlujancreations.habitsapppro.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.authentication.domain.usecase.PasswordResult
import es.rlujancreations.habitsapppro.authentication.domain.usecase.SignUpUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 14/4/24.
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases
) : ViewModel() {
    var state by mutableStateOf(SignUpState())
        private set

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChange -> {
                state = state.copy(email = event.email)
            }

            is SignUpEvent.PasswordChange -> {
                state = state.copy(password = event.password)
            }

            SignUpEvent.LogIn -> {
                state = state.copy(login = true)
            }

            SignUpEvent.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        state = state.copy(
            emailError = null,
            passwordError = null
        )
        if (!signUpUseCases.validateEmailUseCase(state.email))
            state = state.copy(emailError = R.string.emailError)

        val passwordResult = signUpUseCases.validatePasswordUseCase(state.password)
        if (passwordResult is PasswordResult.Invalid)
            state = state.copy(passwordError = passwordResult.errorMessage)

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(isLoading = true)

            viewModelScope.launch {
                signUpUseCases.signUpWithEmailUseCase(
                    email = state.email,
                    password = state.password
                )
                    .onSuccess {
                        val userId = signUpUseCases.getUserIdUseCase() ?: ""
                        state = state.copy(isSignedIn = true, userId = userId)
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


