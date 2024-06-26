package es.rlujancreations.home.data.remote.dto

/**
 * Created by Raúl L.C. on 27/4/24.
 */
data class HabitDto(
    val name: String,
    val userId: String,
    val reminder: Long,
    val startDate: Long,
    val frequency: List<Int>,
    val completedDates: List<Long>?,
)