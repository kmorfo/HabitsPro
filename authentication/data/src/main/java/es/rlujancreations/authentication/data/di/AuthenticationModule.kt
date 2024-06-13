package es.rlujancreations.authentication.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rlujancreations.authentication.data.matcher.EmailMatcherImpl
import es.rlujancreations.authentication.data.repository.AuthenticationRepositoryImpl
import es.rlujancreations.authentication.domain.matcher.EmailMatcher
import es.rlujancreations.authentication.domain.repository.AuthenticationRepository
import es.rlujancreations.authentication.domain.usecase.GetUserIdUseCase
import es.rlujancreations.authentication.domain.usecase.LoginUseCases
import es.rlujancreations.authentication.domain.usecase.LoginWithEmailUseCase
import es.rlujancreations.authentication.domain.usecase.LogoutUseCase
import es.rlujancreations.authentication.domain.usecase.SignUpUseCases
import es.rlujancreations.authentication.domain.usecase.SignUpWithEmailUseCase
import es.rlujancreations.authentication.domain.usecase.ValidateEmailUseCase
import es.rlujancreations.authentication.domain.usecase.ValidatePasswordUseCase

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
            validatePasswordUseCase = ValidatePasswordUseCase(),
            getUserIdUseCase = GetUserIdUseCase(repository)
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
            validatePasswordUseCase = ValidatePasswordUseCase(),
            getUserIdUseCase = GetUserIdUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthenticationRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthenticationRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}