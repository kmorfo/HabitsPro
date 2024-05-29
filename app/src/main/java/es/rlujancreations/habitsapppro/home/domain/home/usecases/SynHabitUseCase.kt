package es.rlujancreations.habitsapppro.home.domain.home.usecases

import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository

/**
 * Created by Raúl L.C. on 29/5/24.
 */
class SynHabitUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke() {
        repository.syncHabits()
    }
}