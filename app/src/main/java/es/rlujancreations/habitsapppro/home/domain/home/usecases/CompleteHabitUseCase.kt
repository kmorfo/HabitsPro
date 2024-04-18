package es.rlujancreations.habitsapppro.home.domain.home.usecases

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class CompleteHabitUseCase(private val repositoty: HomeRepository) {
    suspend operator fun invoke(habit: Habit, date: ZonedDateTime) {
        val newHabit =
            if (habit.completedDates.contains(date.toLocalDate())) {
                habit.copy(completedDates = habit.completedDates - date.toLocalDate())
            } else {
                habit.copy(completedDates = habit.completedDates + date.toLocalDate())
            }
        repositoty.insertHabit(newHabit)
    }
}