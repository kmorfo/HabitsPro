package es.rlujancreations.habitsapppro.authentication.domain.usecase

import es.rlujancreations.habitsapppro.authentication.domain.repository.AuthenticationRepository

/**
 * Created by Raúl L.C. on 14/4/24.
 */
class LogoutUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke() {
        return repository.logout()
    }
}