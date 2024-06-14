package es.rlujancreations.home.data.remote.util

import kotlinx.coroutines.CancellationException

/**
 * Created by Raúl L.C. on 27/4/24.
 */

// https://www.droidcon.com/2022/04/06/resilient-use-cases-with-kotlin-result-coroutines-and-annotations/
inline fun <T, R> T.resultOf(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}