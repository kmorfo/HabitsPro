package es.rlujancreations.habitsapppro.authentication.data.repository

import es.rlujancreations.habitsapppro.authentication.domain.repository.AuthenticationRepository

/**
 * Created by Raúl L.C. on 11/6/24.
 */
class FakeAuthenticationRepository : AuthenticationRepository {
    var fakeError = false
    val fakeErrorMessage = "There was a server error!"
    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (fakeError) {
            Result.failure(Exception(fakeErrorMessage))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return if (fakeError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }

    override fun getUserId(): String? {
        return if (fakeError) {
            null
        } else {
            "1"
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}