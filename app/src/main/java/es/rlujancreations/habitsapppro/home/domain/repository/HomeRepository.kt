package es.rlujancreations.habitsapppro.home.domain.repository

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
interface HomeRepository {
    fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>>
    suspend fun insertHabit(habit: Habit)
    suspend fun getHabitById(id: String): Habit
}