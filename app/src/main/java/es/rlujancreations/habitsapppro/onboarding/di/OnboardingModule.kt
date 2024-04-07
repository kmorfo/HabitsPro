package es.rlujancreations.habitsapppro.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.rlujancreations.habitsapppro.onboarding.data.repository.OnboardingRepositoryImpl
import es.rlujancreations.habitsapppro.onboarding.domain.repository.OnboardingRepository
import es.rlujancreations.habitsapppro.onboarding.domain.usecase.CompleteOnboardingUseCase
import es.rlujancreations.habitsapppro.onboarding.domain.usecase.HasSeenOnboardingUseCase
import javax.inject.Singleton

/**
 * Created by Raúl L.C. on 7/4/24.
 */

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("habits_onboarding_preference", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(repository: OnboardingRepository): HasSeenOnboardingUseCase {
        return HasSeenOnboardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(repository: OnboardingRepository): CompleteOnboardingUseCase {
        return CompleteOnboardingUseCase(repository)
    }
}