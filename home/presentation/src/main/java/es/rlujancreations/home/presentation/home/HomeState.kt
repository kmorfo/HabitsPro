package es.rlujancreations.home.presentation.home

import es.rlujancreations.home.domain.models.Habit
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
