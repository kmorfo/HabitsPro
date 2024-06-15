package es.rlujancreations.habitsapppro.home.data.repository

import es.rlujancreations.home.domain.models.Habit
import es.rlujancreations.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 11/6/24.
 */
class FakeHomeRepository : HomeRepository {
    private var habits = emptyList<Habit>()
    private val habitsFlow = MutableSharedFlow<List<Habit>>()

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime, userId: String) = habitsFlow

    override suspend fun insertHabit(habit: Habit) {
        habits = habits + habit
        habitsFlow.emit(habits)
    }

    override suspend fun getHabitById(id: String) = habits.first { id == it.id }

    override suspend fun syncHabits() {}
}