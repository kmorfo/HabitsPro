package es.rlujancreations.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.authentication.domain.usecase.SignUpUseCases
import es.rlujancreations.authentication.presentation.R

import es.rlujancreations.authentication.presentation.util.PasswordErrorParser
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
        state = state.copy(passwordError = PasswordErrorParser.parseError(passwordResult))

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
                        state = state.copy(responseError = it.message)
                    }
                state = state.copy(isLoading = false)
            }
        }
    }

    fun emptyResponseError() {
        state = state.copy(responseError = null)
    }
}


