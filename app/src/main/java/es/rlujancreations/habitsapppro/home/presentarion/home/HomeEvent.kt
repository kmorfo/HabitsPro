package es.rlujancreations.habitsapppro.home.presentarion.home

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 15/4/24.
 */
sealed interface HomeEvent {
    data class ChangeDate(val date: ZonedDateTime) : HomeEvent
    data class CompleteHabit(val habit: Habit) : HomeEvent
}