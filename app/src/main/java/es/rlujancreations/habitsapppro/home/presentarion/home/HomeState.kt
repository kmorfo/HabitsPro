package es.rlujancreations.habitsapppro.home.presentarion.home

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 15/4/24.
 */
data class HomeState(
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val selectedDate: ZonedDateTime = ZonedDateTime.now(),
    val habits: List<Habit> = mockHabits//emptyList()
)

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
}
