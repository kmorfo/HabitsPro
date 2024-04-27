package es.rlujancreations.habitsapppro.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Raúl L.C. on 22/4/24.
 */
@Entity
data class HabitEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val frequency: List<Int>,
    val completedDates: List<Long>,
    val reminder: Long,
    val startDate: Long
)
