package es.rlujancreations.home.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.home.domain.home.usecases.HomeUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 15/4/24.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val homeUseCases: HomeUseCases
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    private var currentDayJob: Job? = null

    init {
        val userId = savedStateHandle.get<String?>("userId") ?: ""
        state = state.copy(userId = userId)
        getHabits()
        viewModelScope.launch {
            homeUseCases.syncHabitUseCase()
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeDate -> {
                state = state.copy(selectedDate = event.date)
                getHabits()
            }

            is HomeEvent.CompleteHabit -> {
                viewModelScope.launch {
                    homeUseCases.completeHabitUseCase(event.habit, state.selectedDate)
                }
            }
        }
    }

    private fun getHabits() {
        currentDayJob?.cancel()
        currentDayJob = viewModelScope.launch {
            homeUseCases.getAllHabitsForDateUseCase(state.selectedDate, state.userId)
                .collectLatest {
                    state = state.copy(habits = it)
                }
        }
    }
}