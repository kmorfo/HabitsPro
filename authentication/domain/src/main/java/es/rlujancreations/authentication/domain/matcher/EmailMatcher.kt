package es.rlujancreations.authentication.domain.matcher

/**
 * Created by Raúl L.C. on 13/4/24.
 */
interface EmailMatcher {
    fun isValid(email: String): Boolean
}