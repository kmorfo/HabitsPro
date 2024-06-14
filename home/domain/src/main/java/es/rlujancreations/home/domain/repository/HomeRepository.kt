package es.rlujancreations.home.domain.repository

import es.rlujancreations.home.domain.models.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
interface HomeRepository {
    fun getAllHabitsForSelectedDate(date: ZonedDateTime, userId: String): Flow<List<Habit>>
    suspend fun insertHabit(habit: Habit)
    suspend fun getHabitById(id: String): Habit
    suspend fun syncHabits()
}