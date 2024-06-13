package es.rlujancreations.authentication.domain.usecase

import es.rlujancreations.authentication.domain.repository.AuthenticationRepository

/**
 * Created by Raúl L.C. on 14/4/24.
 */
class GetUserIdUseCase(private val repository: AuthenticationRepository) {
    operator fun invoke(): String? {
        return repository.getUserId()
    }
}