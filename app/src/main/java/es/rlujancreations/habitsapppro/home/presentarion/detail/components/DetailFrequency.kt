package es.rlujancreations.habitsapppro.home.presentarion.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.rlujancreations.habitsapppro.R
import es.rlujancreations.habitsapppro.home.presentarion.detail.DetailEvent
import java.time.DayOfWeek

/**
 * Created by Raúl L.C. on 17/4/24.
 */
@Composable

fun DetailFrequency(
    selectedDays: List<DayOfWeek>,
    onFrequencyChange: (DayOfWeek) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        Text(
            text = stringResource(id = R.string.frequency),
            modifier = Modifier.padding(17.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.background)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(horizontal = 4.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            val days = DayOfWeek.values()
            days.forEachIndexed { index, dayOfWeek ->
                DetailFrequencyDate(
                    date = dayOfWeek,
                    isChecked = selectedDays.contains(dayOfWeek),
                    onCheckedChange = { onFrequencyChange(dayOfWeek) })
                if (index < days.size - 1) {
                    VerticalDivider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp),
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}