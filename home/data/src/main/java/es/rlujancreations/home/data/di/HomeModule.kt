package es.rlujancreations.home.data.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.rlujancreations.home.data.alarm.AlarmHandlerImpl
import es.rlujancreations.home.data.local.HomeDao
import es.rlujancreations.home.data.local.HomeDatabase
import es.rlujancreations.home.data.local.typeconverter.HomeTypeConverter
import es.rlujancreations.home.data.remote.HomeApi
import es.rlujancreations.home.data.repository.HomeRepositoryImpl
import es.rlujancreations.home.domain.alarm.AlarmHandler
import es.rlujancreations.home.domain.detail.usecases.DetailUseCases
import es.rlujancreations.home.domain.detail.usecases.GetHabitByIdUseCase
import es.rlujancreations.home.domain.detail.usecases.InsertHabitUseCase
import es.rlujancreations.home.domain.home.usecases.CompleteHabitUseCase
import es.rlujancreations.home.domain.home.usecases.GetAllHabitsForDateUseCase
import es.rlujancreations.home.domain.home.usecases.HomeUseCases
import es.rlujancreations.home.domain.home.usecases.SyncHabitUseCase
import es.rlujancreations.home.domain.repository.HomeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
            completeHabitUseCase = CompleteHabitUseCase(
                repository
            ),
            getAllHabitsForDateUseCase = GetAllHabitsForDateUseCase(
                repository
            ),
            syncHabitUseCase = SyncHabitUseCase(
                repository
            )
        )
    }


    @Singleton
    @Provides
    fun provideDetailUseCases(repository: HomeRepository): DetailUseCases {
        return DetailUseCases(
            getHabitByIdUseCase = GetHabitByIdUseCase(
                repository
            ),
            insertHabitUseCase = InsertHabitUseCase(
                repository
            )
        )
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HomeDatabase {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habits_db"
        ).addTypeConverter(HomeTypeConverter()).build()
    }

    @Singleton
    @Provides
    fun provideHabitDao(database: HomeDatabase): HomeDao {
        return database.getDao()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(client: OkHttpClient): HomeApi {
        return Retrofit.Builder().baseUrl(HomeApi.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        dao: HomeDao,
        api: HomeApi,
        alarmHandler: AlarmHandler,
        workManager: WorkManager
    ): HomeRepository {
        return HomeRepositoryImpl(dao, api, alarmHandler, workManager)
    }

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideAlarmHandler(@ApplicationContext context: Context): AlarmHandler {
        return AlarmHandlerImpl(context)
    }
}