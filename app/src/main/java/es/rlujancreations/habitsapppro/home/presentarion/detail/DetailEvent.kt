package es.rlujancreations.habitsapppro.home.presentarion.detail

import java.time.DayOfWeek
import java.time.LocalTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
sealed interface DetailEvent {
    data class ReminderChange(val time: LocalTime) : DetailEvent
    data class FrequencyChange(val dayOfWeek: DayOfWeek) : DetailEvent
    data class NameChange(val name: String) : DetailEvent
    object HabitSave : DetailEvent
}