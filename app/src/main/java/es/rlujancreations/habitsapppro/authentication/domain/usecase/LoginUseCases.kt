package es.rlujancreations.habitsapppro.authentication.domain.usecase

/**
 * Created by Raúl L.C. on 13/4/24.
 */
data class LoginUseCases(
    val loginWithEmailUseCase: LoginWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)
