package es.rlujancreations.habitsapppro.home.domain.detail.usecases

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class InsertHabitUseCase(private val repositoty: HomeRepository) {
    suspend operator fun invoke(habit: Habit) {
        repositoty.insertHabit(habit)
    }
}