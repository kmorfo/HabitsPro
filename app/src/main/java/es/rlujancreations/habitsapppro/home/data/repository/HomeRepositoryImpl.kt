package es.rlujancreations.habitsapppro.home.data.repository

import es.rlujancreations.habitsapppro.home.data.extension.toStartOfDateTimestamp
import es.rlujancreations.habitsapppro.home.data.local.HomeDao
import es.rlujancreations.habitsapppro.home.data.mapper.toDomain
import es.rlujancreations.habitsapppro.home.data.mapper.toEntity
import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class HomeRepositoryImpl(
    private val dao: HomeDao
) : HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        return dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp())
            .map { it.map { it.toDomain() } }
    }

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }
}