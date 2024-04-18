package es.rlujancreations.habitsapppro.home.domain.home.usecases

import es.rlujancreations.habitsapppro.home.domain.models.Habit
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime


/**
 * Created by Raúl L.C. on 17/4/24.
 */
class GetAllHabitsForDateUseCase(private val repositoty: HomeRepository) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repositoty.getAllHabitsForSelectedDate(date)
    }
}