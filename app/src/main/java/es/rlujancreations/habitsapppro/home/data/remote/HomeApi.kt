package es.rlujancreations.habitsapppro.home.data.remote

import es.rlujancreations.habitsapppro.home.data.remote.dto.HabitDto
import es.rlujancreations.habitsapppro.home.data.remote.dto.HabitResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

/**
 * Created by Raúl L.C. on 27/4/24.
 */
interface HomeApi {
    companion object {
        const val BASE_URL =
            "https://cursofirebase-c7dde-default-rtdb.europe-west1.firebasedatabase.app/"
    }
    @GET("habits.json")
    suspend fun getAllHabits(): HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(@Body habit: HabitResponse)
}