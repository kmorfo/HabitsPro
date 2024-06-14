package es.rlujancreations.home.domain.detail.usecases

import es.rlujancreations.home.domain.models.Habit
import es.rlujancreations.home.domain.repository.HomeRepository

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class InsertHabitUseCase(private val repositoty: HomeRepository) {
    suspend operator fun invoke(habit: Habit) {
        repositoty.insertHabit(habit)
    }
}