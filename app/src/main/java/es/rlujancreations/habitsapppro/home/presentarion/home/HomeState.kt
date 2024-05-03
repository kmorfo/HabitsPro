package es.rlujancreations.habitsapppro.home.presentarion.home

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 15/4/24.
 */
data class HomeState(
    val userId: String = "",
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val selectedDate: ZonedDateTime = ZonedDateTime.now(),
    val habits: List<Habit> = emptyList()
)
