package es.rlujancreations.home.presentation.detail

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
data class DetailState(
    val id: String? = null,
    val userId: String = "",
    val habitName: String = "",
    val frequency: List<DayOfWeek> = emptyList(),
    val reminder: LocalTime = LocalTime.now(),
    val completedDates: List<LocalDate> = emptyList(),
    val startDate: ZonedDateTime = ZonedDateTime.now(),
    val isSaved: Boolean = false
)
