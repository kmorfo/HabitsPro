package es.rlujancreations.habitsapppro.home.domain.home.usecases

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime


/**
 * Created by Raúl L.C. on 17/4/24.
 */
class GetAllHabitsForDateUseCase(private val repository: HomeRepository) {
    operator fun invoke(date: ZonedDateTime, userId: String): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date, userId).map {
            it.filter { it.frequency.contains(date.dayOfWeek) }
        }.distinctUntilChanged()
    }
}