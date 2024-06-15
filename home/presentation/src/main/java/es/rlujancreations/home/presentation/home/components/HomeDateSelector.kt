package es.rlujancreations.home.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.ZonedDateTime

/**
 * Created by Raúl L.C. on 15/4/24.
 */

@Composable
fun HomeDateSelector(
    selectedDate: ZonedDateTime,
    mainDate: ZonedDateTime,
    onDateClick: (ZonedDateTime) -> Unit,
    modifier: Modifier = Modifier,
    datesToShow: Int = 3
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        for (i in datesToShow downTo 0) {
            val date = mainDate.minusDays(i.toLong())
            item {
                HomeDateItem(
                    date = date,
                    isSelected = selectedDate.toLocalDate() == date.toLocalDate()
                ) {
                    onDateClick(date)
                }
            }
        }
    }
}