package es.rlujancreations.habitsapppro.authentication.presentation.login

import es.rlujancreations.authentication.domain.matcher.EmailMatcher
import es.rlujancreations.authentication.domain.usecase.GetUserIdUseCase
import es.rlujancreations.authentication.domain.usecase.LoginUseCases
import es.rlujancreations.authentication.domain.usecase.LoginWithEmailUseCase
import es.rlujancreations.authentication.domain.usecase.ValidateEmailUseCase
import es.rlujancreations.authentication.domain.usecase.ValidatePasswordUseCase
import es.rlujancreations.authentication.presentation.login.LoginEvent
import es.rlujancreations.authentication.presentation.login.LoginState
import es.rlujancreations.authentication.presentation.login.LoginViewModel
import es.rlujancreations.habitsapppro.authentication.data.repository.FakeAuthenticationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

/**
 * Created by Raúl L.C. on 11/6/24.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var authenticationRepository: FakeAuthenticationRepository

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        authenticationRepository = FakeAuthenticationRepository()
        val usecases = LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(
                authenticationRepository
            ),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            validateEmailUseCase = ValidateEmailUseCase(
                object : EmailMatcher {
                    override fun isValid(email: String): Boolean {
                        return email.isNotEmpty()
                    }
                }),
            getUserIdUseCase = GetUserIdUseCase(
                authenticationRepository
            )
        )
        loginViewModel = LoginViewModel(
            usecases,
            dispatcher
        )
    }

    @Test
    fun `inital state is empty`() {
        val state = loginViewModel.state
        assertEquals(
            LoginState(
                email = "",
                userId = "",
                password = "",
                emailError = null,
                passwordError = null,
                isLoggedIn = false,
                isLoading = false,
                responseError = null,
            ),
            state
        )
    }

    @Test
    fun `given an email, verify the state updates the email`() {
        val initialState = loginViewModel.state.email
        assertEquals(initialState, "")

        loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
        val updatedState = loginViewModel.state.email

        assertEquals(updatedState, "asd@asd.com")
    }

    @Test
    fun `given invalid email, show email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange(""))
        loginViewModel.onEvent(LoginEvent.Login)

        val state = loginViewModel.state
        assertNotNull(state.emailError)
    }


    @Test
    fun `set valid email, Login, no email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
        loginViewModel.onEvent(LoginEvent.Login)

        val state = loginViewModel.state
        assert(state.emailError == null)
    }

    @Test
    fun `set invalid password, Login, show password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange(""))
        loginViewModel.onEvent(LoginEvent.Login)

        val state = loginViewModel.state
        assertNotNull(state.passwordError)
    }

    @Test
    fun `set valid password, Login, no password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state

        assertNull(state.passwordError)
    }

    @Test
    fun `set valid details, Login, starts loading and then logs in`() = scope.runTest {
        loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
        loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(LoginEvent.Login)
        var state = loginViewModel.state

        assertNull(state.passwordError)
        assertNull(state.emailError)
        assert(state.isLoading)

        advanceUntilIdle()

        state = loginViewModel.state
        assert(state.isLoggedIn)
    }

    @Test
    fun `set valid details but server error, Login, starts loading and then show error`() =
        scope.runTest {
            authenticationRepository.fakeError = true
            loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
            loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
            loginViewModel.onEvent(LoginEvent.Login)
            var state = loginViewModel.state

            assertNull(state.passwordError)
            assertNull(state.emailError)
            assert(state.isLoading)

            advanceUntilIdle()

            state = loginViewModel.state
            assert(!state.isLoggedIn)
            assertNull(state.emailError)
            assert(!state.isLoading)
        }
}