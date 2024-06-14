package es.rlujancreations.home.domain.models

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 15/4/24.
 */
data class Habit(
    val id: String,
    val userId: String,
    val name: String,
    val frequency: List<DayOfWeek>,
    val completedDates: List<LocalDate>,
    val reminder: LocalTime,
    val startDate: ZonedDateTime
)
