package es.rlujancreations.habitsapppro.home.presentarion.detail

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
data class DetailState(
    val id: String? = null,
    val habitName: String = "",
    val frequency: List<DayOfWeek> = emptyList(),
    val reminder: LocalTime = LocalTime.now(),
    val completedDates: List<LocalDate> = emptyList(),
    val startDate: ZonedDateTime = ZonedDateTime.now(),
    val isSaved: Boolean = false
)