package es.rlujancreations.habitsapppro.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.rlujancreations.habitsapppro.home.data.local.entity.HabitEntity
import es.rlujancreations.habitsapppro.home.data.local.entity.HabitSyncEntity
import es.rlujancreations.habitsapppro.home.data.local.typeconverter.HomeTypeConverter

/**
 * Created by Raúl L.C. on 22/4/24.
 */
@Database(entities = [HabitEntity::class, HabitSyncEntity::class], version = 1, exportSchema = true)
@TypeConverters(HomeTypeConverter::class)
abstract class HomeDatabase : RoomDatabase() {
    abstract val dao: HomeDao
}