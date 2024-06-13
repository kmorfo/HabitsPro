package es.rlujancreations.authentication.domain.repository

/**
 * Created by Raúl L.C. on 13/4/24.
 */
interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
    fun getUserId(): String?
    suspend fun logout()
}