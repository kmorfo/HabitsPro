package es.rlujancreations.habitsapppro.authentication.data.matcher

import android.util.Patterns
import es.rlujancreations.habitsapppro.authentication.domain.matcher.EmailMatcher

/**
 * Created by Raúl L.C. on 13/4/24.
 */
class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}