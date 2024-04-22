package es.rlujancreations.habitsapppro.home.data.repository

import es.rlujancreations.habitsapppro.home.data.extension.toStartOfDateTimestamp
import es.rlujancreations.habitsapppro.home.data.local.HomeDao
import es.rlujancreations.habitsapppro.home.data.mapper.toDomain
import es.rlujancreations.habitsapppro.home.data.mapper.toDto
import es.rlujancreations.habitsapppro.home.data.mapper.toEntity
import es.rlujancreations.habitsapppro.home.data.remote.HomeApi
import es.rlujancreations.habitsapppro.home.data.remote.util.resultOf
import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class HomeRepositoryImpl(private val dao: HomeDao, private val api: HomeApi) : HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        val localFlow = dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp())
            .map { it.map { it.toDomain() } }

        val apiFlow = getHabitsFromApi()

        return localFlow.combine(apiFlow) { db, _ -> db }
    }

    private fun getHabitsFromApi(): Flow<List<Habit>> {
        return flow {
            resultOf {
                val habits = api.getAllHabits().toDomain()
                insertHabits(habits)
            }

            emit(emptyList<Habit>())
        }.onStart { emit(emptyList()) }
    }

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
        resultOf {
            api.insertHabit(habit.toDto())
        }
    }

    private suspend fun insertHabits(habits: List<Habit>) {
        dao.insertHabits(habits.map { it.toEntity() })
    }


    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }
}