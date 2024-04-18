package es.rlujancreations.habitsapppro.home.presentarion.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class DetailViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set

    fun onEvent(detailEvent: DetailEvent) {
        when (detailEvent) {
            is DetailEvent.FrequencyChange -> {
                val frequency = if (state.frequency.contains(detailEvent.dayOfWeek))
                    state.frequency - detailEvent.dayOfWeek
                else
                    state.frequency + detailEvent.dayOfWeek
                state = state.copy(frequency = frequency)
            }

            DetailEvent.HabitSave -> TODO()
            is DetailEvent.NameChange -> {
                state = state.copy(habitName = detailEvent.name)
            }

            is DetailEvent.ReminderChange -> {
                state = state.copy(reminder = detailEvent.time)
            }
        }
    }
}