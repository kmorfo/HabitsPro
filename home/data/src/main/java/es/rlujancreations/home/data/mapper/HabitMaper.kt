package es.rlujancreations.home.data.mapper

import es.rlujancreations.home.data.extension.toStartOfDateTimestamp
import es.rlujancreations.home.data.extension.toTimeStamp
import es.rlujancreations.home.data.extension.toZonedDateTime
import es.rlujancreations.home.data.local.entity.HabitEntity
import es.rlujancreations.home.data.local.entity.HabitSyncEntity
import es.rlujancreations.home.data.remote.dto.HabitDto
import es.rlujancreations.home.data.remote.dto.HabitResponse
import es.rlujancreations.home.domain.models.Habit
import java.time.DayOfWeek

/**
 * Created by Raúl L.C. on 22/4/24.
 */

fun HabitEntity.toDomain(): es.rlujancreations.home.domain.models.Habit {
    return es.rlujancreations.home.domain.models.Habit(
        id = this.id,
        userId = this.userId,
        name = this.name,
        frequency = this.frequency.map { DayOfWeek.of(it) },
        completedDates = this.completedDates.map { it.toZonedDateTime().toLocalDate() },
        reminder = this.reminder.toZonedDateTime().toLocalTime(),
        startDate = this.startDate.toZonedDateTime()
    )
}

fun es.rlujancreations.home.domain.models.Habit.toEntity(): HabitEntity {
    return HabitEntity(
        id = this.id,
        userId = this.userId,
        name = this.name,
        frequency = this.frequency.map { it.value },
        completedDates = this.completedDates.map { it.toZonedDateTime().toTimeStamp() },
        reminder = this.reminder.toZonedDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDateTimestamp()
    )
}

fun HabitResponse.toDomain(): List<es.rlujancreations.home.domain.models.Habit> {
    return this.entries.map {
        val id = it.key
        val dto = it.value
        es.rlujancreations.home.domain.models.Habit(
            id = id,
            userId = dto.userId,
            name = dto.name,
            frequency = dto.frequency.map { DayOfWeek.of(it) },
            completedDates = dto.completedDates?.map { it.toZonedDateTime().toLocalDate() }
                ?: emptyList(),
            reminder = dto.reminder.toZonedDateTime().toLocalTime(),
            startDate = dto.startDate.toZonedDateTime()
        )
    }
}

fun es.rlujancreations.home.domain.models.Habit.toDto(): HabitResponse {
    val dto = HabitDto(
        userId = this.userId,
        name = this.name,
        frequency = this.frequency.map { it.value },
        completedDates = this.completedDates.map { it.toZonedDateTime().toTimeStamp() },
        reminder = this.reminder.toZonedDateTime().toTimeStamp(),
        startDate = this.startDate.toStartOfDateTimestamp()
    )
    return mapOf(id to dto)
}

fun es.rlujancreations.home.domain.models.Habit.toSyncEntity(): HabitSyncEntity {
    return HabitSyncEntity(id)
}