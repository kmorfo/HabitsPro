package es.rlujancreations.habitsapppro.home.data.repository

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class HomeRepositoryImpl() : HomeRepository {

    private val mockHabits = (1..30).map {
        val dates = mutableListOf<LocalDate>()
        if (it % 2 == 0) dates.add(LocalDate.now())
        Habit(
            id = it.toString(),
            name = "Habit $it",
            frequency = listOf(),
            completedDates = dates,
            reminder = LocalTime.now(),
            startDate = ZonedDateTime.now()
        )
    }.toMutableList()

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        return flowOf(mockHabits)
    }

    override suspend fun insertHabit(habit: Habit) {
        val index = mockHabits.indexOfFirst { it.id == habit.id }
        mockHabits.removeAt(index)
        mockHabits.add(index, habit)
    }
}