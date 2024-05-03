package es.rlujancreations.habitsapppro.home.presentarion.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.habitsapppro.core.presentation.HabitCheckBox
import es.rlujancreations.habitsapppro.home.domain.models.Habit
import java.time.LocalDate

/**
 * Created by Raúl L.C. on 16/4/24.
 */

@Composable
fun HomeHabit(
    habit: Habit,
    selectedDate: LocalDate,
    onCheckedChange: () -> Unit,
    onHabitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            .background(Color.White)
            .clickable { onHabitClick() }
            .padding(19.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = habit.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
        HabitCheckBox(isChecked = habit.completedDates.contains(selectedDate)) {
            onCheckedChange()
        }
    }
}