package es.rlujancreations.habitsapppro.home.data.local.typeconverter

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi


/**
 * Created by Raúl L.C. on 22/4/24.
 */
@ProvidedTypeConverter
class HomeTypeConverter {
    @TypeConverter
    fun fromFrequency(days: List<Int>): String {
        return joinIntoStringKmf(days) ?: ""
    }

    @TypeConverter
    fun toFrequency(value: String): List<Int> {
        return splitToIntListKmf(value) ?: emptyList()
    }

    @TypeConverter
    fun fromcompletedDates(days: List<Long>): String {
        return joinIntoString(days) ?: ""
    }

    @TypeConverter
    fun tocompletedDates(value: String): List<Long> {
        return splitToLongList(value) ?: emptyList()
    }

    private fun splitToIntListKmf(input: String?): List<Int>? {
        return input?.split(',')?.mapNotNull { item ->
            try {
                item.toInt()
            } catch (ex: NumberFormatException) {
                Log.e("ROOM", "Malformed integer list", ex)
                null
            }
        }
    }

    private fun joinIntoStringKmf(input: List<Int>?): String? {
        return input?.joinToString(",")
    }

    private fun splitToLongList(input: String?): List<Long>? {
        return input?.split(',')?.mapNotNull { item ->
            try {
                item.toLong()
            } catch (ex: NumberFormatException) {
                Log.e("ROOM", "Malformed integer list", ex)
                null
            }
        }
    }

    private fun joinIntoString(input: List<Long>?): String? {
        return input?.joinToString(",")
    }
}