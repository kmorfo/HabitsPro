package es.rlujancreations.authentication.domain.usecase

/**
 * Created by Raúl L.C. on 13/4/24.
 */
data class SignUpUseCases(
    val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val getUserIdUseCase: GetUserIdUseCase
)
