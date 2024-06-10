package es.rlujancreations.habitsapppro.authentication.presentation.util

import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.authentication.domain.usecase.PasswordResult

/**
 * Created by Raúl L.C. on 10/6/24.
 */
object PasswordErrorParser {
    fun parseError(error: PasswordResult): Int? {
        return when (error) {
            PasswordResult.VALID -> null
            PasswordResult.INVALID_LOWERCASE -> R.string.allUppercasePassword
            PasswordResult.INVALID_UPPERCASE -> R.string.allLowercasePassword
            PasswordResult.INVALID_DIGITS -> R.string.allcharactersPassword
            PasswordResult.INVALID_LENGTH -> R.string.sortPassword
        }
    }
}