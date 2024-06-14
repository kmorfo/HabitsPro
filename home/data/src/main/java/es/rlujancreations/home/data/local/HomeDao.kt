package es.rlujancreations.home.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.rlujancreations.home.data.local.entity.HabitEntity
import es.rlujancreations.home.data.local.entity.HabitSyncEntity

import kotlinx.coroutines.flow.Flow

/**
 * Created by Raúl L.C. on 22/4/24.
 */
@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Query("SELECT * FROM HabitEntity WHERE id = :id")
    suspend fun getHabitById(id: String): HabitEntity

    @Query("SELECT * FROM HabitEntity WHERE startDate <= :date AND userId = :userId ORDER BY id ASC")
    fun getAllHabitsForSelectedDate(date: Long, userId: String): Flow<List<HabitEntity>>

    @Query("SELECT * FROM HabitEntity")
    fun getAllHabits(): List<HabitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitSync(habitSyncEntity: HabitSyncEntity)

    @Query("SELECT * FROM HabitSyncEntity")
    fun getAllHabitsSync(): List<HabitSyncEntity>

    @Delete
    suspend fun deleteHabitSync(habitSyncEntity: HabitSyncEntity)
}