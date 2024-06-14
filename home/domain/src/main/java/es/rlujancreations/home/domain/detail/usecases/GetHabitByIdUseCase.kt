package es.rlujancreations.home.domain.detail.usecases

import es.rlujancreations.home.domain.models.Habit
import es.rlujancreations.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Raúl L.C. on 18/4/24.
 */
class GetHabitByIdUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(id: String): Habit {
        return withContext(Dispatchers.IO) {
            repository.getHabitById(id)
        }
    }
}