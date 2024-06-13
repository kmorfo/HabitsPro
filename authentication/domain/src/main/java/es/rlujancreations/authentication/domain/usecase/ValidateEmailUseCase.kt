package es.rlujancreations.authentication.domain.usecase

import es.rlujancreations.authentication.domain.matcher.EmailMatcher

/**
 * Created by Raúl L.C. on 13/4/24.
 */
class ValidateEmailUseCase(private val emailMatcher: EmailMatcher) {
    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }
}