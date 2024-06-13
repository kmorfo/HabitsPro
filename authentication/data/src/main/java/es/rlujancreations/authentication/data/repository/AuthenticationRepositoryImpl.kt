package es.rlujancreations.authentication.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.rlujancreations.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await

/**
 * Created by Raúl L.C. on 13/4/24.
 */
class AuthenticationRepositoryImpl : AuthenticationRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (err: Exception) {
            Result.failure(err)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (err: Exception) {
            Result.failure(err)
        }
    }

    override fun getUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun logout() {
        Firebase.auth.signOut()
    }
}