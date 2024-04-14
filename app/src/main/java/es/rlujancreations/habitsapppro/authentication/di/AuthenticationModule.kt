package es.rlujancreations.habitsapppro.authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rlujancreations.habitsapppro.authentication.data.matcher.EmailMatcherImpl
import es.rlujancreations.habitsapppro.authentication.data.repository.AuthenticationRepositoryImpl
import es.rlujancreations.habitsapppro.authentication.domain.matcher.EmailMatcher
import es.rlujancreations.habitsapppro.authentication.domain.repository.AuthenticationRepository
import es.rlujancreations.habitsapppro.authentication.domain.usecase.GetUserIdUseCase
import es.rlujancreations.habitsapppro.authentication.domain.usecase.LoginUseCases
import es.rlujancreations.habitsapppro.authentication.domain.usecase.LoginWithEmailUseCase
import es.rlujancreations.habitsapppro.authentication.domain.usecase.SignUpUseCases
import es.rlujancreations.habitsapppro.authentication.domain.usecase.SignUpWithEmailUseCase
import es.rlujancreations.habitsapppro.authentication.domain.usecase.ValidateEmailUseCase
import es.rlujancreations.habitsapppro.authentication.domain.usecase.ValidatePasswordUseCase
import javax.inject.Singleton

/**
 * Created by Raúl L.C. on 13/4/24.
 */

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): SignUpUseCases {
        return SignUpUseCases(
            signUpWithEmailUseCase = SignUpWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthenticationRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }
}