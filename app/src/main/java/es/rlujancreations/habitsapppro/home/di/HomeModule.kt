package es.rlujancreations.habitsapppro.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rlujancreations.habitsapppro.home.data.repository.HomeRepositoryImpl
import es.rlujancreations.habitsapppro.home.domain.home.usecases.CompleteHabitUseCase
import es.rlujancreations.habitsapppro.home.domain.home.usecases.GetAllHabitsForDateUseCase
import es.rlujancreations.habitsapppro.home.domain.home.usecases.HomeUseCases
import es.rlujancreations.habitsapppro.home.domain.repository.HomeRepository
import javax.inject.Singleton

/**
 * Created by Raúl L.C. on 17/4/24.
 */

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Singleton
    @Provides
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getAllHabitsForDateUseCase = GetAllHabitsForDateUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideHomeReposoty(): HomeRepository {
        return HomeRepositoryImpl()
    }

}