package es.rlujancreations.habitsapppro.home.data.statup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import es.rlujancreations.habitsapppro.home.data.extension.goAsync
import es.rlujancreations.habitsapppro.home.data.local.HomeDao
import es.rlujancreations.habitsapppro.home.data.mapper.toDomain
import es.rlujancreations.habitsapppro.home.domain.alarm.AlarmHandler
import javax.inject.Inject


/**
 * Created by Raúl L.C. on 30/5/24.
 */
@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {
    @Inject
    lateinit var alarmHandler: AlarmHandler

    @Inject
    lateinit var homeDao: HomeDao
    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if (context == null || intent == null) return@goAsync
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return@goAsync

        val items = homeDao.getAllHabits()

        items.forEach {
            alarmHandler.setRecurringAlarm(it.toDomain())
        }
    }
}