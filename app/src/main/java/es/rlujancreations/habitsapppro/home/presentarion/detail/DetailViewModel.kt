package es.rlujancreations.habitsapppro.home.presentarion.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.habitsapppro.home.domain.detail.usecases.DetailUseCases
import es.rlujancreations.habitsapppro.home.domain.models.Habit
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 17/4/24.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases
) : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set

    private var userId: String

    init {
        val id = savedStateHandle.get<String?>("habitId")
        userId = savedStateHandle.get<String?>("userId") ?: ""
        state = state.copy(userId = userId)
        if (id != null) {
            viewModelScope.launch {
                val habit = detailUseCases.getHabitByIdUseCase(id)
                state = state.copy(
                    id = habit.id,
                    userId = state.userId,
                    habitName = habit.name,
                    frequency = habit.frequency,
                    reminder = habit.reminder,
                    completedDates = habit.completedDates,
                    startDate = habit.startDate
                )
            }
        }
    }

    fun onEvent(detailEvent: DetailEvent) {
        when (detailEvent) {
            is DetailEvent.FrequencyChange -> {
                val frequency = if (state.frequency.contains(detailEvent.dayOfWeek))
                    state.frequency - detailEvent.dayOfWeek
                else
                    state.frequency + detailEvent.dayOfWeek
                state = state.copy(frequency = frequency)
            }

            DetailEvent.HabitSave -> {
                viewModelScope.launch {
                    val habit = Habit(
                        id = state.id ?: UUID.randomUUID().toString(),
                        userId = state.userId,
                        name = state.habitName,
                        frequency = state.frequency,
                        completedDates = state.completedDates,
                        reminder = state.reminder,
                        startDate = state.startDate
                    )
                    detailUseCases.insertHabitUseCase(habit)
                }
                state = state.copy(
                    isSaved = true
                )
            }

            is DetailEvent.NameChange -> {
                state = state.copy(habitName = detailEvent.name)
            }

            is DetailEvent.ReminderChange -> {
                state = state.copy(reminder = detailEvent.time)
            }
        }
    }
}