package es.rlujancreations.home.domain.home.usecases

/**
 * Created by Raúl L.C. on 17/4/24.
 */
data class HomeUseCases(
    val completeHabitUseCase: CompleteHabitUseCase,
    val getAllHabitsForDateUseCase: GetAllHabitsForDateUseCase,
    val syncHabitUseCase: SyncHabitUseCase
)