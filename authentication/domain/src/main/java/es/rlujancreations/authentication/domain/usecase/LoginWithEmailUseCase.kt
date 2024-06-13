package es.rlujancreations.authentication.domain.usecase

import es.rlujancreations.authentication.domain.repository.AuthenticationRepository

/**
 * Created by Raúl L.C. on 13/4/24.
 */
class LoginWithEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.login(email, password)
    }
}