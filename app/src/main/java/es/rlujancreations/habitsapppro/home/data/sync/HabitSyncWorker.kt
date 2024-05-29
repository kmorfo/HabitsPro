package es.rlujancreations.habitsapppro.home.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import es.rlujancreations.habitsapppro.home.data.local.HomeDao
import es.rlujancreations.habitsapppro.home.data.local.entity.HabitSyncEntity
import es.rlujancreations.habitsapppro.home.data.mapper.toDomain
import es.rlujancreations.habitsapppro.home.data.mapper.toDto
import es.rlujancreations.habitsapppro.home.data.remote.HomeApi
import es.rlujancreations.habitsapppro.home.data.remote.util.resultOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * Created by Raúl L.C. on 29/5/24.
 */
@HiltWorker
class HabitSyncWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    private val api: HomeApi,
    private val dao: HomeDao
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val items = dao.getAllHabitsSync()

        if (runAttemptCount >= 3) return Result.failure()

        return try {
            supervisorScope {
                val jobs = items.map { items -> launch { sync(items) } }
                jobs.forEach { it.join() }
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
        /*
        Si queremos insertar uno a uno cada habit en la api sin el try-catch
        items.forEach {
                    sync(it.id)
                }
        return Result.success()
                */
    }

    private suspend fun sync(entity: HabitSyncEntity) {
        val habit = dao.getHabitById(entity.id).toDomain().toDto()

        resultOf { api.insertHabit(habit) }
            .onSuccess {
                dao.deleteHabitSync(entity)
            }.onFailure { throw it }

    }
}