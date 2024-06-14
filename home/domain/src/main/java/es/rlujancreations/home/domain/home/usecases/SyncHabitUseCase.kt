package es.rlujancreations.home.domain.home.usecases

import es.rlujancreations.home.domain.repository.HomeRepository

/**
 * Created by Raúl L.C. on 29/5/24.
 */
class SyncHabitUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke() {
        repository.syncHabits()
    }
}