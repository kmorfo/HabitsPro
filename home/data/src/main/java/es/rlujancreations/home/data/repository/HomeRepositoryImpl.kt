package es.rlujancreations.home.data.repository

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import es.rlujancreations.home.data.extension.toStartOfDateTimestamp
import es.rlujancreations.home.data.local.HomeDao
import es.rlujancreations.home.data.local.entity.HabitSyncEntity
import es.rlujancreations.home.data.mapper.toDomain
import es.rlujancreations.home.data.mapper.toDto
import es.rlujancreations.home.data.mapper.toEntity
import es.rlujancreations.home.data.mapper.toSyncEntity
import es.rlujancreations.home.data.remote.HomeApi
import es.rlujancreations.home.data.remote.util.resultOf
import es.rlujancreations.home.data.sync.HabitSyncWorker
import es.rlujancreations.home.domain.alarm.AlarmHandler
import es.rlujancreations.home.domain.models.Habit
import es.rlujancreations.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.Duration
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 17/4/24.
 */
class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi,
    private val alarmHandler: es.rlujancreations.home.domain.alarm.AlarmHandler,
    private val workManager: WorkManager
) : es.rlujancreations.home.domain.repository.HomeRepository {

    override fun getAllHabitsForSelectedDate(
        date: ZonedDateTime,
        userId: String
    ): Flow<List<es.rlujancreations.home.domain.models.Habit>> {
        val localFlow = dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp(), userId)
            .map { it.map { it.toDomain() } }

        val apiFlow = getHabitsFromApi(userId)

        return localFlow.combine(apiFlow) { db, _ -> db }
    }

    private fun getHabitsFromApi(userId: String): Flow<List<es.rlujancreations.home.domain.models.Habit>> {
        return flow {
            resultOf {
                val habits = api.getAllHabitsByUserId(userId = "\"$userId\"").toDomain()
                insertHabits(habits)
            }

            emit(emptyList<es.rlujancreations.home.domain.models.Habit>())
        }.onStart { emit(emptyList()) }
    }

    override suspend fun insertHabit(habit: es.rlujancreations.home.domain.models.Habit) {
        handleAlarm(habit = habit)
        dao.insertHabit(habit.toEntity())
        resultOf {
            api.insertHabit(habit.toDto())
        }.onFailure {
            dao.insertHabitSync(habit.toSyncEntity())
        }
    }

    private suspend fun insertHabits(habits: List<es.rlujancreations.home.domain.models.Habit>) {
        habits.forEach {
            handleAlarm(it)
            dao.insertHabit(it.toEntity())
        }
    }

    private suspend fun handleAlarm(habit: es.rlujancreations.home.domain.models.Habit) {
        try {
            val previous = dao.getHabitById(habit.id)
            alarmHandler.cancel(previous.toDomain())
        } catch (e: Exception) {/*Habit does not exist*/
        }
        alarmHandler.setRecurringAlarm(habit = habit)
    }

    override suspend fun getHabitById(id: String): es.rlujancreations.home.domain.models.Habit {
        return dao.getHabitById(id).toDomain()
    }

    override suspend fun syncHabits() {
        val worker = OneTimeWorkRequestBuilder<HabitSyncWorker>().setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        ).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
            .build()
        workManager.beginUniqueWork("sync_habit_id", ExistingWorkPolicy.REPLACE, worker).enqueue()
    }
}