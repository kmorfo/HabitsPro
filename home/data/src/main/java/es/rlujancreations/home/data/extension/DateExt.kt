package es.rlujancreations.home.data.extension

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

/**
 * Created by Raúl L.C. on 22/4/24.
 */
fun ZonedDateTime.toStartOfDateTimestamp(): Long {
    return truncatedTo(ChronoUnit.DAYS).toEpochSecond() * 1000
}

fun Long.toZonedDateTime(): ZonedDateTime {
    return ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(this),
        ZoneId.systemDefault()
    )
}
fun ZonedDateTime.toTimeStamp(): Long {
    return this.toInstant().toEpochMilli()
}

fun LocalDate.toZonedDateTime(): ZonedDateTime {
    return this.atStartOfDay(ZoneId.systemDefault())
}

fun LocalTime.toZonedDateTime(): ZonedDateTime {
    return this.atDate(LocalDate.now()).atZone(ZoneId.systemDefault())
}