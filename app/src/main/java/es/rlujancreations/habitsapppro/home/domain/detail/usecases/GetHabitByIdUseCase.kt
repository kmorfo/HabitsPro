package es.rlujancreations.habitsapppro.home.domain.detail.usecases

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository

/**
 * Created by Raúl L.C. on 18/4/24.
 */
class GetHabitByIdUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(id: String): Habit {
        return repository.getHabitById(id)
    }
}