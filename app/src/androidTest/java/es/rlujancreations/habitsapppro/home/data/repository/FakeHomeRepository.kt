package es.rlujancreations.habitsapppro.home.data.repository

import es.rlujancreations.home.domain.models.Habit
import es.rlujancreations.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 11/6/24.
 */
class FakeHomeRepository : es.rlujancreations.home.domain.repository.HomeRepository {
    private var habits = emptyList<es.rlujancreations.home.domain.models.Habit>()
    private val habitsFlow = MutableSharedFlow<List<es.rlujancreations.home.domain.models.Habit>>()

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime, userId: String) = habitsFlow

    override suspend fun insertHabit(habit: es.rlujancreations.home.domain.models.Habit) {
        habits = habits + habit
        habitsFlow.emit(habits)
    }

    override suspend fun getHabitById(id: String) = habits.first { id == it.id }

    override suspend fun syncHabits() {}
}