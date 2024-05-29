package es.rlujancreations.habitsapppro.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Raúl L.C. on 29/5/24.
 */
@Entity
data class HabitSyncEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String
)