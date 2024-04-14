package es.rlujancreations.habitsapppro.authentication.domain.usecase

import es.rlujancreations.habitsapppro.R

/**
 * Created by Raúl L.C. on 13/4/24.
 */
class ValidatePasswordUseCase {
    operator fun invoke(password: String): PasswordResult {
        if (password.length < 8) return PasswordResult.Invalid(R.string.sortPassword)
        if (!password.any { it.isLowerCase() }) return PasswordResult.Invalid(R.string.allUppercasePassword)
        if (!password.any { it.isUpperCase() }) return PasswordResult.Invalid(R.string.allLowercasePassword)
        if (!password.any { it.isDigit() }) return PasswordResult.Invalid(R.string.allcharactersPassword)

        return PasswordResult.Valid
    }
}

sealed class PasswordResult() {
    object Valid : PasswordResult()
    data class Invalid(val errorMessage: Int) : PasswordResult()
}