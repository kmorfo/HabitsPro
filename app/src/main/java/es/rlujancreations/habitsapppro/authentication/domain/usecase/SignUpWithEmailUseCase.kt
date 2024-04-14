package es.rlujancreations.habitsapppro.authentication.domain.usecase

import es.rlujancreations.habitsapppro.authentication.domain.repository.AuthenticationRepository

/**
 * Created by Raúl L.C. on 13/4/24.
 */
class SignUpWithEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signUp(email, password)
    }
}