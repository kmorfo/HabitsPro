package es.rlujancreations.authentication.presentation.util

import es.rlujancreations.authentication.presentation.R

/**
 * Created by Raúl L.C. on 10/6/24.
 */
object PasswordErrorParser {
    fun parseError(error: es.rlujancreations.authentication.domain.usecase.PasswordResult): Int? {
        return when (error) {
            es.rlujancreations.authentication.domain.usecase.PasswordResult.VALID -> null
            es.rlujancreations.authentication.domain.usecase.PasswordResult.INVALID_LOWERCASE -> R.string.allUppercasePassword
            es.rlujancreations.authentication.domain.usecase.PasswordResult.INVALID_UPPERCASE -> R.string.allLowercasePassword
            es.rlujancreations.authentication.domain.usecase.PasswordResult.INVALID_DIGITS -> R.string.allcharactersPassword
            es.rlujancreations.authentication.domain.usecase.PasswordResult.INVALID_LENGTH -> R.string.sortPassword
        }
    }
}