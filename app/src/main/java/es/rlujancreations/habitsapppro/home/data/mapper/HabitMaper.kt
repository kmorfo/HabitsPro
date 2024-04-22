package es.rlujancreations.habitsapppro.home.data.mapper

import es.rlujancreations.habitsapppro.home.data.extension.toStartOfDateTimestamp
import es.rlujancreations.habitsapppro.home.data.extension.toTimeStamp
import es.rlujancreations.habitsapppro.home.data.extension.toZonedDateTime
import es.rlujancreations.habitsapppro.home.data.local.entity.HabitEntity
import es.rlujancreations.habitsapppro.home.domain.models.Habit
import java.time.DayOfWeek

/**
 * Created by Raúl L.C. on 22/4/24.
 */

fun HabitEntity.toDomain(): Habit {
    return Habit(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map { DayOfWeek.of(it) },
        completedDates = this.completedDates.map { it.toZonedDateTime().toLocalDate() },
        reminder = this.reminder.toZonedDateTime().toLocalTime(),
        startDate = this.startDate.toZonedDateTime()
    )
}

fun Habit.toEntity(): HabitEntity {
    return HabitEntity(
        id = this.id,
        name = this.name,
        frequency = this.frequency.map { it.value },
        completedDates = this.completedDates.map { it.toZonedDateTime().toTimeStamp() },
        reminder = this.reminder.toZonedDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDateTimestamp()
    )
}