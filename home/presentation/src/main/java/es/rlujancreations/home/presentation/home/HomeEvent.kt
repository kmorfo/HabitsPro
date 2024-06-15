package es.rlujancreations.home.presentation.home

import es.rlujancreations.home.domain.models.Habit
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 15/4/24.
 */
sealed interface HomeEvent {
    data class ChangeDate(val date: ZonedDateTime) : HomeEvent
    data class CompleteHabit(val habit: Habit) : HomeEvent
}