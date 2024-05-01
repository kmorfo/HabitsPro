package es.rlujancreations.habitsapppro.home.domain.alarm

import es.rlujancreations.habitsapppro.home.domain.models.Habit

/**
 * Created by Raúl L.C. on 1/5/24.
 */
interface AlarmHandler {
    fun setRecurringAlarm(habit: Habit)
    fun cancel(habit: Habit)
}